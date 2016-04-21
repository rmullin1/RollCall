package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.View;
import android.widget.Button;

/**
 * Created by Wayne on 4/19/2016.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>  {

    public MainActivityTest() {
        super(MainActivity.class);
    }//constructor


    public void testSelectAddClass() {

        Instrumentation instrumentation = getInstrumentation();
        ActivityMonitor monitor = instrumentation.addMonitor(AddClassActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        final Button button = (Button)activity.findViewById(R.id.btnAddClass);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
        assertEquals("AddClassActivity", newActivity.getLocalClassName());
        newActivity.finish();
    }//testSelectAddClass()

    public void testSelectEditClass() {

        Instrumentation instrumentation = getInstrumentation();
        ActivityMonitor monitor = instrumentation.addMonitor(EditClassActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        Button button = (Button)activity.findViewById(R.id.btnEditClass);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
        assertEquals("EditClassActivity", newActivity.getLocalClassName());
        newActivity.finish();
    }//testSelectEditClass()

    public void testSelectAddStudent() {

        Instrumentation instrumentation = getInstrumentation();
        ActivityMonitor monitor = instrumentation.addMonitor(AddStudentActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        Button button = (Button)activity.findViewById(R.id.btnAddStudent);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
        assertEquals("AddStudentActivity", newActivity.getLocalClassName());
        newActivity.finish();
    }//testSelectAddStudent()

    public void testSelectEditStudent() {

        Instrumentation instrumentation = getInstrumentation();
        ActivityMonitor monitor = instrumentation.addMonitor(EditStudentActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        Button button = (Button)activity.findViewById(R.id.btnEditStudent);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
        assertEquals("EditStudentActivity", newActivity.getLocalClassName());
        newActivity.finish();
    }//testSelectEditStudent()

    public void testSelectEnrollStudent() {

        Instrumentation instrumentation = getInstrumentation();
        ActivityMonitor monitor = instrumentation.addMonitor(EnrollStudentActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        Button button = (Button)activity.findViewById(R.id.btnEnroll);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
        assertEquals("EnrollStudentActivity", newActivity.getLocalClassName());
        newActivity.finish();
    }//testSelectEnrollStudent()

    public void testSelectCallRoll() {

        Instrumentation instrumentation = getInstrumentation();
        ActivityMonitor monitor = instrumentation.addMonitor(CallRollActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        Button button = (Button)activity.findViewById(R.id.btnCallRoll);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
        assertEquals("CallRollActivity", newActivity.getLocalClassName());
        assertNotNull(newActivity);
        newActivity.finish();
    }//testSelectCallRoll()

}//test class
