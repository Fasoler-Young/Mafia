package com.example.mufia;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MemberList extends AppCompatActivity {

    ArrayList<Member> members = new ArrayList<Member>();
// add members default
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        final LinearLayout members_layout = findViewById(R.id.members_layout);
        Button add_new_name = findViewById(R.id.add_new_name);
        final EditText new_name = findViewById(R.id.text_new_name);

        add_new_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = new_name.getText().toString();
                if(!name.equals("")){
                    //LinearLayout name_layout = new LinearLayout(MemberList.this);
                    //name_layout.setOrientation(LinearLayout.HORIZONTAL);
                    TextView name_view = new TextView(MemberList.this);
                    name_view.setText(name);
                    name_view.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            ((ViewGroup) v.getParent()).removeView(v);
                            return true;
                        }
                    });
                    members_layout.addView(name_view);
                }
            }
        });

        Button to_roles = findViewById(R.id.to_roles);
        to_roles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MemberList.this, Integer.toString(members_layout.getChildCount()), Toast.LENGTH_LONG).show();
                try{
                    //FileOutputStream fw = new FileOutputStream(getFilesDir() + "/" +"cur_members.txt", false);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                            openFileOutput("cur_members.txt", MODE_PRIVATE)));
                    for (int i = 0; i < members_layout.getChildCount(); i++) {
                        bw.write((((TextView) members_layout.getChildAt(i)).getText().toString() + '\n'));
                    }
                    bw.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(MemberList.this, RolesList.class);
                startActivity(intent);
            }
        });


    }

}
