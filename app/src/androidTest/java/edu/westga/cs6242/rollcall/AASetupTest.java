package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * Created by Wayne on 4/20/2016.
 */
public class AASetupTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public AASetupTest() {
        super(MainActivity.class);
    }//constructor


    //loads the default database and other tasks to setup testing
    public void testSetup() {
        Instrumentation instrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = instrumentation.addMonitor(AddStudentActivity.class.getName(), null, false);
        Activity activity = this.getActivity();
        Button button = (Button)activity.findViewById(R.id.btnLoadData);
        TestUtilities.clickButton(activity, button);
        Activity newActivity = instrumentation.waitForMonitorWithTimeout(monitor, 5000);
    }

}
