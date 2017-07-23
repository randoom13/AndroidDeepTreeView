package am.android.example.android.deeptreeview.nodes.listeners;

public class NodeEventArgs<T> {
    private boolean mIsHandled;
    private T mSource;
    private boolean isFlatListChanged;

    public NodeEventArgs(T source) {
        super();
        mSource = source;
        setFlatListChanged(true);
    }

    public boolean isHandled() {
        return mIsHandled;
    }

    public void setHandled(boolean handled) {
        mIsHandled = handled;
    }

    public T getSource() {
        return mSource;
    }

    public boolean isFlatListChanged() {
        return isFlatListChanged;
    }

    public void setFlatListChanged(boolean flatListChanged) {
        isFlatListChanged = flatListChanged;
    }
}
