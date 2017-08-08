package am.android.example.android.deeptreeview.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import am.android.example.android.deeptreeview.nodes.listeners.NodeEventArgs;

public class TreeNode<T> extends LeafNode<T> {
    public TreeNode() {
        super();
    }

    public TreeNode(T data) {
        super(data);
    }
    public TreeNode(boolean hasEventMessanger) {
        super(hasEventMessanger);
    }

    private <B extends LeafNode<T>>  void detach(B child) {
        if (!isTop(child))
            if (child.mParent.mChildren.contains(child)) {
                child.mParent.remove(child);
            } else child.mParent = null;
    }

    private void detachAll(Collection<? extends LeafNode<T>> children) {
        List<LeafNode<T>> detachingChildren = new ArrayList<LeafNode<T>>();
        Set<LeafNode<T>> parents = new HashSet<LeafNode<T>>();
        for (LeafNode<T> child : children) {
            if (isTop(child))
                continue;

            if (!child.mParent.mChildren.contains(child)) {
                child.mParent = null;
                continue;
            }

            detachingChildren.add(child);
            parents.add(child.mParent);
        }

        for (LeafNode<T> parent : parents) {
            List<LeafNode<T>> removingChildren = new ArrayList<LeafNode<T>>();
            for (LeafNode<T> child : detachingChildren) {
                if (child.mParent == parent)
                    removingChildren.add(child);
            }

            parent.removeAll(removingChildren);
            detachingChildren.removeAll(removingChildren);
        }
    }

    public<B extends LeafNode<T>> int indexOf(B child) {
        return (mChildren.isEmpty()) ? -1 : mChildren.indexOf(child);
    }

    @Override
    public boolean getCanExpanded() {
        return true;
    }

    public void setIsExpanded(boolean isExpanded) {
        boolean isChanged = isExpanded != isExpanded();
        this.mIsExpanded = isExpanded;
        if (isChanged)
            updateFlatList();
        NodeEventArgs args = new NodeEventArgs(this);
        if (isExpanded)
            notifyExpanded(args);
        else
            notifyCollapsed(args);
    }

    @Override
    public void updateSelfFlatList() {
        mFlatList.clear();
        mFlatList.add(this);
        if (isExpanded())
            for (LeafNode<T> child : mChildren) {
                mFlatList.addAll(child.getFlatList());
            }
    }

    @Override
    public void removeAll(Collection<? extends LeafNode<T>> children) {
        mChildren.removeAll(children);
        for (LeafNode child : children) {
            child.mParent = null;
        }
        if (isExpanded())
            updateFlatList();
    }

    @Override
    public <B extends LeafNode<T>> void remove(B child) {
        mChildren.remove(child);
        child.mParent = null;
        if (isExpanded())
            updateFlatList();
    }

    public void attachAll(Collection<? extends LeafNode<T>> children) {
        mChildren.addAll(children);
        for (LeafNode child : children) {
            child.mParent = this;
        }
        if (isExpanded())
            updateFlatList();
    }

    public <B extends LeafNode<T>> void attach(B child) {
        mChildren.add(child);
        child.mParent = this;
        if (isExpanded())
            updateFlatList();
    }


    @Override
    public <B extends LeafNode<T>> void add(B child) {
        detach(child);
        attach(child);
    }

    @Override
    public void addAll(Collection<? extends LeafNode<T>> children) {
        detachAll(children);
        attachAll(children);
    }
}
