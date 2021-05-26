package com.lamzone.mareu.utils;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.lamzone.mareu.service.MareuApiService;
import com.lamzone.mareu.ui.MareuListActivity;

public class BaseActivity extends MareuListActivity {

    public MareuApiService getApiService(){
        return ((MareuApplication) getApplication()).getApiService();
    }
}
