package edu.westga.cs6242.rollcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.westga.cs6242.rollcall.controller.Controller;

public class AddStudentActivity extends AppCompatActivity {

    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        controller = new Controller();
    }

    public void btnAdd_onClick(View view) {
        //see if all fields are filled
        EditText txtStudentId = (EditText)findViewById(R.id.txtStudentId);
        EditText txtStudentFirstName = (EditText)findViewById(R.id.txtStudentFirstName);
        EditText txtStudentLastName = (EditText)findViewById(R.id.txtStudentLastName);
        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        String studentId = txtStudentId.getText().toString();
        String studentFirstName = txtStudentFirstName.getText().toString();
        String studentLastName = txtStudentLastName.getText().toString();

        if (studentId.length() == 0) {
            txtStudentId.setError("Required Field!");
            return;
        }
        if (studentFirstName.length() == 0) {
            txtStudentFirstName.setError("Required Field!");
            return;
        }
        if (studentLastName.length() == 0) {
            txtStudentLastName.setError("Required Field!");
            return;
        }

        //see if student id exists, if so fail
        if (!controller.verifyUniqueStudentId(studentId)) {
            txtStudentId.setError("Student Id Must be Unique!");
            return;
        }
        //call dbaccess to add student
        int studentNo = controller.addNewStudent(studentId, studentFirstName, studentLastName);
        if (studentNo <= 0) {
            btnAdd.setError("Error. Student could not be added.");
            return;
        } else {
            txtStudentId.setText("");
            txtStudentFirstName.setText("");
            txtStudentLastName.setText("");
        }

    }//btnAdd_onClick

}
