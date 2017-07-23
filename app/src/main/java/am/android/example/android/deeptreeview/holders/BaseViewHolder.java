package am.android.example.android.deeptreeview.holders;

import android.view.View;

import com.github.johnkil.print.PrintView;

import am.android.example.android.deeptreeview.R;

public class BaseViewHolder {
    private final View mMain;
    private final PrintView mIcon;

    public BaseViewHolder(View row) {
        mIcon = (PrintView) row.findViewById(R.id.icon);
        mMain = row.findViewById(R.id.main);
    }

    public PrintView getIcon() {
        return mIcon;
    }

    public View getMain() {
        return mMain;
    }
}
