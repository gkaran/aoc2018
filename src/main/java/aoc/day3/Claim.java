package aoc.day3;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class Claim {

    private final int id;

    public int getId() {
        return id;
    }

    private final int offsetX;

    public int getOffsetX() {
        return offsetX;
    }

    private final int offsetY;

    public int getOffsetY() {
        return offsetY;
    }

    private final int width;

    public int getWidth() {
        return width;
    }

    private final int height;

    public int getHeight() {
        return height;
    }

    public Claim(int id, int offsetX, int offsetY, int width, int height) {
        this.id = id;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }

    public static Claim fromString(String claimStr) {
        String[] parts = StringUtils.split(claimStr.replace("@", ""));

        int id = Integer.parseInt(parts[0].replace("#", ""));

        String[] offsets = StringUtils.split(parts[1].replace(":", ""), ",");
        int offsetX = Integer.parseInt(offsets[0]);
        int offsetY = Integer.parseInt(offsets[1]);

        String[] dimensions = StringUtils.split(parts[2], "x");
        int width = Integer.parseInt(dimensions[0]);
        int height = Integer.parseInt(dimensions[1]);

        return new Claim(id, offsetX, offsetY, width, height);
    }

    public List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (var i = getOffsetX(); i < getOffsetX() + getWidth(); i++) {
            for (var j = getOffsetY(); j < getOffsetY() + getHeight(); j++) {
                cells.add(new Cell(i,j));
            }
        }

        return cells;
    }
}
