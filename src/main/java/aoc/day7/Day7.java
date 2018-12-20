package aoc.day7;

import aoc.Solution;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Day7 implements Solution<String, Integer> {

    private static final int WORKERS = 5;

    @Override
    public String part1(List<String> data) {

	Map<String, Constraint> constraints = getConstraintsMap(data);

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
    public Integer part2(List<String> data) {
	Map<String, Constraint> constraints = getConstraintsMap(data);
	List<WorkTask> workerTasks = new ArrayList<>(Collections.nCopies(WORKERS,null));

	int second = 0;

	List<String> availableSteps = new ArrayList<>();
	StringBuilder path = new StringBuilder();

	constraints.values().stream()
	    .filter(c -> c.getRequirements().isEmpty())
	    .map(Constraint::getName)
	    .forEach(availableSteps::add);

	availableSteps.sort(Comparator.naturalOrder());

	System.out.print("Second");
	IntStream.range(0, WORKERS).mapToObj(i -> "   Worker " + (i + 1)).forEach(System.out::print);
	System.out.println("   Done");

	StringBuilder templateString = new StringBuilder().append("%6s   ");
	IntStream.range(0, WORKERS).forEach(i -> templateString.append("   %-5s   "));
	templateString.append("%-8s");

	while (!constraints.isEmpty()) {

	    for (int i = 0; i < WORKERS; i++) {
	        var task = workerTasks.get(i);
		if (task != null) {
		    task.setReaminingTime(task.getReaminingTime() - 1);
		    if (task.getReaminingTime() == 0) {
			String entry = task.getTask();
			path.append(entry);
			workerTasks.set(i, null);

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
			    .filter(Predicate.not(workerTasks.stream().filter(Objects::nonNull).map(WorkTask::getTask).collect(toList())::contains))
			    .forEach(availableSteps::add);

			availableSteps.sort(Comparator.naturalOrder());

		    }
		}
	    }

	    if (!availableSteps.isEmpty()) {
		for (int i = 0; i < WORKERS; i++) {
		    if (availableSteps.isEmpty()) {
		        break;
		    }

		    if (workerTasks.get(i) == null) {
		        String task = availableSteps.remove(0);
		        workerTasks.set(i, new WorkTask(task, 60 + (task.charAt(0) - 'A' + 1)));
		    }
		}
	    }

	    List<String> printValues = new ArrayList<>(List.of(second + ""));

	    IntStream.range(0, WORKERS)
		.mapToObj(workerTasks::get)
		.map(task -> task == null ? "." : task.getTask())
		.forEach(printValues::add);

	    printValues.add(path.toString());



	    System.out.println(String.format(templateString.toString(), printValues.toArray(new String[printValues.size()])));

	    second++;
	}


	return second - 1;
    }

    private Map<String, Constraint> getConstraintsMap(List<String> data) {
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
	return constraints;
    }
}
