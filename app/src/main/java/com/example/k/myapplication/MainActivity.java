package com.example.k.myapplication;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    Button button;
    boolean b = true;
    int relativeLayout_height  = 0;
    int a = 0;
    RelativeLayout re_layout;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    my.method(a,relativeLayout_height);
                    break;
                case 2:
                    my.method(a,relativeLayout_height);
                    break;
                default:break;
            }
        }

    };
    MyView my;
    float value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my = (MyView) findViewById(R.id.my);

        re_layout = (RelativeLayout) findViewById(R.id.re_layout);
        button = (Button) findViewById(R.id.button);
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        b = true;
                        while(b){
                            a+=5;
                            Message message = new Message();
                            message.what = 2;
                            handler.sendMessage(message);
                            if(a<600){
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(a==600){
                                a=0;
                                b = false;
                            }
                        }
                    }
                }).start();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout_height = re_layout.getMeasuredHeight();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(a==0&&b==false){
                        b = true;
                    }
                    while(b){
                        a+=5;
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                        if(a<300){
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if(a==300){
                            b = false;
                        }
                    }
                }
            }).start();
            }
        });
    }

}