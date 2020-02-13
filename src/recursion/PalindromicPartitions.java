package recursion;

public class PalindromicPartitions {

	// reverse using recursion and check
	public static String reverse(String n) {

		if (n.length() == 1)
			return n;

		else
			return reverse(n.substring(1, n.length())).concat(n.substring(0, 1));

	}

	public static void main(String[] args) {

		int n = 10121019;
		int a = Integer.parseInt(reverse(String.valueOf(n)));
		
		if(a==n)
			System.out.println(a+" true");
		else
			System.out.println(a+" false");;
	}

}
