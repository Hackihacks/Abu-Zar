package com.example.abuzar.augmentedtour;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * Created by ${ABUZAR} on ${10/21/2017}.
 */
public class FirstActivity extends AppCompatActivity {
    Activity context;
    AnimatorSet set;
    ImageView imgView;
    Button button_database,button_main;
    int imgResources[]={R.drawable.t1, R.drawable.t2,R.drawable.t3,R.drawable.t5,R.drawable.t6,R.drawable.t7,R.drawable.t8};
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getWindow().getDecorView().setBackgroundColor(Color.rgb(255, 255, 224));
        context=this;
    button_database =(Button)findViewById(R.id.button2);
    button_main=(Button)findViewById(R.id.button3);
        button_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, AddLocation.class);
                startActivity(intent);
                context.finish();

            }
        });

        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
                context.finish();



            }
        });

    }




        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }





    @SuppressLint("ResourceType")
    public void onStart(){
        super.onStart();
        imgView=(ImageView)findViewById(R.id.imageview);
        set = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.slideshow);
        set.setTarget(imgView);
        set.addListener(new AnimatorListenerAdapter(){
            public void onAnimationEnd(Animator animator){
                //repeat animation

                if(index<imgResources.length)
                {
                    imgView.setImageResource(imgResources[index]);
                    index++;
                    set.start();
                }
            }


        });
        set.start();

    }


    }

