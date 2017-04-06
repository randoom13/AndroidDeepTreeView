package am.android.example.android.deeptreeview.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import am.android.example.android.deeptreeview.nodes.listeners.NodeEventArgs;

/**
 * Created by akhlivnyuk on 3/27/2017.
 */
public class LeafNode<T> {
    public T getData() {
        return mData;
    }

    public void setData(T data) {
        this.mData = data;
    }

    private T mData;
    protected List<LeafNode<T>> mFlatList = new ArrayList<LeafNode<T>>();
    private NodeEventMessanger mEventMessanger;

    protected LeafNode<T> mParent;
    protected List<LeafNode<T>> mChildren = new ArrayList<LeafNode<T>>();

    protected boolean mIsExpanded = false;
    private boolean mAutoUpdate = true;

    public LeafNode(boolean hasEventMessenger) {
        mParent = null;
        updateSelfFlatList();
        if (hasEventMessenger)
            mEventMessanger = new NodeEventMessanger();
    }

    public LeafNode() {
        this(true);
    }

    static boolean isTop(LeafNode baseNode) {
        return baseNode.mParent == null;
    }

    public NodeEventMessanger getEventMessanger() {
        return mEventMessanger;
    }

    void notifyExpanded(NodeEventArgs args) {
        if (mEventMessanger !=null)
            mEventMessanger.notifyNodeExpanded(this, args);

        if (args.isHandled() || args.getSource()!= this)
            return;
            LeafNode<T> currentNode = this;
            while (!isTop(currentNode)) {
                args.setFlatListChanged(args.isFlatListChanged() && currentNode.mParent.isExpanded());
                currentNode.mParent.notifyExpanded(args);
                    if (args.isHandled())
                        return;
                currentNode = currentNode.mParent;
        }
    }

    void notifyCollapsed(NodeEventArgs args) {
        if (mEventMessanger !=null)
            mEventMessanger.notifyNodeCollapsed(this, args);

        if (args.isHandled() || args.getSource() != this)
            return;

        LeafNode<T> currentNode = this;
        while (!isTop(currentNode)) {
            args.setFlatListChanged(args.isFlatListChanged() && currentNode.mParent.isExpanded());
            currentNode.mParent.notifyCollapsed(args);
            if (args.isHandled())
                      return;
            currentNode = currentNode.mParent;
            }
    }

    void notifyFlatListUpdated(NodeEventArgs args) {
        if (mEventMessanger !=null)
            mEventMessanger.notifyNodeTreeListUpdate(this, args);
        if (args.isHandled() || args.getSource()!= this)
            return;

        LeafNode<T> currentNode = this;
        while (!isTop(currentNode)) {
            args.setFlatListChanged(args.isFlatListChanged() && currentNode.mParent.isExpanded());
            currentNode.mParent.notifyFlatListUpdated(args);
            if (args.isHandled())
                return;
            currentNode = currentNode.mParent;
        }
    }

    public boolean isAutoUpdate() {
        return mAutoUpdate;
    }

    public void setAutoUpdate(boolean mAutoUpdate) {
        this.mAutoUpdate = mAutoUpdate;
    }

    public int getLevel() {
        return mParent != null ? mParent.getLevel() + 1 : 0;
    }

    public void updateSelfFlatList() {
        mFlatList = new ArrayList<LeafNode<T>>();
        mFlatList.clear();
        mFlatList.add(this);
    }

    public boolean getCanExpanded() {
        return false;
    }

    public void setIsExpanded(boolean isExpanded) {
        throw new UnsupportedOperationException();
    }

    public boolean isExpanded() {
        return mIsExpanded;
    }

    public List<LeafNode<T>> getFlatList() {
        return Collections.unmodifiableList(mFlatList);
    }

    public LeafNode<T> getFlatItem(int position) {
        return mFlatList.get(position);
    }

    public void updateFlatList() {
            updateSelfFlatList();
            updateParentFlatList();
            notifyFlatListUpdated(new NodeEventArgs(this));
    }

    public void updateParentFlatList() {
        LeafNode<T> parent = mParent;
        while (parent != null  && parent.isAutoUpdate() && parent.isExpanded()) {
            parent.updateSelfFlatList();
            parent = parent.mParent;
        }
    }

    public LeafNode<T> getTopParent() {
        LeafNode<T> node = this.mParent;
        while (node != null && node.mParent != null){
            node = node.mParent;
        }
        return node;
    }

    public void remove(LeafNode<T> child) {
        throw new UnsupportedOperationException();
    }

    public void removeAll(Collection<? extends LeafNode<T>> children) {
        throw new UnsupportedOperationException();
    }

    public  void add(LeafNode<T> child) {
        throw new UnsupportedOperationException();
    }

    public void addAll(Collection<? extends LeafNode<T>> children) {
        throw new UnsupportedOperationException();
    }

    public int getFlatListSize() {
        return mFlatList.size();
    }

    public static final class Helper {
        public static<T> List<LeafNode<T>> getExpandedNodes(LeafNode<T> node){
            List<LeafNode<T>> expandedNodes = new ArrayList<LeafNode<T>>();
            List<LeafNode<T>> visitedNodes = new ArrayList<LeafNode<T>>();
            visitedNodes.add(node);
            while (!visitedNodes.isEmpty()) {
                LeafNode<T> currentNode = visitedNodes.remove(0);
                if (currentNode.isExpanded() && currentNode.getCanExpanded()) {
                    expandedNodes.add(currentNode);
                    visitedNodes.addAll(currentNode.mChildren);
                }
            }
            return expandedNodes;
        }

        public static<T> LeafNode<T> getNodeBy(LeafNode<T> node, List<Integer> location) {
            LeafNode<T> resultNode = node;
            for (int index : location) {
                if (index < resultNode.mChildren.size())
                    resultNode = resultNode.mChildren.get(index);
                else
                    return null;
            }
            return resultNode;
        }

        public static int[] getTreeLocation(LeafNode node) {
            int[] location = new int[node.getLevel()];
            while (!isTop(node)) {
                LeafNode parent = node.mParent;
                int index = parent.mChildren.indexOf(node);
                location[parent.getLevel()] = index;
                node = parent;
            }
            return location;
        }

        public static<T> void collapseAll(LeafNode<T> node, boolean isOnlyVisible) {
            List<LeafNode<T>> visitedNodes = new ArrayList<LeafNode<T>>();
            visitedNodes.add(node);
            while (!visitedNodes.isEmpty()) {
                LeafNode<T> currentNode = visitedNodes.remove(0);
                if (!currentNode.getCanExpanded())
                    continue;
                if (!isOnlyVisible || currentNode.isExpanded())
                    visitedNodes.addAll(currentNode.mChildren);

                currentNode.setIsExpanded(false);
            }
        }

        public static<T> void expandAll(LeafNode<T> node) {
            List<LeafNode<T>> collapsedNodes = new ArrayList<LeafNode<T>>();
            List<LeafNode<T>> visitedNodes = new ArrayList<LeafNode<T>>();
            visitedNodes.add(node);
            int maxLevel = 0;
            while (!visitedNodes.isEmpty()) {
                LeafNode<T> currentNode = visitedNodes.remove(0);
                if (currentNode.getCanExpanded()) {
                    visitedNodes.addAll(currentNode.mChildren);
                    if (!currentNode.isExpanded()) {
                        collapsedNodes.add(currentNode);
                        int childLevel = currentNode.getLevel();
                        if (maxLevel < childLevel)
                            maxLevel = childLevel;
                    }
                }
            }

            if (collapsedNodes.isEmpty())
                return;

            for (int level = maxLevel; level >= 0; level--) {
                for (int index = collapsedNodes.size() - 1; index >= 0; index--) {
                    LeafNode<T> currentNode = collapsedNodes.get(index);
                    if (currentNode.getLevel() == level) {
                        currentNode.setIsExpanded(true);
                        collapsedNodes.remove(currentNode);
                    }
                }
            }
        }
    }

}
