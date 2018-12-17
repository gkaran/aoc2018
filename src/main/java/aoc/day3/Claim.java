package aoc.day3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
class Claim {

    private final int id;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    static Claim fromString(String claimStr) {
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

    List<Cell> getCells() {
        List<Cell> cells = new ArrayList<>();
        for (var i = getOffsetX(); i < getOffsetX() + getWidth(); i++) {
            for (var j = getOffsetY(); j < getOffsetY() + getHeight(); j++) {
                cells.add(new Cell(i,j));
            }
        }

        return cells;
    }
}
