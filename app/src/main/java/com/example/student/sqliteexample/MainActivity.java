package com.example.student.sqliteexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etname,etdept,etnumber;
    private TextView tvShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        etname=(EditText)findViewById(R.id.etname);
        etdept=(EditText)findViewById(R.id.etdept);
        etnumber=(EditText)findViewById(R.id.etnumber);
        tvShowData = findViewById(R.id.viewData);
    }

    public void clickSave(View view) {

        String name=etname.getText().toString().trim();
        String dept=etdept.getText().toString().trim();
        String number=etnumber.getText().toString().trim();
        if (name.isEmpty()){
            etname.setError("plz enter name");
            etname.requestFocus();
            return;
        }
        if (dept.isEmpty()){
            etdept.setError("plz enter dept");
            etdept.requestFocus();
            return;
        }
        if (number.isEmpty()){
            etnumber.setError("plz enter number");
            etnumber.requestFocus();
            return;
        }

        MySqlite mySqlite= new MySqlite(this);

        long checker = mySqlite.insertData(name,dept,number);

        if (checker == -1){
            Toast.makeText(this, "Failed To Insert", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
        }


    }

    public void viewData(View view) {
        MySqlite mySqlite = new MySqlite(this);
        Cursor cursor = mySqlite.getData();

        StringBuffer stringBuffer = new StringBuffer();

        if (cursor.getCount()>0) {


            while (cursor.moveToNext()) {
                stringBuffer.append(cursor.getString(0)).append(" , ");
                stringBuffer.append(cursor.getString(1)).append(" , ");
                stringBuffer.append(cursor.getString(2)).append(" , ");
                stringBuffer.append(cursor.getString(3)).append(" \n\n ");

            }

            tvShowData.setText(stringBuffer);
        }


        else {
            tvShowData.setText("No data found");
        }
    }
}
