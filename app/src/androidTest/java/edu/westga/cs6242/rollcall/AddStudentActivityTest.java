package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Wayne on 4/20/2016.
 */
public class AddStudentActivityTest extends ActivityInstrumentationTestCase2<AddStudentActivity> {

    public AddStudentActivityTest() {
        super(AddStudentActivity.class);
    }//constructor


    //"X" in test name helps set test class order
    public void testXAddStudent() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Button btnAdd = (Button)activity.findViewById(R.id.btnAdd);
        assertNotNull(btnAdd);
        EditText txtStudentId = (EditText)activity.findViewById(R.id.txtStudentId);
        assertNotNull(txtStudentId);
        EditText txtFirstName = (EditText)activity.findViewById(R.id.txtStudentFirstName);
        assertNotNull(txtFirstName);
        EditText txtLastName = (EditText)activity.findViewById(R.id.txtStudentLastName);
        assertNotNull(txtLastName);
        TestUtilities.injectText(activity, txtStudentId, "S999");
        TestUtilities.injectText(activity, txtFirstName, "First 999");
        TestUtilities.injectText(activity, txtLastName, "Last 999");
        instrumentation.waitForIdleSync();
        TestUtilities.clickButton(activity, btnAdd);
        instrumentation.waitForIdleSync();
        assertEquals("", txtFirstName.getText().toString());
    }

}
