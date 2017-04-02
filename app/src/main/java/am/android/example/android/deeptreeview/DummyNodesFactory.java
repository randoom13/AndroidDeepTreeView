package am.android.example.android.deeptreeview;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import am.android.example.android.deeptreeview.nodes.LeafNode;
import am.android.example.android.deeptreeview.nodes.TreeNode;

/**
 * Created by akhlivnyuk on 3/30/2017.
 */
final class DummyNodesFactory {
    private static String toString(int[] numbers) {
        StringBuilder builder = new StringBuilder();
        for (int number : numbers) {
            Object info = builder.length() > 0 ? "," + number : number;
            builder.append(info);
        }
        return builder.toString();
    }

    private static void createNodes(int maxLevelCount, LeafNode<String> node){
        boolean isLastLevel = node.getLevel() + 1 >= maxLevelCount;
        List<LeafNode<String>> addedChildren = new ArrayList<>();
        for (int index=0; index< maxLevelCount; index++) {
            LeafNode<String> child = isLastLevel ? new LeafNode<String>(false) :
                    new TreeNode<String>(false);
            addedChildren.add(child);
        }
        node.addAll(addedChildren);
        counter += maxLevelCount;
        for (LeafNode<String> child : addedChildren) {
            String data = "node " + toString(LeafNode.Helper.getTreeLocation(child));
            child.setData(data);
            if (!isLastLevel)
                createNodes(maxLevelCount, child);
        }
    }

    private static Integer counter = 0;
    public static LeafNode<String> getRootNodeV2(int maxLevelCount) {
        LeafNode<String> rootNode = new TreeNode<String>(true);
        rootNode.setData("Root");
        counter++;
        createNodes(maxLevelCount, rootNode);
        Log.d("test count", counter.toString());
        return rootNode;
    }

    public static LeafNode<String> getRootNode(int maxLevelCount) {
        LeafNode<String> rootNode = new TreeNode<String>(true);
        rootNode.setData("Root");
        List<LeafNode<String>> nestedNodes = new ArrayList<LeafNode<String>>();
        nestedNodes.add(rootNode);
        for (int level = 0; level < maxLevelCount; level++) {
            boolean isLastLevel = level == maxLevelCount - 1;
            while (!nestedNodes.isEmpty() && nestedNodes.get(0).getLevel() == level) {
                LeafNode<String> workNode = nestedNodes.remove(0);
                for (int index = 0; index < maxLevelCount; index++) {
                    LeafNode<String> child = isLastLevel ? new LeafNode<String>(false) :
                            new TreeNode<String>(false);
                    workNode.add(child);
                    String data = "node " + toString(LeafNode.Helper.getTreeLocation(child));
                    child.setData(data);
                    nestedNodes.add(child);
                }
            }
        }
        return rootNode;
    }
}
