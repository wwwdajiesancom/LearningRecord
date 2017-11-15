package com.loujie.www.w2c;

public class JavaMain {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Fbc abcn = new Fbc("afdsfsd", "fsdfsdfsdf");
		System.err.println(abcn.toString());
	}

}

class Fbc {

	private String nn;
	private String bb;

	public Fbc(String nn, String bb) {
		this.bb = bb;
		this.nn = nn;
	}

	static {
		System.err.println("static");
	}

	{
		System.err.println(nn + bb + "nx");
	}
}