package com.lamzone.mareu.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.events.RoomSelectedEvent;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MareuApiService;
import com.lamzone.mareu.ui.pickers.DatePickerFragment;
import com.lamzone.mareu.ui.pickers.RoomPickerFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MareuListActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFab;
    private MareuApiService apiService;
    private MareuRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Meeting> meetingList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //bind views + api

        mFab = findViewById(R.id.fab_add_meeting);
        mRecyclerView = findViewById(R.id.recyclerView);
        apiService = DependencyInjector.getMareuApiService();

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        initList();
    }

    /**
     * get the latest set of data using apiService for the recycler view adapter
     */
    public void initList(){
        meetingList = new ArrayList<>(apiService.getAllMeetings());
        mAdapter = new MareuRecyclerViewAdapter(meetingList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filter_date:
                datePopup();
                return false;
            case R.id.filter_room:
                roomPopup();
                return true;
            case R.id.no_filter:
                mAdapter = new MareuRecyclerViewAdapter(apiService.getAllMeetings());
                mRecyclerView.setAdapter(mAdapter);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * creates a date popup using @DatePickerFragment
     */
    public void datePopup(){
        DatePickerFragment picker = new DatePickerFragment();
        picker.show(getSupportFragmentManager(),"date_filter");
    }

    /**
     * creates a room popup using @RoomPickerFragment
     */
    public void roomPopup(){
        DialogFragment popup = new RoomPickerFragment();
        popup.show(getSupportFragmentManager(),"room_filter");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        mAdapter = new MareuRecyclerViewAdapter(apiService.filterByDate(cal.getTime()));
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * called when FAB is clicked
     * @param v
     */
    public void onFABClick(View v){
        Intent i = new Intent().setClass(getApplicationContext(), AddMeetingActivity.class);
        startActivity(i);
    }

    /**
     * fired when the room filter button is pressed
     * @param event
     */
    @Subscribe
    public void onRoomSelectedEvent(RoomSelectedEvent event){
        mAdapter.notifyDataSetChanged();
        mAdapter = new MareuRecyclerViewAdapter(apiService.filterByRoom(event.room));
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * get new meeting list for tests
     */
    @VisibleForTesting
    public void useNewApiService(){
        apiService = DependencyInjector.getNewInstanceApiService();
        meetingList.clear();
        meetingList.addAll(apiService.getAllMeetings());
        mAdapter.notifyDataSetChanged();
    }
}