package com.wayn;

import org.junit.Test;

public class SubTest {

	@Test
	public void test() {

		Sub sub = new Sub();
		sub.display();
		Super super1 = sub;
		System.out.println(super1.count);
		super1.display();
	}
}

class Super {
	public int count = 10;

	public void display() {
		System.out.println(this.count);
	}
}

class Sub extends Super {
	public int count = 20;

	@Override
	public void display() {
		System.out.println(this.count);
	}
}