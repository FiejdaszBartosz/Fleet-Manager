package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bfiejdasz.fleet_manager_android_app.api.ITableItem;

import java.util.List;

public class ColoredTableView extends TableView {
    public ColoredTableView(TableLayout tableLayout, List<String> columns) {
        super(tableLayout, columns);
    }

    @Override
    public void addRow(ITableItem item) {
        TableRow row = new TableRow(tableLayout.getContext());
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);

        if (item instanceof VehicleRepairStatus) {
            VehicleRepairStatus repairStatus = (VehicleRepairStatus) item;

            TextView statusColorView = new TextView(tableLayout.getContext());
            int color = repairStatus.getStatusColor();
            statusColorView.setBackgroundColor(color);
            row.addView(statusColorView);
        }

        List<String> values = item.getValues();
        for (String value : values) {
            TextView textView = new TextView(tableLayout.getContext());
            textView.setText(value);
            textView.setPadding(16, 16, 16, 16);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }
}

