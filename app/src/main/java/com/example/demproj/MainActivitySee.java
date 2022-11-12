package com.example.demproj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;


import java.net.URL;
import java.util.ArrayList;


public class MainActivitySee extends AppCompatActivity {
    ArrayList<String> array=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    String[] arr;
    boolean[] bo;
    String line;
    int i;
    ArrayList<String> array2=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter2;
    String[] arr2;
    boolean[] bo2;
    String line2;
    int j;
    ArrayList<String> array3=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter3;
    String[] arr3;
    boolean[] bo3;
    String line3;
    int h;
    String t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_see);


        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,array);

        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();


        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            public void polls() {

                try {
                    URL url = new URL("http://172.26.192.1:8080/polls.php");
                    // System.out.println("est");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    //System.out.println("est1");
                    con.setRequestMethod("GET");
                    System.out.println("1111");

                    InputStream input=con.getInputStream();
                    BufferedReader r=new BufferedReader(new InputStreamReader(input));
                    line=r.readLine();
                    arr=line.split(",");
                    i=arr.length;
                    for(int k=0;k<i;k++)
                    {
                        array.add(arr[k]);
                    }
                    System.out.println(arr[1]);
                    System.out.println(array);
                    System.out.println(line);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            public void run() {

                polls();
                System.out.println("yyyyy");
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                             /*ListView res=(ListView)findViewById(R.id.l1);
                             res.setAdapter(arrayAdapter2);*/
                        Spinner s=(Spinner) findViewById(R.id.spinner);
                        s.setAdapter(arrayAdapter);
                        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                t=parent.getItemAtPosition(position).toString();
                                //
                                System.out.println(t);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                });
                handlerThread.getLooper().quit();
            }
        });
    }

    public void view(View view)
    {

        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();

        arrayAdapter2=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,array2);
        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            public void getResult(String title) {

                try {
                    URL url2= new URL("http://172.26.192.1:8080/getResult.php?Title="+title);
                    // System.out.println("est");
                    HttpURLConnection con2=(HttpURLConnection) url2.openConnection();
                    //System.out.println("est1");
                    con2.setRequestMethod("GET");
                    System.out.println("1111");

                    InputStream input2=con2.getInputStream();
                    BufferedReader r2=new BufferedReader(new InputStreamReader(input2));
                    line2=r2.readLine();
                    arr2=line2.split(",");
                    j=arr2.length;
                    array2.clear();
                    for(int k=0;k<i;k++)
                    {
                        array2.add(arr2[k]);
                    }
                    // System.out.println(arr[1]);
                    //  System.out.println(array);
                    System.out.println(line2);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            public void run() {

                getResult(t);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ListView res=(ListView)findViewById(R.id.l1);
                        res.setAdapter(arrayAdapter2);
                        System.out.println(array2);
                        //   array2.clear();System.out.println("jjjjjjjj"+array2);
                    }
                });
                //
                handlerThread.getLooper().quit();
            }
        });
    }

    public void view2(View view)
    {
        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();

        arrayAdapter3=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,array3);
        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            public void getP(String title) {

                try {
                    URL url2= new URL("http://172.26.192.1:8080/getP.php?Title="+title);
                    // System.out.println("est");
                    HttpURLConnection con2=(HttpURLConnection) url2.openConnection();
                    //System.out.println("est1");
                    con2.setRequestMethod("GET");
                    System.out.println("1111");

                    InputStream input2=con2.getInputStream();
                    BufferedReader r2=new BufferedReader(new InputStreamReader(input2));
                    line3=r2.readLine();
                    arr3=line3.split(",");
                    h=arr3.length;
                    array3.clear();
                    for(int k=0;k<h;k++)
                    {
                        array3.add(arr3[k]);
                    }
                    // System.out.println(arr[1]);
                    //  System.out.println(array);
                    System.out.println(line3);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            public void run() {

                getP(t);
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ListView res2=(ListView)findViewById(R.id.p2);
                        res2.setAdapter(arrayAdapter3);
                        System.out.println(array3);
                        //   array2.clear();System.out.println("jjjjjjjj"+array2);
                    }
                });
                //
                handlerThread.getLooper().quit();
            }
        });
    }
}