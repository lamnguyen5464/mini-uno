package com.example.lforestor.superminiuno;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.welcome);
        ImageView imageView = (ImageView) findViewById(R.id.im1);
        TextView textView = (TextView) findViewById(R.id.t1);
        TextView textView2 = (TextView) findViewById(R.id.t2);
        imageView.startAnimation(animation);
        textView.startAnimation(animation);
        textView2.startAnimation(animation);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.math_tapping);
        textView.setTypeface(typeface);
        textView2.setTypeface(typeface);

        Timer timer = new Timer();
        timer.schedule(new after_delay(),1000);


    }

    class after_delay extends TimerTask{
        public void run(){
            Intent i = new Intent(splash.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

}
