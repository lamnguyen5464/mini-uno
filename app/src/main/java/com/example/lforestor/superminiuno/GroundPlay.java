package com.example.lforestor.superminiuno;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class GroundPlay extends Activity {
    ImageView pic;
    ImageButton bt1, bt2, repl, home,btsound;
    ProgressBar pb;
    TextView point,newscore,bestscore, plus1,icon_new,or;
    short answer;
    Dialog dialog;
    int mark = 0,timeup = 1000, tick = 1, add = 15, card;
    Random rand = new Random();
    ArrayList<card> arr;
    int[] head = new int[50];
    Handler handler = new Handler();
    SharedPreferences sharedPreferences;
    SoundPlayer sound;
    RelativeLayout bg;
    View v;
    int precolor=0;
    Animation animation;
    boolean intsound = true;
    //----------------------------------------
    void determine(){
        pic = (ImageView) findViewById(R.id.imageView);
        or = (TextView) findViewById(R.id.or);
        bt1 = (ImageButton) findViewById(R.id.imageButton);
        bt2 = (ImageButton) findViewById(R.id.imageButton2);
        btsound = (ImageButton) findViewById(R.id.btsound);
        point  = (TextView) findViewById(R.id.point);
        pb  = (ProgressBar) findViewById(R.id.progressBar);
        plus1 = (TextView) findViewById(R.id.plus1);
        v = this.getWindow().getDecorView(); //IMPORTANT
        sound = new SoundPlayer(GroundPlay.this);
        animation = AnimationUtils.loadAnimation(this,R.anim.replay);



    }
    int generate_ranom(int num){
        int p = rand.nextInt(arr.size()-1)+1;
        while (head[p] == head[num] || Math.abs(p-num)%4 == 0) {
            p = rand.nextInt(arr.size() - 1) + 1;
        }
        return p;
    }
    int generate_samecolor(int num){
        int p = rand.nextInt(20) * 4 + num;
        while (p>44){
            p-=40;
        }

        return p;
    }
    int generate_samenum(int num){
        int p = head[num] + rand.nextInt(3) + 1;
        while (p == num){
            p = head[num] + rand.nextInt(3) + 1;
        }
        return p;
    }
    void show(){
        int pos = rand.nextInt(arr.size()-5) + 1;  // avoid the first element of arr
        pic.setImageResource(arr.get(pos).getPic());
        int ans = rand.nextInt(25);
        int re;
        if (ans <=20){ //have the same number
            re = rand.nextInt(10);
            Log.d("same number","");
            if ((re & 1)==1){
                Log.d("same number","1");
                card = generate_samenum(pos);
                bt1.setImageResource(arr.get(card).getPic());
                bt2.setImageResource(arr.get(generate_ranom(pos)).getPic());
                answer = 1;
                bt1.setBackgroundResource(R.drawable.custom_true_button);
                bt2.setBackgroundResource(R.drawable.custom_false_button);

            }
            else {
                Log.d("same number","2");
                card = generate_samenum(pos);
                bt2.setImageResource(arr.get(card).getPic());
                bt1.setImageResource(arr.get(generate_ranom(pos)).getPic());
                answer = 2;
                bt2.setBackgroundResource(R.drawable.custom_true_button);
                bt1.setBackgroundResource(R.drawable.custom_false_button);
            }
        }
        else{  //have the same color
            re = rand.nextInt();
            Log.d("same color","");
            if (re == 1){
                Log.d("same color","1");
                card = generate_samecolor(pos);
                bt1.setImageResource(arr.get(card).getPic());
                bt2.setImageResource(arr.get(generate_ranom(pos)).getPic());
                answer = 1;
                bt1.setBackgroundResource(R.drawable.custom_true_button);
                bt2.setBackgroundResource(R.drawable.custom_false_button);
            }
            else{
                Log.d("same color","2");
                card = generate_samecolor(pos);
                bt2.setImageResource(arr.get(card).getPic());
                bt1.setImageResource(arr.get(generate_ranom(pos)).getPic());
                answer = 2;
                bt2.setBackgroundResource(R.drawable.custom_true_button);
                bt1.setBackgroundResource(R.drawable.custom_false_button);
            }
        }
    }
    void lose(){
        if (intsound) sound.PlayLoseSound();
        vibrate();
        count.cancel();
        bt1.setEnabled(false); bt2.setEnabled(false);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int bezt = sharedPreferences.getInt("bestscore",0);
        if (mark > bezt){
            bezt = mark;
            editor.putInt("bestscore",mark);
            icon_new.setVisibility(View.VISIBLE);
            editor.commit();
        }
        dialog.show();
        newscore.setText(""+mark);
        bestscore.setText(""+bezt);

    }
    void set_dialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_replay);
        dialog.setCanceledOnTouchOutside(false);
//        Log.d("set dialog","123");
        repl = (ImageButton) dialog.findViewById(R.id.repl);
        home = (ImageButton) dialog.findViewById(R.id.home);
        newscore = (TextView) dialog.findViewById(R.id.newscore);
        bestscore = (TextView) dialog.findViewById(R.id.bestscore);
        icon_new = (TextView) dialog.findViewById(R.id.iconNew);
        icon_new.setVisibility(View.INVISIBLE);
    }
    void show_bonus(){
        plus1.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                plus1.setVisibility(View.INVISIBLE);
            }
        },1000);
    }
    void action(int i){
        Log.d("answer",""+answer+" "+i);
        if (mark==0){
            or.setVisibility(View.INVISIBLE);
        }
        count.cancel();
        if (i == answer) {
            if (intsound) sound.PlayHitSound();

            mark+=1;
            if ((mark<15 && mark%2==0) || (mark%10==0 && mark<50)) add+=2;
            if (card>=41 && card<=44){
                Log.d("bonus",""+card);
                show_bonus();
                mark+=1;
            }
            point.setText(""+mark);
            pb.setProgress(0);
            count.start();
            show();
        }
        else {
            lose();
        };


    }
    void rand_background(){
        //randome color background
        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
        v.setBackgroundColor(androidColors[precolor]);
        if (precolor==3) precolor=0; else precolor++;
        //

    }
    void receive(){
        Intent intent = getIntent();
        arr = (ArrayList<card>) intent.getSerializableExtra("listcard");
        head = intent.getIntArrayExtra("head");
        //Log.d("extra ",""+ arr.get(4).getName() + " " + head[3]);

    }
    final CountDownTimer count = new CountDownTimer(timeup+10000, tick) {
        @Override
        public void onTick(long l) {
            if (pb.getProgress()<pb.getMax()) {
                pb.setProgress(pb.getProgress() + add);
            }
            else{
                lose(); count.cancel();
            }
        };

        @Override
        public void onFinish() {
            Log.d("time up",""+pb.getProgress());
           // pb.setProgress(pb.getMax());
           // lose();

        };
    };
    private void vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(500);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) { //Main
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ground_play);
        determine();
        rand_background();
        receive();
        show();
        set_dialog();
        sharedPreferences = getSharedPreferences("score",MODE_PRIVATE);
        plus1.setVisibility(View.INVISIBLE);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action(1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action(2);
            }
        });
        repl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                icon_new.setVisibility(View.INVISIBLE);
                dialog.cancel();
                bt1.setEnabled(true); bt2.setEnabled(true);
                or.setVisibility(View.VISIBLE);
                rand_background();
                //anim
                bt1.startAnimation(animation); bt2.startAnimation(animation);
                pic.startAnimation(animation);
                point.startAnimation(animation);
                //
                pb.setProgress(0);
                mark = 0;
                add = 10;
                point.setText(""+mark);
                show();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btsound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intsound){
                    btsound.setImageResource(R.drawable.soundoff);
                }else{
                    btsound.setImageResource(R.drawable.soundon);
                }
                intsound = !(intsound);
            }
        });
        //ads
       // MobileAds.initialize(this,"ca-app-pub-8228726508975919~1830224981");
        AdView admview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        admview.loadAd(adRequest);

    }
}
