package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SalesLast extends AppCompatActivity {

    TextView textView;
    TextView textView3;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_last);

        textView=(TextView)findViewById(R.id.textView);
        textView3=(TextView)findViewById(R.id.textView3);

        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent intent = new Intent(SalesLast.this, ImageClick.class);
                startActivity(intent);
            }
        });
        
    }
}
