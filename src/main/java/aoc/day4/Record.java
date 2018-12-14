package aoc.day4;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    private static final SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");

    private int id = -1;
    private String action;
    private String timeStamp;
    private String day;
    private String time;
    private ActionType actionType;
    private Date date;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAction() { return action; }
    public String getTimeStamp() { return timeStamp; }
    public String getDay() { return day; }
    public String getTime() { return time; }
    public ActionType getActionType() { return actionType; }
    public Date getDate() { return date; }

    public Record(String record) {
	var parts = StringUtils.split(record, "]");
	this.action = parts[1].trim();
	this.timeStamp = StringUtils.remove(parts[0], "[");
	this.day = parseDate(StringUtils.split(this.timeStamp)[0]);
	this.time = StringUtils.split(parts[0])[1];
	try {
	    this.date = DateUtils.parseDate(this.timeStamp, "yyyy-MM-dd hh:mm");
	} catch (ParseException e) {
	    System.exit(0);
	}

	if (action.startsWith("Guard") && action.endsWith("begins shift")) {
	    this.actionType = ActionType.SHIFT_START;
	    this.id = parseGuardId(action);
	} else if ("wakes up".equals(action)) {
	    this.actionType = ActionType.WAKE_UP;
	} else if ("falls asleep".equals(action)) {
	    this.actionType = ActionType.FALL_ASLEEP;
	}
    }

    private int parseGuardId(String action) {
	return Integer.parseInt(StringUtils.remove(StringUtils.remove(action, "Guard #"), " begins shift"));
    }

    private String parseDate(String datePart){
	try {
	    Date date = parser.parse(StringUtils.split(datePart)[0]);
	    return formatter.format(date);
	} catch (ParseException e) {
	    System.exit(0);
	}
	return null;
    }
}
