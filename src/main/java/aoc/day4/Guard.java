package aoc.day4;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Guard {

    @Getter
    private final int id;

    private final Map<String, char[]> times = new HashMap<>();

    public Guard(int id) {this.id = id;}

    List<SleepRecord> getSleepRecords() {
        return times.entrySet().stream()
	    .map(entry -> new SleepRecord(entry.getKey(), id, entry.getValue()))
	    .collect(Collectors.toList());
    }

    void initDate(String date) {
        if (!times.containsKey(date)) {
	    char[] values = new char[60];
	    Arrays.fill(values, '.');
	    times.put(date, values);
	}
    }

    void recordSleep(Record from, Record to) {
	var fromParts = StringUtils.split(from.getTime(), ":");
	var toParts = StringUtils.split(to.getTime(), ":");

	var fromMinutes = Integer.parseInt(fromParts[1]);
	var toMinutes = Integer.parseInt(toParts[1]);

	if (DateUtils.isSameDay(from.getDate(), to.getDate())) {
	    for (var i = fromMinutes; i < toMinutes; i++) {
		times.get(to.getDay())[i] = '#';
	    }
	} else {
	    long diffDays = TimeUnit.DAYS.convert(to.getDate().getTime() - from.getDate().getTime(), TimeUnit.MILLISECONDS);

	    for (var i = 0; i < diffDays; i++) {
	        int startIndex;
	        int endIndex;

	        if (i == 0) {
		    startIndex = fromMinutes;
		    endIndex = 60;
		} else if (i == diffDays - 1) {
		    startIndex = 0;
		    endIndex = 60;
		} else {
		    startIndex = 0;
		    endIndex = toMinutes;
		}

		var formatter = new SimpleDateFormat("MM-dd");
	        var day = formatter.format(DateUtils.addDays(from.getDate(), i));
		for (var j = startIndex; j < endIndex; j++) {
		    initDate(day);
		    times.get(day)[j] = '#';
		}
	    }
	}
    }

    long getTotalSleepMinutes() {
        return times.values().stream()
	    .flatMap(chars -> IntStream.range(0, chars.length).mapToObj(i -> chars[i]))
	    .filter(c -> c == '#')
	    .count();
    }

    int getMostSleepyMinute() {
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

    long getMostSleepyMinuteSleepTime() {
        // TODO: This is inefficient but can do for now
        return getMinuteTotalSleepTime(getMostSleepyMinute());
    }

    private long getMinuteTotalSleepTime(int minute) {
	return times.values()
	    .stream()
	    .map(v -> v[minute])
	    .filter(c -> c == '#')
	    .count();
    }
}
