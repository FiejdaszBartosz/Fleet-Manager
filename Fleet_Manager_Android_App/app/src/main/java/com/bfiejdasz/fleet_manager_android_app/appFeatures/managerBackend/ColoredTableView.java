package com.bfiejdasz.fleet_manager_android_app.appFeatures.managerBackend;

import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bfiejdasz.fleet_manager_android_app.R;
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
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);

        if (item instanceof VehicleRepairStatus) {
            VehicleRepairStatus repairStatus = (VehicleRepairStatus) item;
            int color = repairStatus.getStatusColor();

            ImageView circleView = new ImageView(tableLayout.getContext());
            circleView.setImageResource(R.drawable.status_circle);
            circleView.setColorFilter(color);
            circleView.setPadding(30, 30, 30, 30);
            row.addView(circleView);
        }

        int backgroundColor = isWhiteBackground ? ContextCompat.getColor(tableLayout.getContext(), R.color.colorOnPrimary) : ContextCompat.getColor(tableLayout.getContext(), R.color.primary);
        int textColor = isWhiteBackground ? ContextCompat.getColor(tableLayout.getContext(), R.color.primary) : ContextCompat.getColor(tableLayout.getContext(), R.color.colorOnPrimary);
        row.setBackgroundColor(backgroundColor);
        isWhiteBackground = !isWhiteBackground;

        List<String> values = item.getValues();
        for (String value : values) {
            TextView textView = new TextView(tableLayout.getContext());
            textView.setText(value);
            textView.setPadding(50, 50, 50, 50);
            textView.setGravity(Gravity.CENTER);
            textView.setTextAppearance(R.style.ClashDisplayFontMediumStyle);
            textView.setTextColor(textColor);
            row.addView(textView);
        }

        tableLayout.addView(row);
    }
}
