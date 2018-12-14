package aoc.day4;

import aoc.Solution;

import java.util.*;
import java.util.stream.Collectors;

public class Day4Part1 implements Solution<Integer> {
    private final String FORMAT_TEMPLATE = "%-7s#%-9d%s";

    @Override
    public Integer solve(List<String> data) {
	Map<Integer, Guard> guards = new HashMap<>();

	int currentShiftGuardId = -1;
	Record sleepRecord = null;

	List<Record> sortedRecords = data.stream()
	    .map(Record::new)
	    .sorted(Comparator.comparing(Record::getDate))
	    .collect(Collectors.toList());

	for (Record record  : sortedRecords) {
	    if (ActionType.SHIFT_START.equals(record.getActionType())) {
		currentShiftGuardId = record.getId();
		sleepRecord = null;
		guards.putIfAbsent(record.getId(), new Guard(record.getId()));
		guards.get(record.getId()).initDate(record.getDay());
	    } else if (ActionType.WAKE_UP.equals(record.getActionType()) && sleepRecord != null) {
		guards.get(sleepRecord.getId()).initDate(record.getDay());
		guards.get(sleepRecord.getId()).recordSleep(sleepRecord, record);
		sleepRecord = null;
	    } else if (ActionType.FALL_ASLEEP.equals(record.getActionType()) && currentShiftGuardId >= 0) {
	        record.setId(currentShiftGuardId);
	        sleepRecord = record;
	    }
	}

	printSleepLog(guards);

	Guard mostSleepyGuard = guards.values().stream()
	    .max(Comparator.comparing(Guard::getTotalSleepMinutes).thenComparingInt(Guard::getId))
	    .orElseThrow();

	System.out.println("Sleepy Guard id: " + mostSleepyGuard.getId() + ", minute: " + mostSleepyGuard.getMostSleepyMinute());

	return mostSleepyGuard.getId() * mostSleepyGuard.getMostSleepyMinute();
    }

    private void printSleepLog(Map<Integer, Guard> guards) {
	List<SleepRecord> sleepRecords = guards.values().stream()
	    .map(Guard::getSleepRecords)
	    .flatMap(List::stream)
	    .collect(Collectors.toList());

	System.out.println("Date   ID        Minute");
	System.out.println("                 000000000011111111112222222222333333333344444444445555555555");
	System.out.println("                 012345678901234567890123456789012345678901234567890123456789");
	sleepRecords.stream()
	    .sorted(Comparator.comparing(SleepRecord::getDay).thenComparingInt(SleepRecord::getId))
	    .map(record -> String.format(FORMAT_TEMPLATE, record.getDay(), record.getId(), new String(record.getData())))
	    .forEachOrdered(System.out::println);
    }
}
