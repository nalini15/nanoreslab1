package com.example.login;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class Sales extends Activity {
    CheckBox Comparison,Quotation,Visit;
    Button buttonOrder;
    ImageView imageView;
    ArrayList<String> listItems=new ArrayList<>();
    ArrayAdapter<String> adapter;
    Spinner spinner,spinner2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        //Initializing the ImageView
        ImageView imageView;
        imageView = (ImageView) findViewById(R.id.imageView);

        //Loading Image from URL
        Picasso.get().load("https://www.simplifiedcoding.net/wp-content/uploads/2015/10/advertise.png").into(imageView);

        addListenerOnButtonClick();
        spinner=(Spinner)findViewById(R.id.spinner);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listItems);
        spinner.setAdapter(adapter);

     /*   spinner2=(Spinner)findViewById(R.id.spinner2);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listItems);
        spinner2.setAdapter(adapter);*/


    }

    private void addListenerOnButtonClick() {
       Comparison=(CheckBox)findViewById(R.id.checkBox);
        Quotation=(CheckBox)findViewById(R.id.checkBox2);
        Visit=(CheckBox)findViewById(R.id.checkBox3);
        buttonOrder=(Button)findViewById(R.id.button);

        //Applying the Listener on the Button click
        buttonOrder.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int totalamount=0;
                StringBuilder result=new StringBuilder();
                result.append("Please wait");

                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Sales.this, Contact_form.class);
                startActivity(intent);
            }

        });

    }




    public void onStart(){
        super.onStart();
        BackTask bt=new BackTask();
        bt.execute();
    }
    private class BackTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        protected void onPreExecute(){
            super.onPreExecute();
            list=new ArrayList<>();
        }
        protected Void doInBackground(Void...params){
            InputStream is=null;
            String result="";
            try{
                HttpClient httpclient=new DefaultHttpClient();
                HttpPost httppost= new HttpPost("http://192.168.2.10/sentech_api/getInterviewees.php");
                HttpResponse response=httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            }catch(IOException e){
                e.printStackTrace();
            }

            //convert response to string
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result+=line;
                }
                is.close();
                //result=sb.toString();
            }catch(Exception e){
                e.printStackTrace();
            }
            // parse json data
            try{
                JSONArray jArray =new JSONArray(result);
                for(int i=0;i<jArray.length();i++){
                    JSONObject jsonObject=jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list.add(jsonObject.getString("iname"));
                }
            }
            catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result){
            listItems.addAll(list);
            adapter.notifyDataSetChanged();
        }



    }
}
