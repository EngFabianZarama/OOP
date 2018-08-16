package finalEcam;

public class pratice {
	public static int mystery(int[] data, int n) {
		int sum;
		if (n <= 0)
			return 0;
		else {
			if (data[n - 1] % 2 == 0)
				sum = mystery(data, n - 1) + 1;
			else
				sum = mystery(data, n - 1);
			return sum;
		}
	}
	public static int factorial(int n) {
		  if (n == 0)
		    return 1;
		  
		  return factorial(n-1) * n;
		}
	
	public static void main(String[] args){
		int p;
		p=0+(5/2);
		System.out.println(p);
	}
}
