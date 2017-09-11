package am.android.example.android.deeptreeview.holders;

import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;

import am.android.example.android.deeptreeview.R;

public class ViewHolder {
    public final TextView title;
    public final View main;
    public final PrintView icon;

    public ViewHolder(View row) {
        icon = (PrintView) row.findViewById(R.id.icon);
        main = row.findViewById(R.id.main);
        title = (TextView) row.findViewById(R.id.title);
    }
}
