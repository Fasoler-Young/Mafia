package com.example.mufia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RolesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_list);

        LinearLayout roles_layout = findViewById(R.id.roles_layout);

        Button to_game = findViewById(R.id.to_game);
        to_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RolesList.this, Game.class);
                startActivity(intent);
            }
        });


        //Чтение из файлов
        /*
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("cur_members.txt")));
            String word;
            while ((word = br.readLine()) != null) {
                TextView name = new TextView( this);
                name.setText(word);
                roles_layout.addView(name);
            }
        }catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
