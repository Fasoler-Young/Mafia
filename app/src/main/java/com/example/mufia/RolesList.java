package com.example.mufia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class RolesList extends AppCompatActivity {
    int num_members = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles_list);

        LinearLayout roles_layout = findViewById(R.id.roles_layout);

        num_members = numRoles();
        Button to_game = findViewById(R.id.to_game);
        to_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(correctRoles()) {
                    writeRoles();
                    Intent intent = new Intent(RolesList.this, Game.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(RolesList.this, "Количество ролей не соответсвует количсеству игроков", Toast.LENGTH_LONG).show();
                }
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

    private int numRoles() {

        int num_members_file = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("cur_members.txt")));
            String word;
            while ((word = br.readLine()) != null) {
                num_members_file++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return num_members_file;
    }

    private boolean correctRoles() {
        LinearLayout roles_layout = findViewById(R.id.roles_layout);

        int num_roles_seekbar = 0;
        LinearLayout num_layout = findViewById(R.id.num_layout);
        for (int i = 0; i < roles_layout.getChildCount(); i++) {
            num_roles_seekbar -= ((SeekBar) num_layout.getChildAt(i)).getProgress() ;
        }
        return num_members == num_roles_seekbar;
    }

    private void writeRoles() {
        LinearLayout roles_layout = findViewById(R.id.roles_layout);
        LinearLayout num_layout = findViewById(R.id.num_layout);
        try{
            //FileOutputStream fw = new FileOutputStream(getFilesDir() + "/" +"cur_members.txt", false);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("cur_roles.txt", MODE_PRIVATE)));
            for (int i = 0; i < roles_layout.getChildCount(); i++) {
                bw.write((((TextView) roles_layout.getChildAt(i)).getText().toString() + '\n'));
                bw.write(Integer.toString(((SeekBar) num_layout.getChildAt(i)).getProgress()) + '\n');
            }
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
