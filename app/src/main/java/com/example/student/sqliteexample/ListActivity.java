package com.example.student.sqliteexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private List<Student> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initView();
        loadData();

    }



    private void loadData() {
        MySqlite mySqlite = new MySqlite(this);
        Cursor cursor = mySqlite.getData();


        if (cursor.getCount() > 0) {

            // we receive data

            while (cursor.moveToNext()) {

                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String dept = cursor.getString(2);
                String number = cursor.getString(3);

                Student student = new Student(id, name, dept, number);

                studentList.add(student);


            }


            List_Adapter adapter = new List_Adapter(this, studentList);
            listView.setAdapter(adapter);

        } else {
            // nodata found
        }
    }

    private void initView() {
        listView = findViewById(R.id.lvId);
    }
}
