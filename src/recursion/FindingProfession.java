package src.recursion;

public class FindingProfession {

	public static int recurse(int x) {

		if(x==1)
			return 1;
		
		else
			return x*recurse(x-1);
		
	}

	public static String stringReverse(String str){
		
		if(str.length()==1)
			return str;
		
		else
			return (stringReverse(str.substring(1, str.length()))).concat(str.substring(0,1));
		
	}
	
	public static void main(String[] args) {
		
		String str = "tsgarhulaswertyuioop";
		
		System.out.println(stringReverse(str));
		
		
	}

}
