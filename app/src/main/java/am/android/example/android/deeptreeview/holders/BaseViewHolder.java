package am.android.example.android.deeptreeview.holders;

import android.view.View;

import com.github.johnkil.print.PrintView;

import am.android.example.android.deeptreeview.R;

public class BaseViewHolder {
    public final View main;
    public final PrintView icon;

    public BaseViewHolder(View row) {
        icon = (PrintView) row.findViewById(R.id.icon);
        main = row.findViewById(R.id.main);
    }
}
