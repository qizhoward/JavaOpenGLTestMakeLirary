package com.example.opengltest;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;
/**
 * Created by qizhoward on  2018/09/11
 * Email:yupeng1152629941@163.com
 */

public class Square {

    private FloatBuffer vertexBuffer,colorBuffer;
    private ShortBuffer drawListBuffer;
    static final int COORDS_PER_VERTEX = 3;
    //正方形四个顶点的坐标
    static float squareCoords[] =
            {-0.5f, 0.5f, 0.0f,   // top left
             -0.5f, -0.5f, 0.0f,   // bottom left
             0.5f, -0.5f, 0.0f,   // bottom right
             0.5f, 0.5f, 0.0f}; // top right
    private short drawOrder[] =
            {0, 1, 2, 0, 2, 3}; // 顶点的绘制顺序

    public Square() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (坐标数 * 4)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
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
}
