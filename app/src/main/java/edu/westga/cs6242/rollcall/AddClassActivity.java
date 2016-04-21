package edu.westga.cs6242.rollcall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.westga.cs6242.rollcall.controller.Controller;

public class AddClassActivity extends AppCompatActivity {

    Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        controller = new Controller();
    }

    public void btnAdd_onClick(View view) {
        //see if all fields are filled
        EditText txtClassId = (EditText)findViewById(R.id.txtClassId);
        EditText txtClassName = (EditText)findViewById(R.id.txtClassName);
        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        String classId = txtClassId.getText().toString();
        String className = txtClassName.getText().toString();

        if (classId.length() == 0) {
            txtClassId.setError("Required Field!");
            return;
        }
        if (className.length() == 0) {
            txtClassName.setError("Required Field!");
            return;
        }

        //see if class id exists, if so fail
        if (!controller.verifyUniqueClassId(classId)) {
            txtClassId.setError("Class Id Must be Unique!");
            return;
        }
        //call dbaccess to add class
        int classNo = controller.addNewClass(classId, className);
        if (classNo <= 0) {
            Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            txtClassId.setText("");
            txtClassName.setText("");
        }
        Toast.makeText(this, (String) "New Class has been Added!", Toast.LENGTH_SHORT).show();

    }//btnAdd_onClick

}//class
