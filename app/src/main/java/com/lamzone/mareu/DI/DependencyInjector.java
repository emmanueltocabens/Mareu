package com.lamzone.mareu.DI;

import com.lamzone.mareu.service.FakeMareuApiService;
import com.lamzone.mareu.service.MareuApiService;

public class DependencyInjector {

    private static MareuApiService service = new FakeMareuApiService();

    /**
     * returns the current apiService
     * @return current MareuApiService instance
     */
    public static MareuApiService getMareuApiService(){
        return service;
    }

    /**
     * returns a new instance of apiService (used for tests)
     * @return new MareuApiService instance
     */
    public static MareuApiService getNewInstanceApiService(){
        service = new FakeMareuApiService();
        return service;
    }

}
