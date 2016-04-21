package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Wayne on 4/20/2016.
 */
public class AddClassActivityTest extends ActivityInstrumentationTestCase2<AddClassActivity> {

    public AddClassActivityTest() {
        super(AddClassActivity.class);
    }//constructor


    //"X" in test name helps set test class order
    public void testXAddClass() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Button btnAdd = (Button)activity.findViewById(R.id.btnAdd);
        assertNotNull(btnAdd);
        EditText txtClassId = (EditText)activity.findViewById(R.id.txtClassId);
        assertNotNull(txtClassId);
        EditText txtClassName = (EditText)activity.findViewById(R.id.txtClassName);
        assertNotNull(txtClassName);
        TestUtilities.injectText(activity, txtClassId, "C999");
        TestUtilities.injectText(activity, txtClassName, "Class 999");
        instrumentation.waitForIdleSync();
        TestUtilities.clickButton(activity, btnAdd);
        instrumentation.waitForIdleSync();
        assertEquals("", txtClassName.getText().toString());
    }

}
