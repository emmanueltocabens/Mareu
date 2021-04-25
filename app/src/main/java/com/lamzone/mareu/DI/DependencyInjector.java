package com.lamzone.mareu.DI;

import com.lamzone.mareu.service.FakeMareuApiService;
import com.lamzone.mareu.service.MareuApiService;

public class DependencyInjector {

    private static MareuApiService service = new FakeMareuApiService();

    public static MareuApiService getMareuApiService(){
        return service;
    }

    public static MareuApiService getNewInstanceApiService(){
        return new FakeMareuApiService();
    }

}
