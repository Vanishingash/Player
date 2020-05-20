package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Player extends AppCompatActivity implements SurfaceHolder.Callback{

    private MediaPlayer mp3Player;
    private MediaPlayer mp4Player;
    private Button button;
    private Button button3;
    private RadioButton radioButton,radioButton2;
    private SurfaceView surfaceView2;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        radioButton=(RadioButton) findViewById(R.id.radioButton);
        radioButton2=(RadioButton) findViewById(R.id.radioButton2);
        button = (Button)findViewById(R.id.button);
        button3 = (Button) findViewById(R.id.button3);
        surfaceView2 = (SurfaceView)findViewById(R.id.surfaceView2);

        mp3Player = new MediaPlayer();
        mp3Player = MediaPlayer.create(this,R.raw.test);
        mp4Player = new MediaPlayer();
        mp4Player = MediaPlayer.create(this,R.raw.demo);

        mSurfaceHolder=surfaceView2.getHolder();
        mSurfaceHolder.addCallback((SurfaceHolder.Callback)this);


        button3.setEnabled(false);

        mp3Player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("完成。");
            }
        });

        mp4Player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                System.out.println("完成。");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked()){
                    mp3_play();
                    button3.setEnabled(true);
                    button.setEnabled(false);
                }
                else if (radioButton2.isChecked()){
                    mp4_play();
                    button3.setEnabled(true);
                    button.setEnabled(false);
                }
                else
                    Toast.makeText(getApplicationContext(),"请勾选播放类型！",Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButton.isChecked()) {
                    button.setEnabled(true);
                    mp3Player.pause();
                    button3.setEnabled(false);
                }
                else if (radioButton2.isChecked()){
                    mp4Player.pause();
                    button.setEnabled(true);
                    button3.setEnabled(false);
                }
            }
        });
    }

    private void mp3_play(){
        try{
            System.out.println("正在播放……");
            mp3Player.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void mp4_play(){
        try{
            System.out.println("正在播放……");
            mp4Player.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder){
        mp4Player.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
    }
}

