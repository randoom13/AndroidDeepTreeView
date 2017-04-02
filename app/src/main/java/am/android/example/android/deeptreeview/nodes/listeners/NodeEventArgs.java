package am.android.example.android.deeptreeview.nodes.listeners;

/**
 * Created by alexander on 02.04.17.
 */
public class NodeEventArgs<T> {
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

    private boolean mIsHandled;

    public T getSource() {
        return mSource;
    }

    private T mSource;

    public boolean isFlatListChanged() {
        return isFlatListChanged;
    }

    public void setFlatListChanged(boolean flatListChanged) {
        isFlatListChanged = flatListChanged;
    }

    private boolean isFlatListChanged;
}
