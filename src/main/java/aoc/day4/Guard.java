package aoc.day4;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Guard {

    private final int id;
    public int getId() { return id; }

    private final Map<String, char[]> times = new HashMap<>();

    public Guard(int id) {this.id = id;}

    public List<SleepRecord> getSleepRecords() {
        return times.entrySet().stream()
	    .map(entry -> new SleepRecord(entry.getKey(), id, entry.getValue()))
	    .collect(Collectors.toList());
    }

    public void initDate(String date) {
        if (!times.containsKey(date)) {
	    char[] values = new char[60];
	    Arrays.fill(values, '.');
	    times.put(date, values);
	}
    }

    public void recordSleep(Record from, Record to) {
	var fromParts = StringUtils.split(from.getTime(), ":");
	var toParts = StringUtils.split(to.getTime(), ":");

        var fromMinutes = Integer.parseInt(fromParts[1]);
	var toMinutes = Integer.parseInt(toParts[1]);

	var indexStart = fromMinutes > toMinutes ? 0 : fromMinutes;
	for (var i = indexStart; i < toMinutes; i++) {
	    times.get(to.getDay())[i] = '#';
	}
    }

    public long getTotalSleepMinutes() {
        return times.values().stream()
	    .flatMap(chars -> IntStream.range(0, chars.length).mapToObj(i -> chars[i]))
	    .filter(c -> c == '#')
	    .count();
    }

    public int getMostSleepyMinute() {
	int[] identity = new int[60];
	Arrays.fill(identity, 0);

        int[] sum = times.values().stream()
	    .map(chars -> IntStream.range(0, chars.length).map(i -> chars[i] == '#' ? 1 : 0).toArray())
	    .reduce(identity, (a,b) -> IntStream.range(0, a.length).map(i -> a[i] + b[i]).toArray());

	int maxIndex = 0;

	for (int i = 0; i < sum.length; i++) {
	    maxIndex = sum[i] > sum[maxIndex] ? i : maxIndex;
	}

	return maxIndex;
    }
}
