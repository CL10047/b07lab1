import java.io.File;

public class Driver {
	public static void main(String [] args) {

		Polynomial poly1 = new Polynomial(new double[]{6, -2, 5}, new int[]{4, 1, 0});
		Polynomial poly2 = new Polynomial(new double[]{3, 2, 1}, new int[]{0, 1, 2});
		
		//add function test
		Polynomial sum = poly1.add(poly2);

		for (int i = 0; i < sum.coefficients.length; i++) {
			System.out.print(sum.coefficients[i]);
			System.out.print(' ');
			System.out.println(sum.exponents[i]);
		}
		System.out.println();

		//product function test
		Polynomial product = poly1.multiply(poly2);
		
		for (int i = 0; i < product.coefficients.length; i++) {
			System.out.print(product.coefficients[i]);
			System.out.print(' ');
			System.out.println(product.exponents[i]);
		}
		System.out.println();
		
		//read file test
		File file = new File("/Users/chrislee/b07lab1/f1.txt");

		Polynomial poly3 = new Polynomial(file);

		 for (int i = 0; i < poly3.coefficients.length; i++) {
			System.out.print(poly3.coefficients[i]);
			System.out.print(' ');
			System.out.println(poly3.exponents[i]);
		}

		//write file test
		poly3.saveToFile("/Users/chrislee/b07lab1/f3.txt");
	}
}