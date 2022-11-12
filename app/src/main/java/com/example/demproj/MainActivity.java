package com.example.demproj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;






public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void submit(View V)
    {
       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference();
       // EditText E=(EditText) findViewById(R.id.edit_message);
        //String s=E.getText().toString();
       // myRef.child("name").setValue(s);*/

      //  TextView t=(TextView) findViewById(R.id.t1);
     //   t.setText(s);
         //Intent I=new Intent(this,MainActivity2.class);
         // I.putExtra( "var",s);
       // ActivityResultContracts.StartActivityForResult(I);
        // startActivity(I);
    }

    public void signup(View V)
    {
        Intent I=new Intent(this,MainActivity2.class);
       //  I.putExtra( "var",s);
       startActivity(I);
       HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();

        Handler mainHandler =new Handler(this.getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
         /*  public void getUser(int id) {

                try {
                    URL url = new URL("http://172.26.192.1:8080/get.php?id=1");
                    // System.out.println("est");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    System.out.println("111");
                    con.setRequestMethod("GET");
                    System.out.println("1111");

                    InputStream input=con.getInputStream();
                    BufferedReader r=new BufferedReader(new InputStreamReader(input));
                    System.out.println("222");

                    String line=r.readLine();

                    System.out.println(line);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }


            }*/


            public void addUser(String email,String password,String type) {

                try {
                    URL url = new URL("http://172.26.192.1:8080/add.php?Email="+email+"&Pass="+password+"&Type="+type);
                    // System.out.println("iss");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    //System.out.println("111");
                    con.setRequestMethod("GET");
                    System.out.println("hiiii");

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
                addUser("Ali","Atwi","A");
                System.out.println("hiiii1");

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                handlerThread.getLooper().quit();
            }
        });

    }
//========================================
    /*
    public void signin(View V)
    {


        EditText email=(EditText)findViewById(R.id.Email);

        EditText pass=(EditText)findViewById(R.id.Password);

        String e=email.getText().toString();
        String p=pass.getText().toString();

        HandlerThread handlerThread=new HandlerThread("myhandler");
        handlerThread.start();

        Handler mainHandler =new Handler(this.getMainLooper());

        Handler handler=new Handler(handlerThread.getLooper());

        handler.post(new Runnable() {
            public void getType(String email,String pass) {
                String line;

                try {
                    URL url = new URL("http://172.26.192.1:8080/getType.php?Email="+email+"&Pass="+pass);
                    // System.out.println("esss");
                    HttpURLConnection con=(HttpURLConnection) url.openConnection();
                    // System.out.println("1111");
                    con.setRequestMethod("GET");
                    //System.out.println("est11");

                    InputStream input=con.getInputStream();
                    BufferedReader r=new BufferedReader(new InputStreamReader(input));
                    System.out.println("hiiii3");

                    line=r.readLine();

                    if(line.equals("A"))
                    {
                        System.out.println("aaaaa");
                        Intent I1=new Intent(getApplicationContext(),MainActivity.class);
                        I1.putExtra( "em",email);
                        startActivity(I1);
                    }
                    else if(line.equals("U"))
                    {
                        System.out.println("est00000000000");
                        Intent I2=new Intent(getApplicationContext(),MainActivity.class);
                        I2.putExtra("em",email);
                        startActivity(I2);
                    }

                    System.out.println(line);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }


            }

            public void run() {
                getType(e,p);
                System.out.println("est0");

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                handlerThread.getLooper().quit();
            }
        });

    }
*/
}




