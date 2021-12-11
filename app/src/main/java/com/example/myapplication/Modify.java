package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Modify extends AppCompatActivity {
    Button btnModify;
    EditText et1;
    EditText et3;
    EditText et6;
    EditText et12;
    EditText pt;
    TextView tv;
    String sub_text;
    DBHelper2 dbHelper2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        // 툴바 생성
        Toolbar toolbar5 = findViewById(R.id.modify_toolbar);
        setSupportActionBar(toolbar5);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("정보 수정"); // 툴바 제목 설정

        btnModify = findViewById(R.id.btnmodify);
        et1 = findViewById(R.id.et1);
        et3 = findViewById(R.id.et3);
        et6 = findViewById(R.id.et6);
        et12 = findViewById(R.id.et12);
        pt = findViewById(R.id.pt);
        tv = findViewById(R.id.textView);
        Intent intent = getIntent();//DB에서 보낸 intent를 받는다
        sub_text = intent.getStringExtra("이름");

        dbHelper2 = new DBHelper2(Modify.this, 1);
        tv.setText(sub_text);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper2.updatePrice(sub_text, "1", et1.getText().toString());
                dbHelper2.updatePrice(sub_text, "3", et3.getText().toString());
                dbHelper2.updatePrice(sub_text, "6", et6.getText().toString());
                dbHelper2.updatePrice(sub_text, "12", et12.getText().toString());
                dbHelper2.updatePrice(sub_text, "pt", pt.getText().toString());
                Intent intent2 = new Intent(Modify.this, Info.class);
                intent2.putExtra("이름2", sub_text); //'이름2'라는 이름으로 main_text 전달
                startActivity(intent2);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                // 액티비티 이동
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}