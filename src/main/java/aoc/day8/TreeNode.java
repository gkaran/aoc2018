package aoc.day8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeNode {

    private TreeNode parent;
    public TreeNode getParent() { return parent; }
    public void setParent(TreeNode parent) { this.parent = parent; }

    private List<Integer> metadata;
    public List<Integer> getMetadata() { return metadata; }
    public void setMetadata(List<Integer> metadata) { this.metadata = metadata; }

    private TreeNode[] children;
    public TreeNode[] getChildren() { return children; }

    public TreeNode(int children) {
        this.children = new TreeNode[children];
    }

    public void addChild(TreeNode node) {
        node.setParent(this);
        this.children[getChildrenSize()] = node;
    }

    public int getChildrenSize() {
        return (int) Arrays.stream(this.children).filter(Objects::nonNull).count();
    }

    public int getChildrenCapacity() {
        return this.children.length;
    }

    public List<TreeNode> getAllNestedNodesFlattened() {
	return Stream.concat(
	    List.of(this).stream(),
	    Arrays.stream(children).map(TreeNode::getAllNestedNodesFlattened).flatMap(List::stream)
	).collect(Collectors.toList());
    }

}
