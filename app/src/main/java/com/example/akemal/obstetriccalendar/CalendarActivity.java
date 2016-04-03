package com.example.akemal.obstetriccalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.andexert.calendarlistview.library.DatePickerController;
import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;

import java.util.Calendar;

/**
 * Created by akemal on 31-03-16.
 */
public class CalendarActivity extends Activity implements DatePickerController{

    private static final int REQUESTDATE = 1;
    private DayPickerView dayPickerView;
    private int[] dateArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        dayPickerView = (DayPickerView)findViewById(R.id.picker_view);
        dayPickerView.setController(this);
        dateArray = new int [3];
    }

    @Override
    public int getMaxYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR)+1;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        Log.e("AA", day + " / " + month + " / " + year);
        dateArray[0] = year;
        dateArray[1] = month+1;
        dateArray[2] = day;
        Intent data = new Intent();
        data.putExtra("DATE", dateArray);
        setResult(RESULT_OK, data);
        finish();
    }


    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {

    }

}
