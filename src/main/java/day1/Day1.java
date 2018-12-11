package day1;

import helpers.InputLoader;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new InputLoader().getResource(1));

	int sum = 0;

	while (sc.hasNextInt()) {
	    sum += sc.nextInt();
	}

	System.out.println(sum);
    }
}
