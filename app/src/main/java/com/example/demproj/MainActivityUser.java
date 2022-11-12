package com.example.demproj;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivityUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);


    }
    ArrayList<String> a=new ArrayList<>();;
    String[] arr;
    boolean[] bo;
    String line;
    String linev;
    public void submit(View view)
    {
        Intent I2=getIntent();
        String m=I2.getStringExtra("em");
        EditText editText=(EditText) findViewById(R.id.tt);
        String e=editText.getText().toString();

        AlertDialog.Builder al=new AlertDialog.Builder(this);
        al.setTitle(e);
        //al.create().show();
        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();
        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            public void addVote(String email,String title) {

                try {
                    URL url2 = new URL("http://172.26.192.1:8080/addVote.php?Email="+email+"&Title="+title);
                    // System.out.println("est");
                    HttpURLConnection con2=(HttpURLConnection) url2.openConnection();
                    //System.out.println("est1");
                    con2.setRequestMethod("GET");
                    System.out.println("****");

                    InputStream input2=con2.getInputStream();
                    BufferedReader r2=new BufferedReader(new InputStreamReader(input2));

                    linev=r2.readLine();
                    System.out.println(linev);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }


            public void getChoices(String title) {

                try {
                    URL url = new URL("http://172.26.192.1:8080/getChoices.php?Title="+title);
                    // System.out.println("est");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    //System.out.println("est1");
                    con.setRequestMethod("GET");
                    System.out.println("11111");

                    InputStream input=con.getInputStream();
                    BufferedReader r=new BufferedReader(new InputStreamReader(input));

                    line=r.readLine();
                    arr=line.split(",");

                    System.out.println(arr);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            public void run() {
                //  System.out.println(m);
                addVote(m,e);
                System.out.println(linev);
                //  System.out.println(line);
                if(linev.equals("aa"))
                { getChoices(e);
                    //addNb("holiday","forest");
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            al.setMultiChoiceItems(arr,bo, new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                    System.out.println("    yesss"+arr[which]);
                                    //addNb(e,arr[which]);
                                    a.add(arr[which]);
                                    System.out.println("    yesss"+a);
                                }
                            });
                            al.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "if you sure click done ", Toast.LENGTH_SHORT).show();

                                }
                            });
                            al.create().show();
                        }
                    });

                }
                else
                {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            al.setMessage(" you voted before ");
                            al.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "choose another title ", Toast.LENGTH_SHORT).show();
                                }
                            });
                            al.create().show();
                        }
                    });

                }

                handlerThread.getLooper().quit();
            }
        });
    }

    public void done(View view)
    {
        EditText editText=(EditText) findViewById(R.id.tt);
        String e=editText.getText().toString();

        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();
        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {

            public void addNb(String title,String choice) {

                try {
                    URL url2 = new URL("http://172.26.192.1:8080/addNb.php?Title="+title+"&choice="+choice);
                    // System.out.println("99");
                    HttpURLConnection con2=(HttpURLConnection) url2.openConnection();
                    //System.out.println("111");
                    con2.setRequestMethod("GET");
                    InputStream input2=con2.getInputStream();
                    BufferedReader r2=new BufferedReader(new InputStreamReader(input2));

                    String line2=r2.readLine();
                    System.out.println(line2);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }


            }
            public void run() {

                int k=a.size();
                // addNb("holidays","forest");
                for(int i=0;i<k;i++)
                {
                    System.out.println(a.get(i)+"gggggggggggggggggg");
                    addNb(e,a.get(i));
                    // addNb("aller","e1");
                }

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(), "you choose "+a, Toast.LENGTH_SHORT).show();
                        a.clear();
                    }
                });
                handlerThread.getLooper().quit();
            }
        });
    }
}
