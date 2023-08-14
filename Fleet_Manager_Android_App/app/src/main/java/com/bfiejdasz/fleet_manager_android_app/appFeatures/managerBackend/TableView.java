package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bfiejdasz.fleet_manager_android_app.R;
import com.bfiejdasz.fleet_manager_android_app.api.ITableItem;

import java.util.List;

public class TableView {
    protected TableLayout tableLayout;
    protected List<String> columns;
    protected boolean isWhiteBackground = true;

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

        int backgroundColor = isWhiteBackground ? ContextCompat.getColor(tableLayout.getContext(), R.color.colorOnPrimary) : ContextCompat.getColor(tableLayout.getContext(), R.color.primary);
        int textColor = isWhiteBackground ? ContextCompat.getColor(tableLayout.getContext(), R.color.primary) : ContextCompat.getColor(tableLayout.getContext(), R.color.colorOnPrimary);
        row.setBackgroundColor(backgroundColor);

        isWhiteBackground = !isWhiteBackground;

        for (String column : columns) {
            TextView textView = new TextView(tableLayout.getContext());
            textView.setText(item.getValues().get(columns.indexOf(column)));
            textView.setPadding(50, 50, 50, 50);
            textView.setTextAppearance(R.style.ClashDisplayFontMediumStyle);
            textView.setTextColor(textColor);
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
            textView.setPadding(50, 50, 50, 50);
            textView.setTextAppearance(R.style.ClashDisplayFontMediumStyle);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }
}
