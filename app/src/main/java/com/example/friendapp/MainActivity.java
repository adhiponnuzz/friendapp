package com.example.friendapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    AppCompatButton b1,b2;
    String getyn,getfn,getnn,getdyf;
    String apiurl="https://dummyapifriends.herokuapp.com/adddata";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.yn);
        ed2=(EditText) findViewById(R.id.fn);
        ed3=(EditText) findViewById(R.id.nn);
        ed4=(EditText) findViewById(R.id.dyf);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b2=(AppCompatButton) findViewById(R.id.menu);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getyn=ed1.getText().toString();
                getfn=ed2.getText().toString();
                getnn=ed3.getText().toString();
                getdyf=ed4.getText().toString();

                StringRequest sr=new StringRequest(Request.Method.POST, apiurl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");

                                Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();


                            }
                        })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hm=new HashMap<>();
                        hm.put("name",getyn);
                        hm.put("friendName",getfn);
                        hm.put( "friendNickName",getnn);
                        hm.put("DescribeYourFriend",getdyf);

                        return hm;
                    }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);


            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MenuActivity.class);
                startActivity(i);

            }
        });

    }
}