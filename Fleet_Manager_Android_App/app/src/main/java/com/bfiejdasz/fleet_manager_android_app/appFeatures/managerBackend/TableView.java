package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bfiejdasz.fleet_manager_android_app.api.ITableItem;

import java.util.List;

public class TableView {
    protected TableLayout tableLayout;
    protected List<String> columns;

    public TableView(TableLayout tableLayout, List<String> columns) {
        this.tableLayout = tableLayout;
        this.columns = columns;

        addColumnNames();
    }

    public void addRow(ITableItem item) {
        TableRow row = new TableRow(tableLayout.getContext());
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);

        for (String column : columns) {
            TextView textView = new TextView(tableLayout.getContext());
            textView.setText(item.getValues().get(columns.indexOf(column)));
            textView.setPadding(16, 16, 16, 16);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }

    protected void addColumnNames() {
        TableRow row = new TableRow(tableLayout.getContext());
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);

        for (String column : columns) {
            TextView textView = new TextView(tableLayout.getContext());
            textView.setText(column);
            textView.setPadding(16, 16, 16, 16);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }
}


