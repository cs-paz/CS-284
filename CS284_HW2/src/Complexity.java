
public class Complexity {
	
	public static void method1(int n) {
		int counter = 1;
		for (int i =0; i <n; i ++) {
			for (int j = 0; j <n;j ++) {
				System.out.println("Operation "+ counter);
				counter++;
			}
		}
	}
	
	public static void method2(int n) {
		int counter = 1;
		for (int i =0; i <n; i ++) {
			for (int j = 0; j <n;j ++) {
				for (int w = 0; w <n;w ++) {
					System.out.println("Operation "+ counter);
					counter++;
				}
			}
		}
	}
	
	public static void method3(int n) {
		int counter = 1;
		for(int i=1; i < n; i *= 2) {
			System.out.println("Operation "+ counter);
			counter++;
		}
	}
	
	public static void method4(int n) {
		int counter = 1;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j *= 2) {
				System.out.println("Operation "+ counter);
				counter++;
			}
		}
	}
	
	public static void method5(int n) {
		int counter = 1;
		for(int i = 2; i < n; n = (int)(Math.sqrt(n))) {
			System.out.println("Operation "+ counter);
			counter++;
		}
	}
	
	static int count = 1; //counter defined outside method because its recursive
	public static int method6(int n) {
		if(n == 0) {
			System.out.println("Operation "+ count);
			count++;
			return 0;
		}
	    else if(n == 1) {
	    	System.out.println("Operation "+ count);
			count++;
			return 1;
	    }
	   else {
		   System.out.println("Operation "+ count);
		   count++;
		   return method6(n - 1) + method6(n - 2);
	   }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Method 1: ");
		method1(5);
		System.out.println("Method 1: ");
		method1(10);
		System.out.println("Method 2: ");
		method2(5);
		System.out.println("Method 2: ");
		method2(3);
		System.out.println("Method 3: ");
		method3(5);
		System.out.println("Method 3: ");
		method3(10);
		System.out.println("Method 4: ");
		method4(5);
		System.out.println("Method 4: ");
		method4(10);
		System.out.println("Method 5: ");
		method5(10);
		System.out.println("Method 5: ");
		method5(20);
		System.out.println("Method 6: ");
		method6(3);
		System.out.println("Method 6: ");
		method6(10);
	}

}
