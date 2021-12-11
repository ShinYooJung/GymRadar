package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewCenter extends AppCompatActivity {

    DBHelper tcDBHelper;

    EditText name_et;
    TextView x_tv;
    TextView y_tv;
    Button add_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcenter);

        Toolbar toolbar = findViewById(R.id.newcenter_toolbar);
        setSupportActionBar(toolbar);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        name_et = findViewById(R.id.center_name);
        x_tv = findViewById(R.id.center_x);
        y_tv = findViewById(R.id.center_y);
        add_button = findViewById(R.id.add_button);
        tcDBHelper = new DBHelper(this, 1);

        //수정해야함
        int x = 0;
        int y = 0;

        x_tv.setText(""+x);
        y_tv.setText(""+y);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_et.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_LONG).show();
                }
                else{
                    String center_name = name_et.getText().toString();
                    int id = tcDBHelper.insertTC(name_et.getText().toString(), x, y);
                    Intent intent = new Intent(getApplicationContext(), Info.class);
                    intent.putExtra("center_id",id);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:{
                //Intent intent = new Intent(getApplicationContext(), .class);
                //startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
