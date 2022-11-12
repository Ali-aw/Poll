package com.example.demproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivityAdmin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);


    }

    public void createPoll(View view)
    {
        Intent IS=new Intent(this,MainActivitySurvey.class);
        startActivity(IS);

    }

    public void votePoll(View view)
    {
        Intent I3=getIntent();
        String m=I3.getStringExtra("em");
        Intent Iv=new Intent(getApplicationContext(),MainActivityVote.class);
        Iv.putExtra("emm",m);
        startActivity(Iv);
    }

    public void see(View view)
    {
        Intent Is=new Intent(getApplicationContext(),MainActivitySee.class);
        // Iv.putExtra("emm",m);
        startActivity(Is);
    }


}