package src.recursion;

public class PowerOfNum {

	// a to power of b a*a*a-- b times
	public static int powerofNum(int a, int b) {

		if (b == 1)
			return a;

		else
			return a * powerofNum(a, b - 1);

	}

	public static void main(String args[]) {

		System.out.println(powerofNum(5, 2));

	}

}
