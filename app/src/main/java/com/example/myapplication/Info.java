package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Info extends AppCompatActivity {
    SQLiteDatabase sqlDB;
    SQLiteDatabase programDB;
    DBHelper centerDBHelper;
    DBHelper2 programDBHelper;
    TextView center_name;
    TextView center_address;
    ListView program_list;
    ListView equipment_list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        center_name = findViewById(R.id.center_name);
        center_address = findViewById(R.id.center_address);
        program_list = findViewById(R.id.program_list);
        equipment_list = findViewById(R.id.equipment_list);

        Intent intent = getIntent();
        String center_id = intent.getExtras().getString("center_id");

        //DB에서 정보 받아오기
        sqlDB = centerDBHelper.getReadableDatabase();
        Cursor cursor = sqlDB.rawQuery("SELECT * FROM CENTER WHERE id = " + center_id, null);
        String center_name_str = cursor.getString(1);
        String center_address_str = cursor.getString(2);
        center_name.setText(center_name_str);
        center_address.setText(center_address_str);

        programDB = programDBHelper.getReadableDatabase();
        cursor = programDB.rawQuery("SELECT * FROM INFO WHERE (id = "+center_id+")&()", null);
        ListViewAdapter program_adapter = new ListViewAdapter();
        while(cursor.moveToNext()){
            program_adapter.addItemToList(cursor.getString(0), cursor.getInt(1));
        }
        program_list.setAdapter(program_adapter);

        cursor = programDB.rawQuery("SELECT * FROM INFO WHERE id = " + center_id, null);
        ListViewAdapter equipment_adapter = new ListViewAdapter();
        while(cursor.moveToNext()){
            equipment_adapter.addItemToList(cursor.getString(0), cursor.getInt(1));
        }
        equipment_list.setAdapter(equipment_adapter);

        // 툴바 생성
        Toolbar toolbar2 = findViewById(R.id.info_toolbar);
        setSupportActionBar(toolbar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        getSupportActionBar().setTitle(center_name_str); // 툴바 제목 설정


    }
}
