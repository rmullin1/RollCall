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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.westga.cs6242.rollcall.model.AttendanceLine;

public class CallRollActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<AttendanceLine> list;
    ArrayAdapter<AttendanceLine> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_roll);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.listView = (ListView)findViewById(R.id.lvRoll);
        //getModel() must get an ArrayList<AttendanceLine>
        this.listAdapter = new CallRollAdapter(this, getModel());
        this.listView.setAdapter(this.listAdapter);
        this.listView.setOnItemClickListener(this);
    }//onCreate

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
        TextView label = (TextView)view.getTag(R.id.name);
        CheckBox checkbox = (CheckBox)view.getTag(R.id.ispresent);
    }//onItemClick

    private ArrayList<AttendanceLine> getModel() {
        list = new ArrayList<AttendanceLine>();
        list.add(new AttendanceLine("Linux"));
        list.add(new AttendanceLine("Windows7"));
        list.add(new AttendanceLine("Suse"));
        list.add(new AttendanceLine("Eclipse"));
        list.add(new AttendanceLine("Ubuntu"));
        list.add(new AttendanceLine("Solaris"));
        list.add(new AttendanceLine("Android"));
        list.add(new AttendanceLine("iPhone"));
        list.add(new AttendanceLine("Java"));
        list.add(new AttendanceLine(".Net"));
        list.add(new AttendanceLine("PHP"));
        return list;
    }//getModel()
}
