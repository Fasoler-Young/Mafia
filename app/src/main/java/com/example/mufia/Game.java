package com.example.mufia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Game extends AppCompatActivity {

    int nigth = 0;
    ArrayList<Member> members = new ArrayList<>();
    ArrayList<Roles> roles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        readMembers();

        readRoles();

        aliveLayout();

        firstDay();

    }

    private void readRoles() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("cur_roles.txt")));
            String word;
            while ((word = br.readLine()) != null) {
                String role = word;
                int num = Integer.parseInt(br.readLine());
                roles.add(new Roles(role, num));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void firstDay() {

    }

    private void aliveLayout() {

        LinearLayout alive_layout = findViewById(R.id.alive_layout);

        for(int i = 0; i < members.size(); i++) {
            CheckedTextView name = new CheckedTextView(this);
            name.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
            name.setChecked(false);
            name.setText(members.get(i).name);
            name.setTextSize(24);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((CheckedTextView) view).isChecked()) {
                        ((CheckedTextView) view).setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
                        ((CheckedTextView) view).setChecked(false);

                    } else {
                        uncheckAll();
                        ((CheckedTextView) view).setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                        ((CheckedTextView) view).setChecked(true);
                    }
                }
            });

            alive_layout.addView(name);
        }
    }

    private void readMembers() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("cur_members.txt")));
            String word;
            while ((word = br.readLine()) != null) {
                members.add(new Member(word));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void uncheckAll() {
        LinearLayout alive_layout = findViewById(R.id.alive_layout);
        for(int i = 0; i < alive_layout.getChildCount(); i++){
            ((CheckedTextView) alive_layout.getChildAt(i)).setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
            ((CheckedTextView) alive_layout.getChildAt(i)).setChecked(false);
        }

    }
}
