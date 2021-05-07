package com.lamzone.mareu.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MareuApiService;

import java.util.List;

public class MeetingFragment extends Fragment {

    private Button button;
    private MareuApiService mareuApiService;
    private List<Meeting> mMeetings;
    private RecyclerView mRecyclerView;

    public static MeetingFragment newInstance(){
        MeetingFragment fragment = new MeetingFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mareuApiService = DependencyInjector.getMareuApiService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_activity, container, false);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }



    private void initList(){
        mMeetings = mareuApiService.getMeetings();
        mRecyclerView.setAdapter(new MareuRecyclerViewAdapter(mMeetings));
    }

    @Override
    public void onStart() {
        super.onStart();
        //TODO
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onPause() {
        super.onPause();
        //TODO
    }

    @Override
    public void onStop() {
        super.onStop();
        //TODO
    }


}
