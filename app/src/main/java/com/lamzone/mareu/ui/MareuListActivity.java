package com.lamzone.mareu.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.service.MareuApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MareuListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MareuApiService apiService;
    MareuRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bind views + api
        ButterKnife.bind(this);
        apiService = DependencyInjector.getMareuApiService();

        //toolbar
        setContentView(R.layout.list_activity);
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



    }




    @OnClick(R.id.fab_add_meeting)
    void addMeeting(){
        //TODO
    }

}