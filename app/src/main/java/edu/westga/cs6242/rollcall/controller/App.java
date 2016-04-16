package edu.westga.cs6242.rollcall.controller;

import android.app.Application;
import android.content.Context;

/**
 * Class App
 *      This class provides one of several ways to share information between the View and the Model,
 * in this case, the Context which is required to access any resources under /res or /assets.
 * This is similar to the Application class static methods in C#.
 */

//define a class to extend Application so it gets the Context
public class App extends Application {

    private static Context _context;

    /**
     * record the context in onCreate() and make it statically available.
     * Note: this is triggered only for live and instrumentation testing
     */
    @Override
    public void onCreate() {
        super.onCreate();
        _context = this;
    }//onCreate

    //static access routine to Context
    public static Context getContext() {
        return _context;
    }//getContext()

}//class App
