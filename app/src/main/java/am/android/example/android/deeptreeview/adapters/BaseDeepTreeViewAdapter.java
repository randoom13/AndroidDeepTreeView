package am.android.example.android.deeptreeview.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import am.android.example.android.deeptreeview.R;
import am.android.example.android.deeptreeview.holders.ViewHolder;
import am.android.example.android.deeptreeview.nodes.LeafNode;
import am.android.example.android.deeptreeview.nodes.listeners.NodeEventArgs;
import am.android.example.android.deeptreeview.nodes.listeners.NodeFlatListUpdateListener;

public abstract class BaseDeepTreeViewAdapter<T, H extends ViewHolder> extends BaseAdapter {
    private final Activity mActivity;
    private List<LeafNode<T>> mRootNodes;

    public BaseDeepTreeViewAdapter(List<LeafNode<T>> rootNode, Activity activity) {
        super();
        mRootNodes = rootNode;
        mActivity = activity;
    }

    protected void bind(LeafNode<T> node, H holder) {
        int arrowState = node.isExpanded() ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right;
        holder.getIcon().setIconText(mActivity.getResources().getString(arrowState));
        if (node.getCanExpanded()) {
            holder.getIcon().setVisibility(View.VISIBLE);

        } else {
            holder.getIcon().setVisibility(View.INVISIBLE);
        }
        holder.getMain().setPadding(20 * node.getLevel(), 0, 0, 0);
    }

    abstract protected H createViewHolder(View row);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null)
            row = mActivity.getLayoutInflater().inflate(R.layout.list_item, parent, false);
        H holder = (H) row.getTag();
        if (holder == null) {
            holder = createViewHolder(row);
            row.setTag(holder);
        }
        final LeafNode<T> node = (LeafNode<T>) getItem(position);
        bind(node, holder);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UpdateTreeThread(node).run();
            }
        });
        return row;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        int count = 0;
        for (LeafNode rootNode : mRootNodes) {
            count += rootNode.getFlatListSize();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        int offset = position;
        for (LeafNode rootNode : mRootNodes) {
            if (offset < rootNode.getFlatListSize())
                return rootNode.getFlatItem(offset);
            offset -= rootNode.getFlatListSize();
        }
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    void notifyVisibleItemsChanged() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }

    private class UpdateTreeThread extends Thread {
        private LeafNode<T> mNode;

        public UpdateTreeThread(LeafNode<T> node) {
            super();
            mNode = node;
        }

        @Override
        public void run() {
            if (!mNode.getCanExpanded())
                return;

            LeafNode rootNode = mNode.getTopParent();
            if (rootNode == null)
                rootNode = mNode;

            rootNode.getEventMessanger().subscribe(new NodeFlatListUpdateListener() {
                @Override
                public void nodeFlatListUpdated(Object sender, NodeEventArgs args) {
                    if (args.isFlatListChanged())
                        notifyVisibleItemsChanged();
                    args.setHandled(true);
                }
            });
            mNode.setIsExpanded(!mNode.isExpanded());
        }
    }
}
