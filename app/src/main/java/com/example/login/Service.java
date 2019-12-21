package com.example.login;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Service extends AppCompatActivity {


    private EditText Issue;
    private EditText Machine_name;
    private EditText Model_number;
    private EditText Manufacture;
    private EditText Date_of_installation;
    private EditText Organization_name;
    private EditText Address;
    private EditText Contact_number;
    // define the global variable

    // Add button Move previous activity
    Button submit_button;
    String ServerURL = "http://192.168.2.15/Service_db/get_data.php" ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);



        submit_button = (Button)findViewById(R.id.third_activity_previous_button);
        Issue = (EditText) findViewById(R.id.id_issue);
        Machine_name = (EditText) findViewById(R.id.id_name);
        Model_number = (EditText) findViewById(R.id.id_number);
        Manufacture = (EditText) findViewById(R.id.id_manu);
        Date_of_installation = (EditText) findViewById(R.id.id_date);
        Organization_name =(EditText) findViewById(R.id.id_org);
        Contact_number =(EditText)findViewById(R.id.id_contact);
        Address =(EditText)findViewById(R.id.id_address);

        submit_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {
                Intent intent = new Intent(Service.this, Service_contact.class);
                startActivity(intent);
            }
        });


        final String issue = Issue.getText().toString();
        final String machine_name = Machine_name.getText().toString();
        final String model_number = Model_number.getText().toString();
        final String manufacture = Manufacture.getText().toString();
        final String date_of_installation = Date_of_installation.getText().toString();
        final String organization_name = Organization_name.getText().toString();
        final String contact_number = Contact_number.getText().toString();
        final String address = Address.getText().toString();

        if(issue.isEmpty() || machine_name.isEmpty() || model_number.isEmpty() || manufacture.isEmpty() || date_of_installation.isEmpty() || organization_name.isEmpty() || contact_number.isEmpty() || address.isEmpty()){
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        }

        else {
            class Login extends AsyncTask<Void, Void, String> {
                ProgressDialog pdLoading = new ProgressDialog(Service.this);

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                    //this method will be running on UI thread
                    pdLoading.setMessage("\tLoading...");
                    pdLoading.setCancelable(false);
                    pdLoading.show();
                }

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("issue", issue);
                    params.put("machine_name", machine_name);
                    params.put("model_number", model_number);
                    params.put("manufacture", manufacture);
                    params.put("date_of_installation", date_of_installation);
                    params.put("organization_name", organization_name);
                    params.put("contact_number", contact_number);
                    params.put("address", address);


                    //returing the response
                    return requestHandler.sendPostRequest(ServerURL , params);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    pdLoading.dismiss();

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(s);
                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Service.this, "Exception: " + e, Toast.LENGTH_LONG).show();
                    }
                }
            }

            Login login = new Login();
            login.execute();
        }
    }

        public void login(View view){
        finish();
        Intent intent = new Intent(Service.this, LoginActivity.class);
        startActivity(intent);
    }
    }

