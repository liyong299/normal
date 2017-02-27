package com.ly.java.other;

import com.google.gson.Gson;

public class TestFor科学计数法 {

	public static void main(String[] args) {
		double a = 223222323.3d;
		double b = 2.2323233E6d;
		System.out.println(a == b);
		ScientificNotation sf = new ScientificNotation();
		sf.setBalance(a);

		System.out.println(new Gson().toJson(sf));
	}
}

class ScientificNotation {
	double balance;

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}
}