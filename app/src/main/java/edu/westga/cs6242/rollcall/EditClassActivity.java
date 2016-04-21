package edu.westga.cs6242.rollcall;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
import edu.westga.cs6242.rollcall.model.SchoolClass;

public class EditClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Controller controller;

    private ArrayList<String> backingList;
    private ArrayList<SchoolClass> schoolClassList;
    private ArrayAdapter<String> adapter;
    private SchoolClass schoolClass;

    private Spinner spnClass;
    private EditText txtClassId;
    private EditText txtClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        controller = new Controller();
        initControls();
    }

    private void initControls() {

        this.spnClass = (Spinner)findViewById(R.id.spnClass);
        this.txtClassId = (EditText)findViewById(R.id.txtClassId);
        this.txtClassName = (EditText)findViewById(R.id.txtClassName);

        //load class and backing array here
        this.schoolClass = null;
        this.backingList = new ArrayList<String>();
        schoolClassList = controller.getSchoolClassList();
        if (schoolClassList.size() == 0)
            this.backingList.add("no classes");
        else {
            this.backingList.add("select class");
            for (SchoolClass schoolClass : schoolClassList) {
                this.backingList.add(schoolClass.getClassNameEx());
            }
        }//endif

        //setup class selector
        this.spnClass.setOnItemSelectedListener(this);
        //this.adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, backingList);
        this.adapter = new ArrayAdapter<String>(this, R.layout.spinner1, backingList);
        this.spnClass.setAdapter(adapter);

    }//initControls()

    private void loadClassControls() {
        txtClassId.setText("");
        txtClassName.setText("");
        if (this.schoolClass != null) {
            txtClassId.setText(schoolClass.getClassId());
            txtClassName.setText(schoolClass.getClassName());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id ) {
        this.schoolClass = null;
        String string = backingList.get(position);
        if (position > 0) {
            //first spinner position reserved for hint
            this.schoolClass = schoolClassList.get(position - 1);
        }
        loadClassControls();
    }

    @Override
    public void onNothingSelected(AdapterView<?> view) {
        return;
    }

    public void btnUpdate_onClick(View view) {
        try {
            if (this.schoolClass == null) {
                Toast.makeText(this, "A Class must be selected!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            int classNo = this.schoolClass.getClassNo();
            String classId = txtClassId.getText().toString();
            String className = txtClassName.getText().toString();

            //validate fields
            if (classId.length() == 0) {
                txtClassId.setError("Required Field!");
                return;
            }
            if (className.length() == 0) {
                txtClassName.setError("Required Field!");
                return;
            }

            //verify either this is the existing classId or a new unique class
            if (!controller.verifyUniqueClassId(classId, classNo)) {
                txtClassId.setError("Class Id must be Unique");
                return;
            }
            boolean done = controller.updateClass(classNo, classId, className);
            if (!done) {
                Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
                return;
            }
            initControls();
            loadClassControls();
            Toast.makeText(this, (String) "Class has been Updated!", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            Toast.makeText(this, "Sorry an unexpected error occured!",
                    Toast.LENGTH_SHORT).show();
        }
    }//btnUpdate_onClick

    public void btnDelete_onClick(View view) {
        try {
            if (this.schoolClass == null) {
                Toast.makeText(this, "A Class must be selected!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            int classNo = this.schoolClass.getClassNo();
            boolean done = controller.deleteClass(classNo);
            if (!done) {
                Toast.makeText(this, (String) "An unexpected error has occured!", Toast.LENGTH_SHORT).show();
                return;
            }
            initControls();
            loadClassControls();
            Toast.makeText(this, (String) "Class has been Deleted!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            Toast.makeText(this, "Sorry an unexpected error occured.",
                    Toast.LENGTH_SHORT).show();
        }
    }//btnDelete_onClick

}//class
