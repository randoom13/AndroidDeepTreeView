package am.android.example.android.deeptreeview.holders;

import android.view.View;
import android.widget.TextView;

import am.android.example.android.deeptreeview.R;

public class ViewHolder extends BaseViewHolder {
    private final TextView mTitle;

    public ViewHolder(View row) {
        super(row);
        mTitle = (TextView) row.findViewById(R.id.title);
    }

    public TextView getTitle() {
        return mTitle;
    }
}
