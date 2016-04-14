package edu.westga.cs6242.rollcall.controller;

import edu.westga.cs6242.rollcall.dbaccess.*;
import edu.westga.cs6242.rollcall.model.*;

/**
 * Class Controller
 *      A controller for the RollCall application
 */
public class Controller {

    private DbHandler dbHandler;
    private DbAccess dbAccess;

    public Controller() {
        //create db access models
        this.dbHandler = new DbHandler(App.getContext());
        this.dbAccess = new DbAccess();
    }

    public DbHandler getDbHandler() {
        return this.dbHandler;
    }

    public DbAccess getDbAccess() {
        return this.dbAccess;
    }

}//class
