package edu.westga.cs6242.rollcall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
        button.setBackgroundColor(Color.RED);
        Intent intent = new Intent(this, CallRollActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "Test Info");
        startActivity(intent);
    }

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

}
