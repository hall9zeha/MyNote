package com.barryzea.mynote.ui;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.barryzea.mynote.R;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin=findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MiNoteMenuActivity.class);
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}