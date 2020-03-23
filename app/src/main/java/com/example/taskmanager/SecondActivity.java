package com.example.taskmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.net.Uri;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Calendar;

public class SecondActivity extends Activity{

    DatePickerDialog datePicker;
    EditText editText;
    TextView editText2;
    EditText editText3;
    Button klik;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle b = getIntent().getExtras();

        editText = (EditText) findViewById(R.id.editText);
        editText2 = (TextView) findViewById(R.id.textView3);
        editText3 = (EditText) findViewById(R.id.editText3);
        klik = (Button) findViewById(R.id.button3);

        editText2.setInputType(EditorInfo.TYPE_NULL);
        klik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // time picker dialog
                datePicker = new DatePickerDialog(SecondActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker dp, int sYear, int sMonth, int sDate) {
                                sMonth++;
                                editText2.setText(sYear + "-" + sMonth + "-" + sDate);
                            }
                        }, year, month, day);
                datePicker.show();
            }
        });

    }
    public void onClick(View view){
        Intent i = new Intent();
        Log.d("due date", editText2.getText().toString());

        String due = editText2.getText().toString()+" 00:00:00";

        Log.d("due", due);
        Task newTask = new Task( editText.getText().toString(), Timestamp.valueOf(due), editText3.getText().toString());
        i.putExtra("newTask", newTask);

        setResult(RESULT_OK, i);
        finish();
    }
}
