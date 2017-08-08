package am.android.example.android.deeptreeview;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import am.android.example.android.deeptreeview.adapters.StringDeepTreeViewAdapter;
import am.android.example.android.deeptreeview.nodes.LeafNode;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CreateAdapterDataThread(4).start();
    }

    private void fillListView(final List<LeafNode<String>> rootNodes) {
        final StringDeepTreeViewAdapter adapter = new StringDeepTreeViewAdapter(rootNodes, getLayoutInflater());
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getListView().setAdapter(adapter);
            }
        });
    }

    private final class CreateAdapterDataThread extends Thread {
        private int mMaxDeepLevel;

        public CreateAdapterDataThread(int maxDeepLevel) {
            mMaxDeepLevel = maxDeepLevel;
        }

        @Override
        public void run() {
            List<LeafNode<String>> rootNodes = new ArrayList<LeafNode<String>>();
            rootNodes.add(DummyNodesFactory.getRootNode(mMaxDeepLevel));
            fillListView(rootNodes);
        }
    }
}
