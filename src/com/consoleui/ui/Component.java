package com.consoleui.ui;

public interface Component {

	public String getId();
	public void show();
	public Object getValue();
	
	/*
	 * for(int i=0;i<200;i++)
	 * System.out.println("\u001B["+i+"m this is "+"\\u001B["+i+
	 * "m"+" color \u001B[0m");
	 */
}
