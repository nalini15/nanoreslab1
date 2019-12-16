package com.example.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

public class ImageClick extends AppCompatActivity {
    ImageButton image_serv,image_sales;
    ImageView image_research;
    ImageView image_supp;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_click);

        viewPager = (ViewPager) findViewById(R.id.viewPage);
        ImageAdapter adapterView = new ImageAdapter(this);
        viewPager.setAdapter(adapterView);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);

        image_serv = (ImageButton) findViewById(R.id.image_serv);
        image_serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ImageClick.this,Service.class);
                startActivity(intentLoadNewActivity);
            }
        });
        image_sales=(ImageButton)findViewById(R.id.image_sales);
        image_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ImageClick.this,Sales.class);
                startActivity(intentLoadNewActivity);
            }
        });
        image_research=(ImageView)findViewById(R.id.image_research);
        image_supp=(ImageView)findViewById(R.id.image_supp);
    }

    public class MyTimerTask extends TimerTask {

        public void run() {
            ImageClick.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}