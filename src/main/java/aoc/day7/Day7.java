package aoc.day7;

import aoc.Solution;

import java.util.*;
import java.util.function.Predicate;

public class Day7 implements Solution<String, String> {
    @Override
    public String part1(List<String> data) {

	Map<String, Constraint> constraints = new HashMap<>();

	for (var instruction : data) {
	    final String[] steps = instruction
		.replace("Step ", "")
		.replace(" must be finished before step ", " ")
		.replace(" can begin.", "")
		.split(" ");

	    constraints.compute(steps[0], (step, constraint) -> {
		if (constraint == null) {
		    constraint = new Constraint(steps[0]);
		}
		constraint.getUnlockables().add(steps[1]);
		return constraint;
	    });

	    constraints.compute(steps[1], (step, constraint) -> {
		if (constraint == null) {
		    constraint = new Constraint(steps[1]);
		}
		constraint.getRequirements().add(steps[0]);
		return constraint;
	    });
	}

	StringBuilder path = new StringBuilder();

	List<String> availableSteps = new ArrayList<>();

	constraints.values().stream()
	    .filter(c -> c.getRequirements().isEmpty())
	    .map(Constraint::getName)
	    .forEach(availableSteps::add);

	availableSteps.sort(Comparator.naturalOrder());

	while (!availableSteps.isEmpty()) {
	    String entry = availableSteps.remove(0);
	    path.append(entry);
	    constraints.get(entry)
		.getUnlockables()
		.stream()
		.map(constraints::get)
		.forEach(v -> v.getRequirements().remove(entry));

	    constraints.remove(entry);

	    constraints.values().stream()
		.filter(c -> c.getRequirements().isEmpty())
		.map(Constraint::getName)
		.filter(Predicate.not(availableSteps::contains))
		.forEach(availableSteps::add);

	    availableSteps.sort(Comparator.naturalOrder());
	}

	return path.toString();
    }

    @Override
    public String part2(List<String> data) {
	return null;
    }
}
