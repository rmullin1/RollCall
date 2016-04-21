package edu.westga.cs6242.rollcall;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.westga.cs6242.rollcall.controller.Controller;
import edu.westga.cs6242.rollcall.model.Attendance;
import edu.westga.cs6242.rollcall.model.AttendanceLine;
import edu.westga.cs6242.rollcall.model.SchoolClass;

public class CallRollActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private ArrayList<String> classBackingList;
    private ArrayList<SchoolClass> schoolClassList;
    private ArrayAdapter<String> classAdapter;
    private SchoolClass schoolClass;

    private ListView listView;
    private ArrayList<AttendanceLine> attendanceList;
    private ArrayAdapter<AttendanceLine> attendanceListAdapter;

    private Controller controller;
    private Date dateTime;

    private Spinner spnClass;
    private CheckBox chkIsPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_roll);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        controller = new Controller();
        initControls();
    }//onCreate

    private void initControls() {

        this.spnClass = (Spinner)findViewById(R.id.spnClass);
        this.chkIsPresent = (CheckBox)findViewById(R.id.chkIsPresent);
        //load class and backing array here
        this.schoolClass = null;
        this.classBackingList = new ArrayList<String>();
        schoolClassList = controller.getSchoolClassList();
        if (schoolClassList.size() == 0)
            this.classBackingList.add("no classes");
        else {
            this.classBackingList.add("select class");
            for (SchoolClass schoolClass : schoolClassList) {
                this.classBackingList.add(schoolClass.getClassNameEx());
            }
        }//endif

        //setup class selector
        this.spnClass.setOnItemSelectedListener(this);
        this.classAdapter = new ArrayAdapter<String>(this, R.layout.spinner1, classBackingList);
        this.spnClass.setAdapter(classAdapter);

        //setup empty attendance list
        this.attendanceList = new ArrayList<AttendanceLine>();

        //setup the Date/Time of the Roll Call
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        this.dateTime = new Date();
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        txtDate.setText(dateFormatter.format(this.dateTime));
    }//initControls()

    private void updateListView() {
        this.attendanceListAdapter = new CallRollAdapter(this, this.attendanceList);
        this.listView = (ListView)findViewById(R.id.lvRoll);
        this.listView.setAdapter(this.attendanceListAdapter);
    }//updateListView();

    //check handler for the global isPresent checkbox
    public void chkIsPresent_onClick(View view) {
        boolean isChecked = false;
        CheckBox checkbox = (CheckBox)view;
        isChecked = checkbox.isChecked();
        //initialize each attendance checkbox to current isPresent value
        for (AttendanceLine al : attendanceList) {
            al.setIsPresent(isChecked);
        }
        updateListView();
    }//chkIsPresent_onClick()

    @Override
    // handler for the Class spinner onItemSelected
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
        this.schoolClass = null;
        String string = classBackingList.get(position);
        this.attendanceList = new ArrayList<AttendanceLine>();
        if (position > 0) {
            //first spinner position reserved for hint
            this.schoolClass = schoolClassList.get(position - 1);
            //CHANGE TO GET LIST FROM DB BASED ON CLASS
            //this.attendanceList = getSampleModel();
            this.attendanceList = controller.getRollListByClass(this.schoolClass.getClassNo());
        }
        updateListView();
        chkIsPresent.setChecked(false);
    }//onItemSelected (Class)

    // handler for the Class spinner onNothingSelected
    @Override
    public void onNothingSelected(AdapterView<?> view) {
        return;
    }

    //handler for the Attendance isPresent checkbox
    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
        TextView label = (TextView)view.getTag(R.id.lblName);
        CheckBox checkbox = (CheckBox)view.getTag(R.id.ispresent);

    }//onItemClick

    //handler for Save Attendance button
    public void btnSave_onClick(View view) {
        int classNo = this.schoolClass.getClassNo();
        boolean done= controller.setAttendanceRecords(this.dateTime, classNo, attendanceList);
        if (!done) {
            Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
            return;
        }
        spnClass.setSelection(0);
        Toast.makeText(this, (String) "Class Attendance has been saved!", Toast.LENGTH_SHORT).show();
    }//btnSave_onClick

}
