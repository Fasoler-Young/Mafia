package com.example.mufia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game extends AppCompatActivity {

    int nigth = 0;
    ArrayList<Member> members = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        readMembers();

    }

    private void readMembers() {
        try {
            LinearLayout alive_layout = findViewById(R.id.alive_layout);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("cur_members.txt")));
            String word;
            while ((word = br.readLine()) != null) {
                CheckedTextView name = new CheckedTextView( this);
                name.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
                name.setText(word);
                name.setTextSize(24);
                alive_layout.addView(name);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
