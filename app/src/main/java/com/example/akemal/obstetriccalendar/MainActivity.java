package com.example.akemal.obstetriccalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUESTDATE = 1;
    private static final int REQUESTEDD = 2;
    private static final int REQUESTLMP = 3;
    private int yearLMP, monthLMP, dayLMP;
    private int yearEDD, monthEDD, dayEDD;
    private int[] dateArray;

    private Button btnEDD, btnLMP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateArray = new int[] {2015,1,10};

        btnEDD = (Button) findViewById(R.id.buttonEDD);
        btnLMP = (Button) findViewById(R.id.buttonLMP);
    }

    public void setDueDate(int yearLMP, int monthLMP, int dayLMP) {

        Calendar cal = Calendar.getInstance();
        cal.set(yearLMP, monthLMP, dayLMP);
        cal.add(Calendar.DATE, 280);

        yearEDD = cal.get(Calendar.YEAR);
        monthEDD = cal.get(Calendar.MONTH);
        dayEDD = cal.get(Calendar.DATE);
    }

    public void onCalendarPickRequest(View view) {
        Intent intent = new Intent(this, CalendarActivity.class);
        switch (view.getId()) {
            case R.id.buttonLMP:
                startActivityForResult(intent, REQUESTEDD);
                break;
            case R.id.buttonEDD:
                startActivityForResult(intent, REQUESTLMP);
                break;
        }

    }

    private void updateButtonViews() {
        btnLMP.setText(dayLMP + "-" + monthLMP + "-" + yearLMP);
        btnEDD.setText(dayEDD + "-" + monthEDD + "-" + yearEDD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            dateArray = data.getIntArrayExtra("DATE");
            if (requestCode == REQUESTEDD) {
                yearLMP = dateArray[0]; monthLMP = dateArray[1]; dayLMP = dateArray[2];
                setDueDate(yearLMP, monthLMP, dayLMP);
                Log.e("AA", "Success " + dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2]);

            } else if (requestCode == REQUESTLMP) {
                yearEDD = dateArray[0]; monthEDD = dateArray[1]; dayEDD = dateArray[2];
                setLMPDate(yearEDD, monthEDD, dayEDD);
            }
            updateButtonViews();
        }

    }

    private void setLMPDate(int yearEDD, int monthEDD, int dayEDD) {
        Calendar cal = Calendar.getInstance();
        cal.set(yearEDD, monthEDD, dayEDD);
        cal.add(Calendar.DATE, -280);

        yearLMP = cal.get(Calendar.YEAR);
        monthLMP = cal.get(Calendar.MONTH);
        dayLMP = cal.get(Calendar.DATE);
    }
}
