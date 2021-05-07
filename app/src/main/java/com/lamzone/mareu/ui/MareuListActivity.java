package com.lamzone.mareu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.service.MareuApiService;


public class MareuListActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RecyclerView mRecyclerView;
    Toolbar mToolbar;
    FloatingActionButton mFab;
    MareuApiService apiService;
    MareuRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //bind views + api
        mToolbar = findViewById(R.id.main_toolbar);
        mFab = findViewById(R.id.fab_add_meeting);
        mRecyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.filter_button);
        apiService = DependencyInjector.getMareuApiService();

        //toolbar
        setSupportActionBar(mToolbar);



        //recyclerView
        mRecyclerView.setHasFixedSize(true);

        //layoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //adapter
        mAdapter = new MareuRecyclerViewAdapter(apiService.getMeetings());
        mRecyclerView.setAdapter(mAdapter);

        //FAB
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent().setClass(getApplicationContext(), AddMeetingActivity.class);
                startActivity(i);
            }
        });
    }

    public void showPopup(View view) {
        PopupMenu pop = new PopupMenu(this,button);
        pop.setOnMenuItemClickListener(this);
        pop.inflate(R.menu.menu_filters);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter1:

                return true;
            case R.id.filter2:

                return true;
            case R.id.filter3:

                return true;
            case R.id.filter4:

                return true;
            default:

                return true;
        }
    }
}