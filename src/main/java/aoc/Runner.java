package aoc;

import aoc.helpers.InputLoader;
import lombok.extern.java.Log;

import java.lang.reflect.Constructor;
import java.util.List;

@Log
public class Runner {
    private static final InputLoader inputLoader = new InputLoader();

    public static void main(String[] args) throws Exception {
	int day = 6;
	String className = "aoc.day" + day + ".Day" + day;

	Class<Solution<?, ?>> clazz = (Class<Solution<?, ?>>) Class.forName(className);
	Constructor<Solution<?, ?>> ctor = clazz.getConstructor();
	Solution<?, ?> object = ctor.newInstance();

	List<String> data = inputLoader.getData(day);
	System.out.println("=== Day " + day + " - Part 1 ===");
	System.out.println(object.part1(data) + "");

	System.out.println("==========================================");


	System.out.println("=== Day " + day + " - Part 2 ===");
	System.out.println(object.part2(data) + "");
    }
}