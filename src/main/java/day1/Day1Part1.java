package day1;

import helpers.InputLoader;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new InputLoader().getResourceAsFile(1));

	int sum = 0;

	while (sc.hasNextInt()) {
	    sum += sc.nextInt();
	}

	System.out.println(sum);
	sc.close();
    }
}
