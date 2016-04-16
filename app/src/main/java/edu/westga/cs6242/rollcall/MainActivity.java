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
import edu.westga.cs6242.rollcall.model.SchoolClass;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

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
        testSpinner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void button_onClick(View view) {
        Button button = (Button)findViewById(R.id.btnButton);
        Intent intent = new Intent(this, CallRollActivity.class);
        startActivity(intent);
    }

    public void btnAddClass_onClick(View view) {
        Button button = (Button)findViewById(R.id.btnAddClass);
        Intent intent = new Intent(this, AddClassActivity.class);
        startActivity(intent);
    }//btnAddClass_onClick

    public void btnEditClass_onClick(View view) {
        Button button = (Button)findViewById(R.id.btnEditClass);
        Intent intent = new Intent(this, EditClassActivity.class);
        startActivity(intent);
    }//btnEditClass_onClick

    public void btnAddStudent_onClick(View view) {
        Button button = (Button)findViewById(R.id.btnAddStudent);
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }//btnAddStudent_onClick()

    public void btnEditStudent_onClick(View view) {
        Button button = (Button)findViewById(R.id.btnEditStudent);
        Intent intent = new Intent(this, EditStudentActivity.class);
        startActivity(intent);
    }//btnEditStudent_onClick()


    public void btnTest_onClick(View view) {
        boolean value = ShowDialog("This is a test");

        ArrayList<SchoolClass> list = controller.getSchoolClassList();
        int ll = list.size();
        int xx = 1;
    }//btnTest_onClick
    //--------------------

    private void testSpinner() {
        // Spinner element
        Spinner spnClass = (Spinner) findViewById(R.id.spnClass);

        // Spinner click listener
        spnClass.setOnItemSelectedListener(this);

        // Spinner Drop Down elements
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("");
        categories.add("Automobile");
        categories.add("Business Services");
        categories.add("Computers");
        categories.add("Education");
        categories.add("Personal");
        categories.add("Travel");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnClass.setAdapter(dataAdapter);

    }//testSpinner

    //--------- Spinner Event Handlers ------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
        ((TextView) parent.getChildAt(0)).setTextSize(5);


        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }//onItemSelected

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
        int x = 1;  //DEBUG STUB
        Toast.makeText(parent.getContext(), "Nothing Selected: ", Toast.LENGTH_LONG).show();
    }//onNothingSelected


    //---------------------------------
    //Test Dialog Alert
    boolean dialogReturnValue;
    private boolean ShowDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogReturnValue = true;
                        dialog.cancel();
                    }
                });

        builder.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialogReturnValue = false;
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder.create();
        alert11.show();
        return dialogReturnValue;
    }//ShowDialog()

}
