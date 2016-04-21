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
public class EditClassActivityTest extends ActivityInstrumentationTestCase2<EditClassActivity> {

    public EditClassActivityTest() {
        super(EditClassActivity.class);
    }//constructor

    public void testUpdateClass() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);
        TestUtilities.setSpinnerSelection(activity, spnClass, 1);
        instrumentation.waitForIdleSync();
        EditText txtClassId = (EditText)activity.findViewById(R.id.txtClassId);
        assertNotNull(txtClassId);
        EditText txtClassName = (EditText)activity.findViewById(R.id.txtClassName);
        assertNotNull(txtClassName);
        TestUtilities.injectText(activity, txtClassId, "C998");
        TestUtilities.injectText(activity, txtClassName, "Class 998");
        instrumentation.waitForIdleSync();
        Button btnUpdate = (Button)activity.findViewById(R.id.btnUpdate);
        assertNotNull(btnUpdate);
        TestUtilities.clickButton(activity, btnUpdate);
        instrumentation.waitForIdleSync();
        assertEquals("", txtClassName.getText().toString());
    }

    //"X" in test name helps set test class order
    public void testXDeleteClass() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);
        TestUtilities.setSpinnerSelection(activity, spnClass, 3);
        instrumentation.waitForIdleSync();
        Button btnDelete = (Button)activity.findViewById(R.id.btnDelete);
        assertNotNull(btnDelete);
        TestUtilities.clickButton(activity, btnDelete);
        instrumentation.waitForIdleSync();
        EditText txtClassName = (EditText)activity.findViewById(R.id.txtClassName);
        assertNotNull(txtClassName);
        assertEquals("", txtClassName.getText().toString());
    }

}
