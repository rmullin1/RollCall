package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Wayne on 4/20/2016.
 */
public class EditStudentActivityTest extends ActivityInstrumentationTestCase2<EditStudentActivity> {

    public EditStudentActivityTest() {
        super(EditStudentActivity.class);
    }//constructor

    public void testUpdateStudent() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnStudent = (Spinner)activity.findViewById(R.id.spnStudent);
        assertNotNull(spnStudent);
        TestUtilities.setSpinnerSelection(activity, spnStudent, 1);
        instrumentation.waitForIdleSync();
        EditText txtStudentId = (EditText)activity.findViewById(R.id.txtStudentId);
        assertNotNull(txtStudentId);
        EditText txtFirstName = (EditText)activity.findViewById(R.id.txtStudentFirstName);
        assertNotNull(txtFirstName);
        EditText txtLastName = (EditText)activity.findViewById(R.id.txtStudentLastName);
        assertNotNull(txtLastName);
        TestUtilities.injectText(activity, txtStudentId, "S998");
        TestUtilities.injectText(activity, txtFirstName, "First 998");
        TestUtilities.injectText(activity, txtLastName, "Last 998");
        instrumentation.waitForIdleSync();
        Button btnUpdate = (Button)activity.findViewById(R.id.btnUpdate);
        assertNotNull(btnUpdate);
        TestUtilities.clickButton(activity, btnUpdate);
        instrumentation.waitForIdleSync();
        assertEquals("", txtStudentId.getText().toString());
    }

    //"X" in test name helps set test class order
    public void testXDeleteStudent() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnStudent = (Spinner)activity.findViewById(R.id.spnStudent);
        assertNotNull(spnStudent);
        TestUtilities.setSpinnerSelection(activity, spnStudent, 3);
        instrumentation.waitForIdleSync();
        Button btnDelete = (Button)activity.findViewById(R.id.btnDelete);
        assertNotNull(btnDelete);
        TestUtilities.clickButton(activity, btnDelete);
        instrumentation.waitForIdleSync();
        EditText txtStudentId = (EditText)activity.findViewById(R.id.txtStudentId);
        assertNotNull(txtStudentId);
        assertEquals("", txtStudentId.getText().toString());
    }

}
