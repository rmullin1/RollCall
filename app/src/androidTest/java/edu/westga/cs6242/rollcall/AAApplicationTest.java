package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

//note:  "AA prefix controls early run test order
public class AAApplicationTest extends ApplicationTestCase<Application> {

    public AAApplicationTest() {
        super(Application.class);
    }

    public void testForActivities() {
        Activity activity;
        activity = new MainActivity();
        assertNotNull(activity);
        activity = new AddClassActivity();
        assertNotNull(activity);
        activity = new AddStudentActivity();
        assertNotNull(activity);
        activity = new CallRollActivity();
        assertNotNull(activity);
        activity = new EditClassActivity();
        assertNotNull(activity);
        activity = new EditStudentActivity();
        assertNotNull(activity);
        activity = new EnrollStudentActivity();

    }
}