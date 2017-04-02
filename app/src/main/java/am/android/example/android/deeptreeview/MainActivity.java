package am.android.example.android.deeptreeview;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import am.android.example.android.deeptreeview.adapters.StringDeepTreeViewAdapter;
import am.android.example.android.deeptreeview.nodes.LeafNode;

public class MainActivity extends ListActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new CreateAdapterDataThread(5).run();
    }

    private void fillListView(final List<LeafNode<String>> rootNodes){
        this.runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   StringDeepTreeViewAdapter adapter = new StringDeepTreeViewAdapter(rootNodes, MainActivity.this);
                                   getListView().setAdapter(adapter);
                               }
                           });
    }

    private final class CreateAdapterDataThread extends Thread {
        private int mMaxLevel;
        public CreateAdapterDataThread(int maxLevel) {
            super();
            mMaxLevel = maxLevel;
        }

        @Override
        public void run() {
            List<LeafNode<String>> rootNodes = new ArrayList<LeafNode<String>>();
            rootNodes.add(DummyNodesFactory.getRootNodeV2(mMaxLevel));
            fillListView(rootNodes);
        }
    }
}
