public class Polynomial {
	double[] coefficients;
	
	public Polynomial() {
		this.coefficients = new double[0];
	}

	public Polynomial(double[] coefficients) {
		this.coefficients = new double[coefficients.length];
		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
		}
	}

	public Polynomial add(Polynomial p2) {
		double[] sumC;
		if(this.coefficients.length >= p2.coefficients.length) {
			sumC = new double[this.coefficients.length];
			for (int i = 0; i < this.coefficients.length; i++) {
				if (i < p2.coefficients.length)
					sumC[i] = this.coefficients[i] + p2.coefficients[i];
				else
					sumC[i] = this.coefficients[i];
			}
		} else {
			sumC = new double[p2.coefficients.length];
			for (int i = 0; i < p2.coefficients.length; i++) {
				if (i < this.coefficients.length)
					sumC[i] = this.coefficients[i] + p2.coefficients[i];
				else
					sumC[i] = p2.coefficients[i];
			}
		}

		Polynomial returnP = new Polynomial(sumC);
		return returnP;
	}

	public double evaluate(double x) {
		double result = 0.0;

		for (int i = 0; i < this.coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, i);
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