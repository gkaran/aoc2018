package aoc.day8;

import aoc.Solution;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day8 implements Solution<Integer, Integer> {

    private int treeParseIndex = 0;
    private TreeNode root = null;

    @Override
    public Integer part1(List<String> data) {
	parseTree(data);

	return root.getAllNestedNodesFlattened().stream()
	    .map(TreeNode::getMetadata)
	    .flatMap(List::stream)
	    .mapToInt(Integer::intValue)
	    .sum();
    }

    @Override
    public Integer part2(List<String> data) {
	parseTree(data);
	return root.getValue();
    }

    private void parseTree(List<String> data) {
        if (root == null) {
	    List<Integer> input = Arrays.stream(data.get(0).split(" "))
		.map(Integer::parseInt)
		.collect(toList());

	    treeParseIndex = 0;
	    root = parseNode(input);
	}
    }

    private TreeNode parseNode(List<Integer> input) {
	var children = input.get(treeParseIndex++);
	var metadata = input.get(treeParseIndex++);

	TreeNode node = new TreeNode(children);

	while (node.getChildrenSize() < node.getChildrenCapacity()) {
	    node.addChild(parseNode(input));
	}

	var meta = input.subList(treeParseIndex, treeParseIndex + metadata);

	treeParseIndex += metadata;
	node.setMetadata(meta);

	return node;
    }
}
