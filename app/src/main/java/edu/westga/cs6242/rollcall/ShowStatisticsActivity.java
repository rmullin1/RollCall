package edu.westga.cs6242.rollcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.westga.cs6242.rollcall.controller.Controller;
import edu.westga.cs6242.rollcall.model.SchoolClass;
import edu.westga.cs6242.rollcall.model.Student;

public class ShowStatisticsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Controller controller;

    private ArrayList<String> classBackingList;
    private ArrayList<SchoolClass> schoolClassList;
    private ArrayAdapter<String> classAdapter;
    private Spinner spnClass;
    private SchoolClass schoolClass;

    private ArrayList<String> studentBackingList;
    private ArrayList<Student> studentList;
    private ArrayAdapter<String> studentAdapter;
    private Spinner spnStudent;
    private Student student;

    private TextView lblNumClasses;
    private TextView lblNumStudents;
    private TextView lblNumDates;
    private TextView lblNumFilledSeats;  //total of all present attendances
    private TextView lblNumTotalSeats;   //total of all possible attnedances
    private TextView lblAverageAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_statistics);

        controller = new Controller();
        initClassControls();
        initStudentControls();

        lblNumClasses = (TextView)findViewById(R.id.lblNumClasses);
        lblNumStudents = (TextView)findViewById(R.id.lblNumStudents);
        lblNumDates = (TextView)findViewById(R.id.lblNumDates);
        lblNumFilledSeats = (TextView)findViewById(R.id.lblNumFilledSeats);
        lblNumTotalSeats = (TextView)findViewById(R.id.lblNumTotalSeats);
        lblAverageAttendance = (TextView)findViewById(R.id.lblAverageAttendance);

    }

    private void initClassControls() {
        this.spnClass = (Spinner)findViewById(R.id.spnClass);

        //load class and backing array here
        this.schoolClass = null;
        this.classBackingList = new ArrayList<String>();
        schoolClassList = controller.getSchoolClassList();
        if (schoolClassList.size() == 0)
            this.classBackingList.add("no classes");
        else {
            this.classBackingList.add("all classes");
            for (SchoolClass schoolClass : schoolClassList) {
                this.classBackingList.add(schoolClass.getClassNameEx());
            }
        }//endif

        //setup class selector
        this.spnClass.setOnItemSelectedListener(this);
        //this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, backingList);
        this.classAdapter = new ArrayAdapter<String>(this, R.layout.spinner1, classBackingList);
        this.spnClass.setAdapter(classAdapter);

    }//initClassControls()

    private void initStudentControls() {
        this.spnStudent = (Spinner)findViewById(R.id.spnStudent);

        //load student and backing array here
        this.student = null;
        this.studentBackingList = new ArrayList<String>();
        studentList = controller.getStudentList();
        if (studentList.size() == 0)
            this.studentBackingList.add("no students");
        else {
            this.studentBackingList.add("all students");
            for (Student student : studentList) {
                this.studentBackingList.add(student.getStudentNameEx());
            }
        }//endif

        //setup student selector
        this.spnStudent.setOnItemSelectedListener(this);
        //this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, backingList);
        this.studentAdapter = new ArrayAdapter<String>(this, R.layout.spinner1, studentBackingList);
        this.spnStudent.setAdapter(studentAdapter);

    }//initStudentControls()

    //this handler serves both spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
        Spinner spinner = (Spinner)parent;
        if ((Spinner)parent == this.spnClass) {
            this.schoolClass = null;
            String string = classBackingList.get(position);
            if (position > 0) {
                //first spinner position reserved for hint
                this.schoolClass = schoolClassList.get(position - 1);
            }
        } else {
            this.student = null;
            String string = studentBackingList.get(position);
            if (position > 0) {
                //first spinner position reserved for hint
                this.student = studentList.get(position - 1);
            }

        }//end else
    }//onItemSelected()

    //this handler serves both spinners
    @Override
    public void onNothingSelected(AdapterView<?> view) {
        return;
    }

    //Shows the statistics for the specified class(s) and/or specified students()
    //No selection for class or student implies ALL
    public void btnShow_onClick(View view) {
        try {
            int classNo = 0;
            int studentNo = 0;
            if (this.schoolClass != null)
                classNo = this.schoolClass.getClassNo();
            if (this.student != null) {
                studentNo = this.student.getStudentNo();
            }

            ArrayList<Integer> stats =
                    controller.doStatistics(classNo, studentNo);
            lblNumDates.setText(stats.get(0).toString());
            lblNumClasses.setText(stats.get(1).toString());
            lblNumStudents.setText(stats.get(2).toString());
            lblNumTotalSeats.setText(stats.get(3).toString());
            lblNumFilledSeats.setText(stats.get(4).toString());
            lblAverageAttendance.setText(stats.get(5).toString() + "%");

        } catch (Exception ex) {
            Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
        }
    }
}
