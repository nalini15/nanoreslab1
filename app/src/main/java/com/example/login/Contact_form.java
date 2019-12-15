package com.example.login;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Contact_form extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        ((Button) findViewById(R.id.button_send)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String sub = ((EditText)findViewById(R.id.edit_text_subject)).getText().toString();
                String mess = ((EditText)findViewById(R.id.edit_text_message)).getText().toString();
                Intent mail = new Intent(Intent.ACTION_SEND);



                mail.putExtra(Intent.EXTRA_EMAIL,new String[]{"epowersoftnalini@gmail.com"});
                mail.putExtra(Intent.EXTRA_SUBJECT, sub);
                mail.putExtra(Intent.EXTRA_TEXT, mess);
                mail.setType("message/rfc822");
                startActivity(Intent.createChooser (mail, "Send email via:"));
            }
        });
    }
}