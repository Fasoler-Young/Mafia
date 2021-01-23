package com.example.mufia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Member> members = new ArrayList<Member>();
    ArrayList<String> roles = new ArrayList<>();
    int night = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemberList.class);
                startActivity(intent);
            }
        });
        /*addMembers("Ilya");
        createMemberList();
        Toast.makeText(this, Integer.toString(members.size()), Toast.LENGTH_LONG).show();
        createRoleList();
        while (notEnd()){
            night++;
            for (String role : roles){

                if(night==1){

                }
            }
        }
        int i = 0;*/

    }

    private void createRoleList() {

    }

    private boolean notEnd() {
        return true;
    }


    public void createMemberList(){

        try {
            InputStreamReader reader = new InputStreamReader(getAssets().open("members.txt"));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                members.add(new Member(word));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addMembers(String name){
        members.add(new Member(name));
        try{
            FileOutputStream fw = new FileOutputStream("members.txt", true);
            fw.write((name + '\n').getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
