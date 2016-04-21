package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Wayne on 4/20/2016.
 */
public class EnrollStudentActivityTest extends ActivityInstrumentationTestCase2<EnrollStudentActivity> {

    public EnrollStudentActivityTest() {
        super(EnrollStudentActivity.class);
    }//constructor

    public void testEnrollStudent() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);
        Spinner spnStudent = (Spinner)activity.findViewById(R.id.spnStudent);
        assertNotNull(spnStudent);
        TestUtilities.setSpinnerSelection(activity, spnClass, 1);
        instrumentation.waitForIdleSync();
        TestUtilities.setSpinnerSelection(activity, spnStudent, 1);
        instrumentation.waitForIdleSync();
        Button btnEnroll = (Button)activity.findViewById(R.id.btnEnroll);
        assertNotNull(btnEnroll);
        TestUtilities.clickButton(activity, btnEnroll);
        instrumentation.waitForIdleSync();
        assertTrue(spnClass.getSelectedItemPosition() == 0);
    }

}
