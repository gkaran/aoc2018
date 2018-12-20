package aoc.day7;

import java.util.ArrayList;
import java.util.List;

public class Constraint {

    private final String name;
    public String getName() { return name; }

    private List<String> requirements = new ArrayList<>();
    public List<String> getRequirements() { return requirements; }
    public void setRequirements(List<String> requirements) { this.requirements = requirements; }

    private List<String> unlockables = new ArrayList<>();
    public List<String> getUnlockables() { return unlockables; }
    public void setUnlockables(List<String> unlockables) { this.unlockables = unlockables; }

    public Constraint(String name) {this.name = name;}
}
