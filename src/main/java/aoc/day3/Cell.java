package aoc.day3;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
class Cell {
    private final int x;
    private final int y;
    @EqualsAndHashCode.Exclude
    private final List<Claim> claims = new ArrayList<>();

    Cell(int x, int y) {
	this.x = x;
	this.y = y;
    }

    boolean hasSingleClaim() {
	return claims.size() == 1;
    }

    void addClaim(Claim claim) {
	this.claims.add(claim);
    }

    Claim getSingleClaim() {
	return claims.get(0);
    }

}
