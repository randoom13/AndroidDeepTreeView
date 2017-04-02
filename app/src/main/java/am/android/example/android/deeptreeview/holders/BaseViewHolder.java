package am.android.example.android.deeptreeview.holders;

import android.view.View;
import android.widget.TextView;

import com.github.johnkil.print.PrintView;

import am.android.example.android.deeptreeview.R;

/**
 * Created by akhlivnyuk on 3/30/2017.
 */
public class BaseViewHolder {
    public PrintView getIcon() {
        return mIcon;
    }

    public View getMain() {
        return mMain;
    }

    private final View mMain;
    private final PrintView mIcon;
    public BaseViewHolder(View row){
        mIcon = (PrintView) row.findViewById(R.id.icon);
        mMain = row.findViewById(R.id.main);
    }
}
