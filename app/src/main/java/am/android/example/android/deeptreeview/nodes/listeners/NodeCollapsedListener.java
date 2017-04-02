package am.android.example.android.deeptreeview.nodes.listeners;

import java.util.Objects;

import am.android.example.android.deeptreeview.nodes.LeafNode;

/**
 * Created by akhlivnyuk on 3/29/2017.
 */
public interface NodeCollapsedListener {
    void nodeCollapsed(Object sender, NodeEventArgs args);
}
