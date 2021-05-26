package com.lamzone.mareu.utils;

import android.app.Application;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.service.MareuApiService;

public class MareuApplication extends Application {

    private MareuApiService apiService;

    public MareuApiService getApiService(){
        if (apiService == null) apiService = DependencyInjector.getNewInstanceApiService();
        return apiService;
    }
}
