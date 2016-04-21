package edu.westga.cs6242.rollcall;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.westga.cs6242.rollcall.controller.Controller;
import edu.westga.cs6242.rollcall.model.Enrollment;
import edu.westga.cs6242.rollcall.model.SchoolClass;

public class MainActivity extends AppCompatActivity  {

    public final static String EXTRA_MESSAGE = "TOKEN";
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create controller
        controller = new Controller();
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void btnCallRoll_onClick(View view) {
        Intent intent = new Intent(this, CallRollActivity.class);
        startActivity(intent);
    }

    public void btnEnrollStudent_onClick(View view) {
        Intent intent = new Intent(this, EnrollStudentActivity.class);
        startActivity(intent);
    }

    public void btnAddClass_onClick(View view) {
        Intent intent = new Intent(this, AddClassActivity.class);
        startActivity(intent);
    }//btnAddClass_onClick

    public void btnEditClass_onClick(View view) {
        Intent intent = new Intent(this, EditClassActivity.class);
        startActivity(intent);
    }//btnEditClass_onClick

    public void btnAddStudent_onClick(View view) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }//btnAddStudent_onClick

    public void btnEditStudent_onClick(View view) {
        Intent intent = new Intent(this, EditStudentActivity.class);
        startActivity(intent);
    }//btnEditStudent_onClick


    public void btnLoadData_onClick(View view) {
        controller.deleteDatabase();
        controller.loadDemoData();
        Toast.makeText(this, (String) "Demo Data has been Loaded!", Toast.LENGTH_SHORT).show();
    }//btnLoadData_onClick

    public void btnTest_onClick(View view) {
        //boolean value = ShowDialog("This is a test");

        ArrayList<SchoolClass> list = controller.getSchoolClassList();
        //int ll = list.size();
        ArrayList<Enrollment> elist = controller.getEnrollmentList();
        controller.deleteEnrollment(3, 3);
        ArrayList<Enrollment> elist2 = controller.getEnrollmentList();
        controller.addNewEnrollment(3, 3);
        ArrayList<Enrollment> elist3 = controller.getEnrollmentList();

        int xx = 1;
    }//btnTest_onClick

}
