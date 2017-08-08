package am.android.example.android.deeptreeview.nodes;

import am.android.example.android.deeptreeview.nodes.listeners.NodeCollapsedListener;
import am.android.example.android.deeptreeview.nodes.listeners.NodeEventArgs;
import am.android.example.android.deeptreeview.nodes.listeners.NodeExpandedListener;
import am.android.example.android.deeptreeview.nodes.listeners.NodeFlatListUpdateListener;

public class NodeEventMessenger {

    private NodeCollapsedListener mNodeCollapsedListener = null;
    private NodeExpandedListener mNodeExpandedListener = null;
    private NodeFlatListUpdateListener mNodeFlatListUpdateListener = null;

    public void subscribe(NodeCollapsedListener subscriber) {
        mNodeCollapsedListener = subscriber;
    }

    public void subscribe(NodeExpandedListener listener) {
        mNodeExpandedListener = listener;
    }

    public void subscribe(NodeFlatListUpdateListener listener) {
        mNodeFlatListUpdateListener = listener;
    }

    public void unSubscribeTreeUpdateListener() {
        mNodeFlatListUpdateListener = null;
    }

    public void unSubscribeExpandedListener() {
        mNodeExpandedListener = null;
    }

    public void unSubscribeCollapsedListener() {
        mNodeCollapsedListener = null;
    }

    void notifyNodeTreeListUpdate(Object sender, NodeEventArgs args) {
        if (mNodeFlatListUpdateListener != null)
            mNodeFlatListUpdateListener.nodeFlatListUpdated(sender, args);
    }

    void notifyNodeExpanded(Object sender, NodeEventArgs args) {
        if (mNodeExpandedListener != null)
            mNodeExpandedListener.nodeExpanded(sender, args);
    }

    void notifyNodeCollapsed(Object sender, NodeEventArgs args) {
        if (mNodeCollapsedListener != null)
            mNodeCollapsedListener.nodeCollapsed(sender, args);
    }
}
