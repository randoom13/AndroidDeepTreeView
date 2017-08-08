package am.android.example.android.deeptreeview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import am.android.example.android.deeptreeview.nodes.LeafNode;
import am.android.example.android.deeptreeview.nodes.TreeNode;

import static org.junit.Assert.assertEquals;

public class TreeNodeBaseOperationsTests {

    @Test
    public void testAddCommandforGetLevel() {
        TreeNode<String> treeNode = new TreeNode<>("top");
        LeafNode<String> node = new LeafNode<>();
        treeNode.add(node);
        assertEquals(treeNode.getLevel(), 0);
        assertEquals(node.getLevel(), 1);
    }

    @Test
    public void addFreeLeafNode() {
        TreeNode<String> treeNode = new TreeNode<>("top");
        LeafNode<String> node = new LeafNode<>();
        treeNode.add(node);
        assertEquals(treeNode.indexOf(node), 0);
        assertEquals(node.getTopParent(), treeNode);
    }

    @Test
    public void removeAddedLeafNode() {
        TreeNode<String> treeNode = new TreeNode<>("top");
        LeafNode<String> node = new LeafNode<>();
        treeNode.add(node);
        treeNode.remove(node);
        assertEquals(treeNode.indexOf(node), -1);
        assertEquals(node.getTopParent(), null);
    }

    @Test
    public void indexOfFreeLeafNode() {
        TreeNode<String> treeNode = new TreeNode<>("top");
        LeafNode<String> node = new LeafNode<>();
        assertEquals(treeNode.indexOf(node), -1);
        assertEquals(node.getTopParent(), null);
    }

    @Test
    public void attachFreeLeafNode() {
        TreeNode<String> treeNode = new TreeNode<>("top");
        LeafNode<String> node = new LeafNode<>();
        treeNode.attach(node);
        assertEquals(treeNode.indexOf(node), 0);
        assertEquals(node.getTopParent(), treeNode);
    }

    @Test
    public void addLeafFromAnotherTree() {
        TreeNode<String> treeNode1 = new TreeNode<>("top1");
        LeafNode<String> leafNode = new LeafNode<>("first node");
        treeNode1.add(leafNode);
        TreeNode<String> treeNode2 = new TreeNode<>("top2");
        treeNode2.add(leafNode);
        assertEquals(treeNode2.indexOf(leafNode), 0);
        assertEquals(treeNode1.indexOf(leafNode), -1);
        assertEquals(leafNode.getTopParent(), treeNode2);
    }

    @Test
    public void addLeavesFromAnotherTree() {
        TreeNode<String> treeNode1 = new TreeNode<>("top1");
        LeafNode<String> leaf1 = new LeafNode<>("first leaf");
        treeNode1.add(leaf1);
        LeafNode<String> leaf2 = new LeafNode<>("second leaf");
        treeNode1.add(leaf2);
        LeafNode<String> leaf3 = new LeafNode<>("third leaf");
        treeNode1.add(leaf3);
        TreeNode<String> treeNode2 = new TreeNode<>("top1");
        treeNode2.add(leaf1);
        treeNode2.add(leaf2);
        assertEquals(treeNode2.indexOf(leaf1), 0);
        assertEquals(treeNode2.indexOf(leaf2), 1);
        assertEquals(treeNode1.indexOf(leaf3), 0);
        assertEquals(leaf1.getTopParent(), treeNode2);
        assertEquals(leaf2.getTopParent(), treeNode2);
        assertEquals(leaf3.getTopParent(), treeNode1);
    }

    @Test
    public void addAllLeavesFromAnotherTree() {
        TreeNode<String> treeNode1 = new TreeNode<>("top1");
        List<LeafNode<String>> nodes = new ArrayList<>();
        LeafNode<String> leaf1 = new LeafNode<>("first leaf");
        nodes.add(leaf1);
        LeafNode<String> leaf2 = new LeafNode<>("second leaf");
        nodes.add(leaf2);
        treeNode1.addAll(nodes);
        TreeNode<String> treeNode2 = new TreeNode<>("top1");
        treeNode2.addAll(nodes);
        assertEquals(treeNode2.indexOf(leaf1), 0);
        assertEquals(treeNode2.indexOf(leaf2), 1);
        assertEquals(leaf1.getTopParent(), treeNode2);
        assertEquals(leaf2.getTopParent(), treeNode2);
    }

    @Test
    public void attachAllAllLeavesFromAnotherTree() {
        TreeNode<String> treeNode1 = new TreeNode<>("top1");
        List<LeafNode<String>> nodes = new ArrayList<>();
        LeafNode<String> leaf1 = new LeafNode<>("first leaf");
        nodes.add(leaf1);
        LeafNode<String> leaf2 = new LeafNode<>("second leaf");
        nodes.add(leaf2);
        treeNode1.attachAll(nodes);
        TreeNode<String> treeNode2 = new TreeNode<>("top1");
        treeNode2.attachAll(nodes);
        assertEquals(treeNode2.indexOf(leaf1), 0);
        assertEquals(treeNode2.indexOf(leaf2), 1);
        assertEquals(leaf1.getTopParent(), treeNode2);
        assertEquals(leaf2.getTopParent(), treeNode2);
    }

    @Test
    public void attachFreeLeaves() {
        TreeNode<String> treeNode1 = new TreeNode<>("top1");
        LeafNode<String> leaf1 = new LeafNode<>("first leaf");
        treeNode1.attach(leaf1);
        LeafNode<String> leaf2 = new LeafNode<>("second leaf");
        treeNode1.attach(leaf2);
        LeafNode<String> leaf3 = new LeafNode<>("third leaf");
        treeNode1.attach(leaf3);
        assertEquals(treeNode1.indexOf(leaf1), 0);
        assertEquals(treeNode1.indexOf(leaf2), 1);
        assertEquals(treeNode1.indexOf(leaf3), 2);
        assertEquals(leaf1.getTopParent(), treeNode1);
        assertEquals(leaf2.getTopParent(), treeNode1);
        assertEquals(leaf3.getTopParent(), treeNode1);
    }

    @Test
    public void testAddNodesForLevel() {
        TreeNode<String> treeNode0 = new TreeNode<>("top node");
        TreeNode<String> treeNode1 = new TreeNode<>("first node");
        treeNode0.attach(treeNode1);
        TreeNode<String> treeNode2 = new TreeNode<>("second node");
        treeNode1.attach(treeNode2);
        TreeNode<String> treeNode3 = new TreeNode<>("third node");
        treeNode2.attach(treeNode3);
        assertEquals(treeNode1.getLevel(), 1);
        assertEquals(treeNode2.getLevel(), 2);
        assertEquals(treeNode3.getLevel(), 3);
    }
}
