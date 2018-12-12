package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cell {

    private final int x;
    private final int y;

    private final List<Claim> claims = new ArrayList<>();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean hasSingleClaim() {
        return claims.size() == 1;
    }

    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }

    public Claim getSingleClain() {
        return claims.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x &&
                y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
