package com.example.opengltest;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Axies {
    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;

    static final int COORDS_PER_VERTEX = 3;
    // X Y Z 轴坐标
    static float AxiesCoords[] =
                    {-0.0f, 0.0f, 0.5f,   // Z
                    -0.5f, -0.5f, 0.0f,   // Y
                    0.5f, -0.5f, 0.0f};   // X

    private short drawOrder[] = {0, 1, 2, 0, 2, 3}; // 顶点的绘制顺序


    public Axies() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (坐标数 * 4)
                AxiesCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(AxiesCoords);
        vertexBuffer.position(0);

        // 为绘制列表初始化字节缓冲
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (对应顺序的坐标数 * 2)short是2字节
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }

    public void draw(GL10 gl) {

        gl.glColor4f(0.8f,0.0f,0.0f,1.0f);
//        gl.glLoadIdentity();
        gl.glTranslatef(0.1f,0,-2.0f);
//        gl.glFrontFace(GL10.GL_CCW);
//        gl.glEnable(GL10.GL_CULL_FACE);
//        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0,
                vertexBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, drawOrder.length,
                GL10.GL_UNSIGNED_SHORT, drawListBuffer);
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        gl.glDisable(GL10.GL_CULL_FACE);

    }



}
