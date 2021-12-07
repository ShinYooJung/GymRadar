package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;


public class DB extends AppCompatActivity{

    Button btnSave;
    Button btnSelect;
    Button btnDelete;
    EditText edtId;
    EditText edtName;
    EditText edtAddr;
    EditText edtCoordinateX;
    EditText edtCoordinateY;
    EditText deleteName;
    LinearLayout listView;

    DBHelper dbHelper;
    DBHelper2 dbHelper2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        // 툴바 생성
        Toolbar toolbar2 = findViewById(R.id.db_toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle("DB에 등록"); // 툴바 제목 설정
        //nothing
        btnSave = findViewById(R.id.button);
        btnSelect = findViewById(R.id.button2);
        btnDelete = findViewById(R.id.button3);
        edtId = findViewById(R.id.center_id);
        edtName = findViewById(R.id.et);
        edtAddr = findViewById(R.id.et2);
        edtCoordinateX = findViewById(R.id.et3);
        edtCoordinateY = findViewById(R.id.et4);
        deleteName = findViewById(R.id.et5);
        listView = findViewById(R.id.listView);

        dbHelper = new DBHelper(DB.this, 1);//db생성자
        dbHelper2 = new DBHelper2(DB.this, 1);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertTC(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(), edtAddr.getText().toString(), Float.parseFloat(edtCoordinateX.getText().toString()), Float.parseFloat(edtCoordinateY.getText().toString()));
                dbHelper2.insertPrice(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(), "1", "default");//개월, pt별 가격정보를 Price 테이블에 넣는다.
                dbHelper2.insertPrice(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(), "3", "default");
                dbHelper2.insertPrice(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(), "6", "default");
                dbHelper2.insertPrice(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(), "12", "default");
                dbHelper2.insertPrice(Integer.parseInt(edtId.getText().toString()),edtName.getText().toString(), "pt", "default");
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewResult.setText(dbHelper.getResultTC());
                listView.removeAllViewsInLayout();
                List<String> list = dbHelper.getResultTC();
                for(int i = 0; i<list.size(); i++){
                    CreateTextView(list.get(i));

                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteTC(deleteName.getText().toString());
                dbHelper2.deletePrice(deleteName.getText().toString());
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

    private void CreateTextView(String contents){
        TextView textViewNm = new TextView(getApplicationContext());
        textViewNm.setText(contents);
        textViewNm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DB.this, Modify.class);
                String[] strList = contents.split(":");
                intent.putExtra("이름", strList[1].split(",")[0]); //'이름'라는 이름으로 main_text 전달
                startActivity(intent);
            }
        });
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        param.leftMargin = 30;
        //레이아웃 설정
        textViewNm.setLayoutParams(param);
        listView.addView(textViewNm);
    }
}