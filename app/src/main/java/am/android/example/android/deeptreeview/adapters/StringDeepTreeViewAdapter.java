package am.android.example.android.deeptreeview.adapters;

import android.app.Activity;
import android.view.View;

import java.util.List;

import am.android.example.android.deeptreeview.adapters.BaseDeepTreeViewAdapter;
import am.android.example.android.deeptreeview.holders.ViewHolder;
import am.android.example.android.deeptreeview.nodes.LeafNode;

/**
 * Created by akhlivnyuk on 3/30/2017.
 */
public class StringDeepTreeViewAdapter extends BaseDeepTreeViewAdapter<String,ViewHolder> {
    public StringDeepTreeViewAdapter(List<LeafNode<String>> rootNode, Activity activity) {
        super(rootNode, activity);
    }

    @Override
    protected ViewHolder createViewHolder(View row) {
        return new ViewHolder(row);
    }

    @Override
    protected void bind(LeafNode<String> node, ViewHolder holder) {
        super.bind(node, holder);
        holder.getTitle().setText(node.getData());
    }
}
