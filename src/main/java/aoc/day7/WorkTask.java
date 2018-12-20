package aoc.day7;

public class WorkTask {

    private final String task;
    public String getTask() { return task; }

    private int reaminingTime;
    public int getReaminingTime() { return reaminingTime; }
    public void setReaminingTime(int reaminingTime) { this.reaminingTime = reaminingTime; }

    public WorkTask(String task, int reaminingTime) {
	this.task = task;
	this.reaminingTime = reaminingTime;
    }
}
