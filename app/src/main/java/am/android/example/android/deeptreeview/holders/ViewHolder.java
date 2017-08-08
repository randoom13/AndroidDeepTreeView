package am.android.example.android.deeptreeview.holders;

import android.view.View;
import android.widget.TextView;

import am.android.example.android.deeptreeview.R;

public class ViewHolder extends BaseViewHolder {
    public final TextView title;

    public ViewHolder(View row) {
        super(row);
        title = (TextView) row.findViewById(R.id.title);
    }
}
