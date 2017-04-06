package am.android.example.android.deeptreeview;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import am.android.example.android.deeptreeview.nodes.LeafNode;
import am.android.example.android.deeptreeview.nodes.TreeNode;

final class DummyNodesFactory {
    private static Integer sNodeCount = 0;

    private static void nextLocation(List<Integer> location, int maxLevelCount) {
        int increment = 1;
        for (int index = 0; index < location.size() && increment == 1; index++) {
            int num = location.get(index) + increment;
            if (num == maxLevelCount) {
                location.set(index, 0);
            } else {
                location.set(index, num);
                increment--;
            }
        }
        if (increment == 1)
            location.add(0);
    }

    public static LeafNode<String> getRootNode(int maxLevelCount) {
        TreeNode<String> rootNode = new TreeNode<String>();
        rootNode.setData("Root");
        List<Integer> location = new ArrayList<Integer>();
        while (true) {
            if (location.size() >= maxLevelCount)
                break;

            LeafNode<String> node = LeafNode.Helper.getNodeBy(rootNode, location);
            addNodes(node, maxLevelCount, node.getLevel() + 1 == maxLevelCount);
            nextLocation(location, maxLevelCount);
        }
        return rootNode;
    }

    private static void addNodes(LeafNode<String> rootNode, int maxLevelCount, boolean isLastNode) {
        List<LeafNode<String>> addingNodes = new ArrayList<LeafNode<String>>();
        for (int index = 0; index < maxLevelCount; index++) {
            LeafNode<String> node = isLastNode ? new LeafNode<String>() : new TreeNode<String>();
            addingNodes.add(node);
        }
        rootNode.addAll(addingNodes);
        for (LeafNode<String> node : addingNodes) {
            String data = "node " + Arrays.toString(LeafNode.Helper.getTreeLocation(node));
            node.setData(data);
        }
    }

    private static void createNodes(int maxLevelCount, LeafNode<String> node) {
        boolean isLastLevel = node.getLevel() + 1 >= maxLevelCount;
        List<LeafNode<String>> addedChildren = new ArrayList<>();
        for (int index = 0; index < maxLevelCount; index++) {
            LeafNode<String> child = isLastLevel ? new LeafNode<String>(false) :
                    new TreeNode<String>(false);
            addedChildren.add(child);
        }
        node.addAll(addedChildren);
        sNodeCount += maxLevelCount;
        for (LeafNode<String> child : addedChildren) {
            String data = "node " + Arrays.toString(LeafNode.Helper.getTreeLocation(child));
            ;
            child.setData(data);
            if (!isLastLevel)
                createNodes(maxLevelCount, child);
        }
    }

    public static LeafNode<String> getRootNodeV3(int maxLevelCount) {
        LeafNode<String> rootNode = new TreeNode<String>(true);
        rootNode.setData("Root");
        sNodeCount++;
        createNodes(maxLevelCount, rootNode);
        Log.d("INFO", String.format("created %d node(s)", sNodeCount));
        return rootNode;
    }

    public static LeafNode<String> getRootNodeV2(int maxLevelCount) {
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
                    String data = "node " + Arrays.toString(LeafNode.Helper.getTreeLocation(child));
                    child.setData(data);
                    nestedNodes.add(child);
                }
            }
        }
        return rootNode;
    }
}
