package com.lamzone.mareu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.service.MareuApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MareuListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Toolbar mToolbar;
    FloatingActionButton mFab;
    MareuApiService apiService;
    MareuRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        //bind views + api
        mToolbar = findViewById(R.id.main_toolbar);
        apiService = DependencyInjector.getMareuApiService();

        //toolbar

        setSupportActionBar(mToolbar);

        //recyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //layoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //adapter
        mAdapter = new MareuRecyclerViewAdapter(apiService.getMeetings());
        mRecyclerView.setAdapter(mAdapter);

        //FAB
        mFab = findViewById(R.id.fab_add_meeting);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent().setClass(getApplicationContext(), AddMeetingActivity.class);
                startActivity(i);
            }
        });






    }

}