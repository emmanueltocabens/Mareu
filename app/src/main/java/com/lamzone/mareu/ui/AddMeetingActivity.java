package com.lamzone.mareu.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.model.Room;
import com.lamzone.mareu.service.MareuApiService;
import com.lamzone.mareu.ui.pickers.DatePickerFragment;
import com.lamzone.mareu.ui.pickers.TimePickerFragment;
import com.lamzone.mareu.utils.MareuUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private Button button_confirm;
    private Button button_participants;
    private Button button_clear;
    private EditText et_title;
    private EditText et_date;
    private EditText et_start;
    private EditText et_end;
    private EditText et_participants;
    private Spinner spinner;
    private MareuApiService apiService;
    private Date startDate;
    private Date endDate;
    private Room room;
    private List<String> participants;
    private String picker;
    private LinearLayout layout_participants;
    private ArrayAdapter<CharSequence> adapter;
    private List<Room> displayedRooms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init all variables
        setContentView(R.layout.add_activity);
        apiService = DependencyInjector.getMareuApiService();
        participants = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        displayedRooms = new ArrayList<>(apiService.getAllRooms());
        List<String> data = MareuUtils.getRoomNames(displayedRooms);
        ActionBar actionBar = getSupportActionBar();
        linkViews();

        actionBar.setDisplayHomeAsUpEnabled(true);

        //create spinner
        adapter.addAll(data);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                room = apiService.getAllRooms().get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        et_title.setMaxLines(1);
        setListeners();
        unlockRooms();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        startDate = c.getTime();
        et_date.setText(MareuUtils.getDateString(c.getTime()));
        unlockRooms();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        String display = MareuUtils.getTimeString(c.getTime());
        switch(picker){
            case "start":
                et_start.setText(display);
                startDate = c.getTime();
                break;
            case "end":
                endDate = c.getTime();
                et_end.setText(display);
                break;
            default:
                break;
        }
        unlockRooms();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * checks if all fields are filled correctly
     * @return
     */
    public boolean areAllFieldsFilledCorrectly() {
        boolean ret = true;
        if (et_title.getText().length() == 0)
            ret = false;
        if (et_start.getText().length() == 0)
            ret = false;
        if (et_end.getText().length() == 0)
            ret = false;
        if (et_date.getText().length() == 0)
            ret = false;
        if (layout_participants.getChildCount() < 2)
            ret = false;
        if (startDate == null || endDate == null) {
            ret = false;
        } else if (startDate.after(endDate))
            ret = false;
        return ret;
    }

    /**
     * if all the fields are correctly filled, unlocks the room spinner, else locks the spinner
     */
    private void unlockRooms(){
        spinner.setEnabled(false);
        if(et_start.getText().length() != 0 && et_end.getText().length() != 0 && et_date.getText().length() != 0) {
            adapter.clear();
            displayedRooms.clear();
            displayedRooms = apiService.getAvailableRooms(startDate,endDate);
            adapter.addAll(MareuUtils.getRoomNames(displayedRooms));
            spinner.setAdapter(adapter); 
            spinner.setEnabled(true);
        }
    }

    /**
     * adds onClickListener on all components
     */
    private void setListeners(){
        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date");
            }
        });

        et_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment startTimePicker = new TimePickerFragment();
                startTimePicker.show(getSupportFragmentManager(),"start");
                picker = "start";
            }
        });

        et_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment startTimePicker = new TimePickerFragment();
                startTimePicker.show(getSupportFragmentManager(),"end");
                picker = "end";
            }
        });

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_title.getText().clear();
                et_start.getText().clear();
                et_end.getText().clear();
                et_date.getText().clear();
                et_participants.getText().clear();
                participants.clear();
                layout_participants.removeAllViews();
            }
        });

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(areAllFieldsFilledCorrectly()) {
                    Meeting meeting = new Meeting(startDate, endDate, room, participants, et_title.getText().toString());
                    apiService.addNewMeeting(meeting);
                    finish();
                } else {
                    CharSequence txt = "Tous les champs ne sont pas correctement remplis.";
                    Toast.makeText(v.getContext(),txt,Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et_participants.getText().toString();
                participants.add(s);
                TextView tv = new TextView(v.getContext());
                tv.setText(s);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
                layout_participants.addView(tv);
                et_participants.getText().clear();
            }
        });
    }

    /**
     * link views and variables
     */
    private void linkViews(){
        et_title = findViewById(R.id.add_et_title);
        et_date = findViewById(R.id.add_et_date);
        et_start = findViewById(R.id.add_et_time_start);
        et_end = findViewById(R.id.add_et_time_end);
        et_participants = findViewById(R.id.add_et_participants);
        spinner = findViewById(R.id.room_spinner);
        button_confirm = findViewById(R.id.confirm_button);
        button_clear = findViewById(R.id.clear_button);
        button_participants = findViewById(R.id.button_add_participant);
        layout_participants = findViewById(R.id.layout_participants);
    }
}
