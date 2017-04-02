package am.android.example.android.deeptreeview.holders;

import android.view.View;
import android.widget.TextView;

import am.android.example.android.deeptreeview.R;
import am.android.example.android.deeptreeview.holders.BaseViewHolder;


/**
 * Created by alexander on 29.03.17.
 */
public class ViewHolder extends BaseViewHolder {
    public TextView getTitle() {
        return mTitle;
    }
    private final TextView mTitle;

    public ViewHolder(View row){
        super(row);
        mTitle = (TextView)row.findViewById(R.id.title);
    }
}
