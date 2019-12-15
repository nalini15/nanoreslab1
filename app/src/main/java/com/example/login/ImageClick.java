package com.example.login;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class ImageClick extends AppCompatActivity {

    ImageButton image_serv,image_sales;
    ImageView image_research;
    ImageView image_supp;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_click);


        image_serv = (ImageButton) findViewById(R.id.image_serv);
        image_serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ImageClick.this, Service.class);
                startActivity(intentLoadNewActivity);
            }
        });
        image_sales = (ImageButton) findViewById(R.id.image_sales);
        image_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(ImageClick.this,Sales.class);
                startActivity(intentLoadNewActivity);
            }
        });
        image_research = (ImageView) findViewById (R.id.image_research);
        image_supp = (ImageView) findViewById(R.id.image_supp);
        textView2 =(TextView) findViewById(R.id.textView2);

    }
}