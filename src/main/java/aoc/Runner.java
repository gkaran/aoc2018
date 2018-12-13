package aoc;

import aoc.helpers.InputLoader;

import java.lang.reflect.Constructor;

public class Runner {
    private static final InputLoader inputLoader = new InputLoader();

    public static void main(String[] args) throws Exception {
        int day = 1;
        int part = 1;
	String className = "aoc.day" + day + ".Day" + day + "Part" + part;

	Class<Solution<?>> clazz = (Class<Solution<?>>) Class.forName(className);
	Constructor<Solution<?>> ctor = clazz.getConstructor();
	Solution<?> object = ctor.newInstance();

	System.out.println(object.solve(inputLoader.getData(day)));
    }
}
