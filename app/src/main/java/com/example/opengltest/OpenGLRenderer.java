package com.example.opengltest;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements GLSurfaceView.Renderer {
    private Square mSquare;
    private Axies mAxies;
    private Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0,0,0,1f);
        mSquare = new Square();
        mAxies = new Axies();
        mTriangle = new Triangle();
    }

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    @Override
    public void onSurfaceChanged(GL10 gl , int width, int height) {
        GLES20.glViewport(0,0,width,height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public float getAngle() {
        return mAngle;
    }
    public void setAngle(float angle) {
        mAngle = angle;
    }

    private float[] mRotationMatrix = new float[16];
    public volatile float mAngle;
    @Override
    public void onDrawFrame(GL10 gl){
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // 设置相机位置（查看矩阵）
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        // 计算投影和视图变换
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        float[] scratch = new float[16];
        // 为三角形创建一个旋转变换
        //long time = SystemClock.uptimeMillis() % 4000L;
        //float angle = 9f * ((int) time);
        Matrix.setRotateM(mRotationMatrix, 0, mAngle, 0, 0, -1.0f);
        // Combine the rotation matrix with the projection and camera view
        // Note that the mMVPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, mRotationMatrix, 0);
        // 绘制形状
        // mTriangle.draw(scratch);
        mTriangle.draw(scratch);

    }

    public static int loadShader(int type, String shaderCode) {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }


}
