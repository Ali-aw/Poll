package com.example.demproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivitySurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_survey);
    }
    ArrayList<String> array=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    public void add(View view)
    {
        EditText title=(EditText) findViewById(R.id.title);
        EditText choice=(EditText) findViewById(R.id.choice);
        EditText date=(EditText) findViewById(R.id.date);
        TextView text=(TextView)findViewById(R.id.text);

        String t=title.getText().toString();
        String ch=choice.getText().toString();
        String d=date.getText().toString();

        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,array);
        array.add(ch);


        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();


        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            public void addChoice(String title,String choice,String date) {

                try {
                    URL url = new URL("http://172.26.192.1:8080/addChoice.php?Title="+title+"&choice="+choice+"&date="+date);
                    // System.out.println("est");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    //System.out.println("est1");
                    con.setRequestMethod("GET");
                    System.out.println("===");

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

                addChoice(t,ch,d);
                System.out.println("yyyyy");

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ListView list=(ListView)findViewById(R.id.list);
                        list.setAdapter(arrayAdapter);
                        choice.setText("");
                        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                String t=parent.getItemAtPosition(position).toString();
                                text.setText(t);
                                return false;
                            }
                        });


                    }
                });
                handlerThread.getLooper().quit();
            }
        });
    }



    public void delete(View view)
    {
        EditText e=(EditText) findViewById(R.id.title);
        String s1=e.getText().toString();
        TextView e2=(TextView) findViewById(R.id.text);
        String s2=e2.getText().toString();

        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();


        Handler mainHandler =new Handler(getApplicationContext().getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            public void deleteChoice(String title,String choice) {

                try {
                    URL url = new URL("http://172.26.192.1:8080/deleteChoice.php?Title="+title+"&choice="+choice);
                    // System.out.println("est");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    //System.out.println("est1");
                    con.setRequestMethod("GET");
                    // System.out.println("**");

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

                deleteChoice(s1,s2);
                System.out.println("deleteddd ");

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "the choice:  "+s2+" is deleted", Toast.LENGTH_SHORT).show();
                        // ListView list1=(ListView)findViewById(R.id.list);
                        // list.getpo
                        //list.removeViewAt(1);
                        //arrayAdapter.notifyDataSetChanged();
                        int i=array.indexOf(s2);
                        array.remove(i);
                        // ArrayAdapter<String> arrayAdapter1=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,array);
                        arrayAdapter.notifyDataSetChanged();

                    }
                });
                handlerThread.getLooper().quit();
            }
        });
    }


    public void ok(View view)
    {
        Intent I3=new Intent(getApplicationContext(),MainActivityAdmin.class);
        //  I.putExtra( "var",s);
        startActivity(I3);
    }


}