import java.io.BufferedReader;
import java.io.File;
import java.io.PrintStream;
import java.io.IOException;
import java.io.FileReader;

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		this.coefficients = new double[0];
		this.exponents = new int[0];
	}

	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];
		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		}
	}

	public Polynomial(File file) {
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();

			String[] terms = line.split("(?=[-+])");
			this.coefficients = new double[terms.length];
	        this.exponents = new int[terms.length];
	        int indexOfVariable;

	        this.coefficients[0] = Double.parseDouble(terms[0]);
	        this.exponents[0] = 0;

	        for (int i = 1; i < terms.length; i++) {
	        	indexOfVariable = terms[i].indexOf("x");
	        	this.coefficients[i] = Double.parseDouble(terms[i].substring(0, indexOfVariable));
	        	this.exponents[i] = Integer.parseInt(terms[i].substring(indexOfVariable + 1));
	        }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void saveToFile(String filename) {
		try(PrintStream writer = new PrintStream(filename)) {
			for(int i = 0; i < this.exponents.length; i++) {
				if (i == 0) {
					writer.print(this.coefficients[i]);
				} else {
					if (this.coefficients[i] > 0) {
						writer.print("+");
					}

					writer.print(coefficients[i]);
					writer.print("x");
					writer.print(exponents[i]);
				}
			}
		} catch (IOException e) {
            e.printStackTrace();
        }
	}

	public Polynomial add(Polynomial p2) {
	    int maxLength = this.coefficients.length + p2.coefficients.length;
	    int count = 0;
	    double[] sumC = new double[maxLength];
	    int[] sumE = new int[maxLength];

	    for (int i = 0; i < this.coefficients.length; i++) {
	        sumC[this.exponents[i]] = this.coefficients[i];
	        sumE[this.exponents[i]] = this.exponents[i];
	    }

	    for (int i = 0; i < p2.coefficients.length; i++) {
	        if (sumC[p2.exponents[i]] == 0) {
	        	sumC[p2.exponents[i]] = p2.coefficients[i];
	        	sumE[p2.exponents[i]] = p2.exponents[i];
	        } else {
	        	sumC[p2.exponents[i]] += p2.coefficients[i];
	        }
	    }

	    for (int i = 0; i < maxLength; i++) {
    		if (sumC[i] != 0) {
    			count++;
    		}
    	}

		Polynomial returnP = depopulate(new Polynomial(sumC, sumE), count);
    	return returnP;
	}

	public Polynomial multiply(Polynomial p2) {
		int maxLength = this.exponents.length * p2.exponents.length;
		int count = 0;

		double[] productC = new double[maxLength];
    	int[] productE = new int[maxLength];

    	for (int i = 0; i < this.exponents.length; i++) {
    		for (int j = 0; j < p2.exponents.length; j++) {
    			double newCoeff = this.coefficients[i] * p2.coefficients[j];
    			int newExp = this.exponents[i] + p2.exponents[j];

    			if (productE[newExp] == 0) {
    				productC[newExp] = newCoeff;
    				productE[newExp] = newExp;
    			} else {
    				productC[newExp] += newCoeff;
    			}
    		}
    	}

    	for (int i = 0; i < maxLength; i++) {
    		if (productC[i] != 0) {
    			count++;
    		}
    	}

	    Polynomial returnP = depopulate(new Polynomial(productC, productE), count);
    	return returnP;
	}

	public Polynomial depopulate(Polynomial p, int usedCells) {
		double[] depC = new double[usedCells];
    	int[] depE = new int[usedCells];
    	int count = 0;

		for (int i = 0; i < p.coefficients.length; i++) {
			if (p.coefficients[i] != 0) {
				depC[count] = p.coefficients[i];
				depE[count] = p.exponents[i];
				count++;
			}
		}

		return new Polynomial(depC, depE);
	}

	public double evaluate(double x) {
		double result = 0.0;

		for (int i = 0; i < this.coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, exponents[i]);
		}
		return result;
	}

	public boolean hasRoot(double x) {
		if(evaluate(x) == 0.0) 
			return true;
		else
			return false;
	}
}