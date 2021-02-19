package com.example.dong_sapanta;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TeamRosterAdapter extends ArrayAdapter<Roster> {
    Context _context;
    public TeamRosterAdapter(Context context, ArrayList<Roster> roster) {
        super(context, 0, roster);
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Activity activity = (Activity) _context;
        // Get the data item for this position
        Roster roster = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }

        // Lookup view for data population
        TextView tvRosterName = convertView.findViewById(R.id.name);

        // Populate the data into the template view using the data object
        tvRosterName.setText(roster.getFullName());

        // Return the completed view to render on screen
        return convertView;
    }
}
