package com.mhkz.loginandsignup;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity {
    Button update, scan, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        scan = findViewById(R.id.scan);
        update = findViewById(R.id.update);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggout();
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }


    private void scan() {
        //startActivity(new Intent(this, Activities.class));
        Intent i= new Intent(dashboard.this,scanactivity.class);
        startActivity(i);
    }
    private void update() {
        Intent i= new Intent(dashboard.this,updateactivity.class);
        startActivity(i);
    }

    // logout below
    private void loggout()
    {
        finish();
        startActivity(new Intent(dashboard.this,MainActivity.class));
        Toast.makeText(dashboard.this,"LOGOUT SUCCESSFUL", Toast.LENGTH_SHORT).show();

    }
}
