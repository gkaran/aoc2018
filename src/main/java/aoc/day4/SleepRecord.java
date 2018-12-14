package aoc.day4;

public class SleepRecord {
    private final String day;
    private final int id;
    private final char[] data;

    public String getDay() { return day; }

    public int getId() { return id; }

    public char[] getData() { return data; }

    public SleepRecord(String day, int id, char[] data) {
	this.day = day;
	this.id = id;
	this.data = data;
    }
}
