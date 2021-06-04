package com.lamzone.mareu.DI;

import com.lamzone.mareu.service.FakeMareuApiService;
import com.lamzone.mareu.service.MareuApiService;

public class DependencyInjector {

    private static MareuApiService service = new FakeMareuApiService();

    /**
     * returns the current apiService
     * @return
     */
    public static MareuApiService getMareuApiService(){
        return service;
    }

    /**
     * returns a new instance of apiService (test purpose)
     * @return
     */
    public static MareuApiService getNewInstanceApiService(){
        return new FakeMareuApiService();
    }

}
