package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Wayne Mullins on 4/26/2016.
 */
public class ShowStatisticsActivityTest extends ActivityInstrumentationTestCase2<ShowStatisticsActivity> {

    public ShowStatisticsActivityTest() {
        super(ShowStatisticsActivity.class);
    }//constructor

    //test default statistics
    public void testGeneralStatistics() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);
        Spinner spnStudent = (Spinner)activity.findViewById(R.id.spnStudent);
        assertNotNull(spnStudent);

        TextView lblNumClasses = (TextView)activity.findViewById(R.id.lblNumClasses);
        assertNotNull(lblNumClasses);
        TextView lblNumStudents = (TextView)activity.findViewById(R.id.lblNumStudents);
        assertNotNull(lblNumStudents);
        TextView lblNumFilledSeats = (TextView)activity.findViewById(R.id.lblNumFilledSeats);
        assertNotNull(lblNumFilledSeats);
        TextView lblNumTotalSeats = (TextView)activity.findViewById(R.id.lblNumTotalSeats);
        assertNotNull(lblNumTotalSeats);
        TextView lblNumDates = (TextView)activity.findViewById(R.id.lblNumDates);
        assertNotNull(lblNumDates);
        TextView lblAverage = (TextView)activity.findViewById(R.id.lblAverageAttendance);
        assertNotNull(lblAverage);

        //use default class and student spinner values

        Button btnShow = (Button)activity.findViewById(R.id.btnShow);
        assertNotNull(btnShow);
        TestUtilities.clickButton(activity, btnShow);
        instrumentation.waitForIdleSync();

        assertEquals("3", lblNumClasses.getText());
        assertEquals("10", lblNumStudents.getText());
        assertEquals("4", lblNumDates.getText());
        assertEquals("49", lblNumFilledSeats.getText());
        assertEquals("56", lblNumTotalSeats.getText());
        assertEquals("87%", lblAverage.getText());
    }

    //test statistics for Class 1
    public void testClassSpecificStatistics() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);
        Spinner spnStudent = (Spinner)activity.findViewById(R.id.spnStudent);
        assertNotNull(spnStudent);

        TextView lblNumClasses = (TextView)activity.findViewById(R.id.lblNumClasses);
        assertNotNull(lblNumClasses);
        TextView lblNumStudents = (TextView)activity.findViewById(R.id.lblNumStudents);
        assertNotNull(lblNumStudents);
        TextView lblNumFilledSeats = (TextView)activity.findViewById(R.id.lblNumFilledSeats);
        assertNotNull(lblNumFilledSeats);
        TextView lblNumTotalSeats = (TextView)activity.findViewById(R.id.lblNumTotalSeats);
        assertNotNull(lblNumTotalSeats);
        TextView lblNumDates = (TextView)activity.findViewById(R.id.lblNumDates);
        assertNotNull(lblNumDates);
        TextView lblAverage = (TextView)activity.findViewById(R.id.lblAverageAttendance);
        assertNotNull(lblAverage);

        //set specific class 1 and default/all student spinner values
        TestUtilities.setSpinnerSelection(activity, spnClass, 1);
        instrumentation.waitForIdleSync();
        TestUtilities.setSpinnerSelection(activity, spnStudent, 0);
        instrumentation.waitForIdleSync();

        Button btnShow = (Button)activity.findViewById(R.id.btnShow);
        assertNotNull(btnShow);
        TestUtilities.clickButton(activity, btnShow);
        instrumentation.waitForIdleSync();

        assertEquals("1", lblNumClasses.getText());
        assertEquals("5", lblNumStudents.getText());
        assertEquals("4", lblNumDates.getText());
        assertEquals("16", lblNumFilledSeats.getText());
        assertEquals("20", lblNumTotalSeats.getText());
        assertEquals("80%", lblAverage.getText());
    }

    //test statistics for Student 1
    public void testStudentSpecificStatistics() {
        Instrumentation instrumentation = getInstrumentation();
        Activity activity = this.getActivity();
        Spinner spnClass = (Spinner)activity.findViewById(R.id.spnClass);
        assertNotNull(spnClass);
        Spinner spnStudent = (Spinner)activity.findViewById(R.id.spnStudent);
        assertNotNull(spnStudent);

        TextView lblNumClasses = (TextView)activity.findViewById(R.id.lblNumClasses);
        assertNotNull(lblNumClasses);
        TextView lblNumStudents = (TextView)activity.findViewById(R.id.lblNumStudents);
        assertNotNull(lblNumStudents);
        TextView lblNumFilledSeats = (TextView)activity.findViewById(R.id.lblNumFilledSeats);
        assertNotNull(lblNumFilledSeats);
        TextView lblNumTotalSeats = (TextView)activity.findViewById(R.id.lblNumTotalSeats);
        assertNotNull(lblNumTotalSeats);
        TextView lblNumDates = (TextView)activity.findViewById(R.id.lblNumDates);
        assertNotNull(lblNumDates);
        TextView lblAverage = (TextView)activity.findViewById(R.id.lblAverageAttendance);
        assertNotNull(lblAverage);

        //set specific default/all class and student 5 spinner values
        TestUtilities.setSpinnerSelection(activity, spnClass, 0);
        instrumentation.waitForIdleSync();
        TestUtilities.setSpinnerSelection(activity, spnStudent, 5);
        instrumentation.waitForIdleSync();

        Button btnShow = (Button)activity.findViewById(R.id.btnShow);
        assertNotNull(btnShow);
        TestUtilities.clickButton(activity, btnShow);
        instrumentation.waitForIdleSync();

        assertEquals("2", lblNumClasses.getText());
        assertEquals("1", lblNumStudents.getText());
        assertEquals("4", lblNumDates.getText());
        assertEquals("6", lblNumFilledSeats.getText());
        assertEquals("8", lblNumTotalSeats.getText());
        assertEquals("75%", lblAverage.getText());


    }

}
