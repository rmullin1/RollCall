package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Wayne on 4/20/2016.
 */

//homemade utilities to use in RollCall Android testing
public class TestUtilities {

    //runs on UI thread to click the specified button for the specified activity
    public static void clickButton(Activity activity, final Button button) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.performClick();
            }
        });
    }//clickButton()

    //runs on UI thread to click the specified button for the specified activity
    public static void injectText(Activity activity, final EditText textField, final String text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textField.setText(text);
            }
        });
    }//clickButton()

    public static void setSpinnerSelection(Activity activity, final Spinner spinner, final int index) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                spinner.setSelection(index);
            }
        });
    }

}//class
