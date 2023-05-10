package com.usman.csudh.util;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class UniqueCounter {
	private static int counterState = 1000;
	private static int counterState2 = 1;

	public static int nextValue() {
		return counterState++;
	}
	public static int nextValue2() {
		return counterState2++;
	}

}
