package edu.westga.cs6242.rollcall;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs6242.rollcall.model.AttendanceLine;

/**
 * Created by Wayne on 4/13/2016.
 */
public class CallRollAdapter extends ArrayAdapter<AttendanceLine> {

    private final ArrayList<AttendanceLine> list;
    private final Activity context;
    boolean checkAll = false;
    boolean checkItem = false;

    public CallRollAdapter(Activity context, ArrayList<AttendanceLine> list) {
        super(context, R.layout.attendance_line, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.attendance_line, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.name);
            viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.ispresent);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();
                    // Here we get the position that we have set for the checkbox using setTag.
                    // Set the value of checkbox to maintain its state.
                    list.get(getPosition).setIsPresent(buttonView.isChecked());
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.label, viewHolder.text);
            convertView.setTag(R.id.ispresent, viewHolder.checkbox);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.checkbox.setTag(position); // This line is important.

        viewHolder.text.setText(list.get(position).getStudentName());
        viewHolder.checkbox.setChecked(list.get(position).getIsPresent());

        return convertView;
    }//getView()

    static class ViewHolder {
        protected TextView text;
        protected CheckBox checkbox;
    }

}//class
