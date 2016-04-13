package edu.westga.cs6242.rollcall;

import android.app.Application;
import android.content.Context;
import android.test.ApplicationTestCase;
import edu.westga.cs6242.rollcall.dbaccess.DbHandler;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }
}