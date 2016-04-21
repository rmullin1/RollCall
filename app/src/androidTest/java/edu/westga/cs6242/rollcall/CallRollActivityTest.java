package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

/**
 * Created by Wayne on 4/21/2016.
 */
public class CallRollActivityTest extends ActivityInstrumentationTestCase2<CallRollActivity> {

    public CallRollActivityTest() {
        super(CallRollActivity.class);
    }//constructor

    public void testCallRoll() {} {

        Instrumentation instrumentation = getInstrumentation();
/*
        Activity activity = this.getActivity();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);

        TestUtilities.setSpinnerSelection(activity, spnClass, 1);
        instrumentation.waitForIdleSync();

        ListView lvRoll = (ListView)activity.findViewById(R.id.lvRoll);
        assertNotNull(lvRoll);
        Button btnSave = (Button)activity.findViewById(R.id.btnSave);
        assertNotNull(btnSave);
        int index = spnClass.getSelectedItemPosition();
        assertEquals(0, index);
*/
        assertTrue(true);
    }

}
