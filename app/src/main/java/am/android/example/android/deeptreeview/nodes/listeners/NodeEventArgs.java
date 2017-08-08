package am.android.example.android.deeptreeview.nodes.listeners;

public class NodeEventArgs<T> {
    private boolean mIsHandled;
    public T source;
    private boolean isFlatListChanged;

    public NodeEventArgs(T source) {
        super();
        this.source = source;
        setFlatListChanged(true);
    }

    public boolean isHandled() {
        return mIsHandled;
    }

    public void setHandled(boolean handled) {
        mIsHandled = handled;
    }

    public boolean isFlatListChanged() {
        return isFlatListChanged;
    }

    public void setFlatListChanged(boolean flatListChanged) {
        isFlatListChanged = flatListChanged;
    }
}
