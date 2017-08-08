package am.android.example.android.deeptreeview.adapters;

import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import am.android.example.android.deeptreeview.holders.ViewHolder;
import am.android.example.android.deeptreeview.nodes.LeafNode;

public class StringDeepTreeViewAdapter extends BaseDeepTreeViewAdapter<String, ViewHolder> {
    public StringDeepTreeViewAdapter(List<LeafNode<String>> rootNode, LayoutInflater inflater) {
        super(rootNode, inflater);
    }

    @Override
    protected ViewHolder createViewHolder(View row) {
        return new ViewHolder(row);
    }

    @Override
    protected void bind(LeafNode<String> node, ViewHolder holder) {
        super.bind(node, holder);
        holder.title.setText(node.getData());
    }
}
