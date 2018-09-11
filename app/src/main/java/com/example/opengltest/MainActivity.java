package com.example.opengltest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

   private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OpenGLView openGLView = new OpenGLView(this);
        setContentView(openGLView);

    }
}
