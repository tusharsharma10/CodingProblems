package src.recursion;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {

	public static List<Integer> primeFactors(int rec, int num, List<Integer> pf) {

		if (rec == 1) {

			pf.add(1);
			return pf;
		}

		else if (num % rec == 0) {

			pf.add(rec);
			return primeFactors(rec - 1, num, pf);

		}

		else
			return primeFactors(rec - 1, num, pf);

	}

	public static void main(String[] args) {

		int num = 12;

		List<Integer> pf = new ArrayList<Integer>();

		pf = primeFactors(num, num, pf);

		for (int i = 0; i < pf.size(); i++) {

			System.out.println(pf.get(i));

		}

	}

}
