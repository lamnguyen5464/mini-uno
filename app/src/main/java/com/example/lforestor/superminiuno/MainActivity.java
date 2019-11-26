package com.example.lforestor.superminiuno;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.media.tv.TvContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    ImageView pic;
    Button bt,bt2,bt3,close;
    TextView txt;
    ArrayList<card> arr;
    Random rand;
    Dialog dialog;
    SoundPlayer sound;


    int[] head = new int[50];
    void determined(){
        bt  = (Button) findViewById(R.id.bt);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        sound = new SoundPlayer(this);
    }
    void add(){
        arr = new ArrayList<>();
        int i = 1, j;

        arr.add( new card("null",R.drawable.color));
        //0
        arr.add( new card("Blue 0",R.drawable.blue0));
        arr.add( new card("Green 0",R.drawable.green0));
        arr.add( new card("Red 0",R.drawable.red0));
        arr.add( new card("Yellow 0",R.drawable.yellow0));
        j = i + 3;for (i=1; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j+" "+head[3]);
        //1
        arr.add( new card("Blue 1",R.drawable.blue1));
        arr.add( new card("Green 1",R.drawable.green1));
        arr.add( new card("Red 1",R.drawable.red1));
        arr.add( new card("Yellow 1",R.drawable.yellow1));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //2
        arr.add( new card("Blue 2",R.drawable.blue2));
        arr.add( new card("Green 2",R.drawable.green2));
        arr.add( new card("Red 2",R.drawable.red2));
        arr.add( new card("Yellow 2",R.drawable.yellow2));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //3
        arr.add( new card("Blue 3",R.drawable.blue3));
        arr.add( new card("Green 3",R.drawable.green3));
        arr.add( new card("Red 3",R.drawable.red3));
        arr.add( new card("Yellow 3",R.drawable.yellow3));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //4
        arr.add( new card("Blue 4",R.drawable.blue4));
        arr.add( new card("Green 4",R.drawable.green4));
        arr.add( new card("Red 4",R.drawable.red4));
        arr.add( new card("Yellow 4",R.drawable.yellow4));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //5
        arr.add( new card("Blue 5",R.drawable.blue5));
        arr.add( new card("Green 5",R.drawable.green5));
        arr.add( new card("Red 5",R.drawable.red5));
        arr.add( new card("Yellow 5",R.drawable.yellow5));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //6
        arr.add( new card("Blue 6",R.drawable.blue6));
        arr.add( new card("Green 6",R.drawable.green6));
        arr.add( new card("Red 6",R.drawable.red6));
        arr.add( new card("Yellow 6",R.drawable.yellow6));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //7
        arr.add( new card("Blue 7",R.drawable.blue7));
        arr.add( new card("Green 7",R.drawable.green7));
        arr.add( new card("Red 7",R.drawable.red7));
        arr.add( new card("Yellow 7",R.drawable.yellow7));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //8
        arr.add( new card("Blue 8",R.drawable.blue8));
        arr.add( new card("Green 8",R.drawable.green8));
        arr.add( new card("Red 8",R.drawable.red8));
        arr.add( new card("Yellow 8",R.drawable.yellow8));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //9
        arr.add( new card("Blue 9",R.drawable.blue9));
        arr.add( new card("Green 9",R.drawable.green9));
        arr.add( new card("Red 9",R.drawable.red9));
        arr.add( new card("Yellow 9",R.drawable.yellow9));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //null
        arr.add( new card("Blue null",R.drawable.bluenull));
        arr.add( new card("Green null",R.drawable.greennull));
        arr.add( new card("Red null",R.drawable.rednull));
        arr.add( new card("Yellow null",R.drawable.yellownull));
        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;}; Log.d("num",""+j);
        //plus2
//        arr.add( new card("Blue plus2",R.drawable.blueplus2));
//        arr.add( new card("Green plus2",R.drawable.greenplus2));
//        arr.add( new card("Red plus2",R.drawable.redplus2));
//        arr.add( new card("Yellow plus2",R.drawable.yellowplus2));
//        j = i + 3;for (i=i; i<=j; i++){ head[i] = j-4;};

        Log.d("Size:",""+arr.size());
    }
    void set_dialog(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.about);
        close = (Button) dialog.findViewById(R.id.cl);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ads
                MobileAds.initialize(this,"ca-app-pub-8228726508975919~1830224981");
                AdView admview = (AdView)findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                admview.loadAd(adRequest);
        //ads
        determined();
        set_dialog();
        add();
        for (int i = 1; i<arr.size(); i+=1){
            Log.d("card ",""+i+" "+arr.get(i).getName());
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GroundPlay.class);
                intent.putExtra("listcard",arr);
                Log.d("heade",""+head[3]);
                intent.putExtra("head",head);
                startActivity(intent);
//
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //ads



    }
}
