package com.example.demproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity2 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent I=getIntent();
        //String m=I.getStringExtra("variable");
        //TextView T1=(TextView)findViewById(R.id.item1);
        //T1.setText(m);



        Button b1=(Button)findViewById(R.id.create);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText email=(EditText) findViewById(R.id.email);
                EditText pass1=(EditText) findViewById(R.id.pass1);
                EditText pass2=(EditText) findViewById(R.id.pass2);
                EditText type=(EditText) findViewById(R.id.type);

                String e= String.valueOf(email.getText());
                String p1= String.valueOf(pass1.getText());
                String p2= String.valueOf(pass2.getText());
                String t= String.valueOf(type.getText());
                HandlerThread handlerThread=new HandlerThread("myhandler");
                handlerThread.start();


                Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

                Handler handler=new Handler(handlerThread.getLooper());

                handler.post(new Runnable() {
                    public void addUser(String email,String password,String type) {

                        try {
                            URL url = new URL("http://172.26.192.1:8080/add.php?Email="+email+"&Pass="+password+"&Type="+type);
                            // System.out.println("est");
                            HttpURLConnection con=(HttpURLConnection) url.openConnection();
                            //System.out.println("est1");
                            con.setRequestMethod("GET");
                            System.out.println("is11");

                            InputStream input=con.getInputStream();
                            BufferedReader r=new BufferedReader(new InputStreamReader(input));

                            String line=r.readLine();

                            System.out.println(line);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }


                    }
                    public void run() {

                        if(p1.equals(p2)) {
                            addUser(e,p1,t);
                            System.out.println("hellow");
                        }
                        else
                            System.out.println(" password not identical ");

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(t.equals("A"))
                                    Toast.makeText(getApplicationContext(), "admin added", Toast.LENGTH_SHORT).show();
                                else if(t.equals("U"))
                                    Toast.makeText(getApplicationContext(), "user added", Toast.LENGTH_SHORT).show();

                            }
                        });
                        handlerThread.getLooper().quit();
                    }
                });


            }
        });
/*
        public void createAccount(View view)
        {
            Button b1=(Button)view;
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            EditText email=(EditText) findViewById(R.id.email);
            System.out.println("neeefe");
        }
*/

    }

    public void createAccount(View view) {
    }
}