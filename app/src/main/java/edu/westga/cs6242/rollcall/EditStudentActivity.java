package edu.westga.cs6242.rollcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import edu.westga.cs6242.rollcall.controller.Controller;
import edu.westga.cs6242.rollcall.model.Student;

public class EditStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Controller controller;

    private ArrayList<String> backingList;
    private ArrayList<Student> studentList;
    private ArrayAdapter<String> adapter;
    private Student student;

    private Spinner spnStudent;
    private EditText txtStudentId;
    private EditText txtStudentFirstName;
    private EditText txtStudentLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        controller = new Controller();
        initControls();
    }

    private void initControls() {

        this.spnStudent = (Spinner)findViewById(R.id.spnStudent);
        this.txtStudentId = (EditText)findViewById(R.id.txtStudentId);
        this.txtStudentFirstName = (EditText)findViewById(R.id.txtStudentFirstName);
        this.txtStudentLastName = (EditText)findViewById(R.id.txtStudentLastName);

        //load student and backing array here
        this.student = null;
        this.backingList = new ArrayList<String>();
        studentList = controller.getStudentList();
        if (studentList.size() == 0)
            this.backingList.add("no students");
        else {
            this.backingList.add("select student");
            for (Student student : studentList) {
                this.backingList.add(student.getStudentNameEx());
            }
        }//endif

        //setup student selector
        this.spnStudent.setOnItemSelectedListener(this);
        //this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, backingList);
        this.adapter = new ArrayAdapter<String>(this, R.layout.spinner1, backingList);
        this.spnStudent.setAdapter(adapter);

    }//initControls()

    private void loadStudentControls() {
        txtStudentId.setText("");
        txtStudentFirstName.setText("");
        txtStudentLastName.setText("");
        if (this.student != null) {
            txtStudentId.setText(student.getStudentId());
            txtStudentFirstName.setText(student.getFirstName());
            txtStudentLastName.setText(student.getLastName());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
        this.student = null;
        String string = backingList.get(position);
        if (position > 0) {
            //first spinner position reserved for hint
            this.student = studentList.get(position - 1);
        }
        loadStudentControls();
    }

    @Override
    public void onNothingSelected(AdapterView<?> view) {
        return;
    }

    public void btnUpdate_onClick(View view) {
        try {
            if (this.student == null) {
                Toast.makeText(this, "A Student must be selected!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            //verify either this is the existing studentId or a new unique student
            int studentNo = this.student.getStudentNo();
            String studentId = txtStudentId.getText().toString();
            String studentFirstName = txtStudentFirstName.getText().toString();
            String studentLastName = txtStudentLastName.getText().toString();

            //validate fields
            if (studentId.length() == 0) {
                txtStudentId.setError("Required Field!");
                return;
            }
            if (studentFirstName.length() == 0) {
                txtStudentFirstName.setError("Required Field!");
                return;
            }
            if (studentFirstName.length() == 0) {
                txtStudentFirstName.setError("Required Field!");
                return;
            }

            //verify either this is the existing StudentId or a new unique student id
            if (!controller.verifyUniqueStudentId(studentId, studentNo)) {
                txtStudentId.setError("Student Id must be Unique");
                return;
            }
            boolean done = controller.updateStudent(studentNo, studentId, studentFirstName, studentLastName);
            if (!done) {
                Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
                return;
            }
            initControls();
            loadStudentControls();
            Toast.makeText(this, (String) "Student has been Updated!", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, "Sorry an unexpected error occured.",
                    Toast.LENGTH_SHORT).show();
        }
    }//btnUpdate_onClick

    public void btnDelete_onClick(View view) {
        try {
            if (this.student == null) {
                Toast.makeText(this, "A Student must be selected!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            int studentNo = this.student.getStudentNo();
            boolean done = controller.deleteStudent(studentNo);
            if (!done) {
                Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
                return;
            }
            initControls();
            loadStudentControls();
            Toast.makeText(this, (String) "Student has been deleted!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            Toast.makeText(this, "Sorry an unexpected error occured.",
                    Toast.LENGTH_SHORT).show();
        }
    }//btnDelete_onClick

}
