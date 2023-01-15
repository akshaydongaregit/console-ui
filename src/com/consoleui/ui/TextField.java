package com.consoleui.ui;

import java.util.Scanner;

import com.consoleui.app.Action;

public class TextField implements Component {

	private String lable = "";
	private String id = null;
	private String value = null;

	private Action action;

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	@Override
	public void show() {
		Scanner in = new Scanner(System.in);
		System.out.print("\n+    \u001B[1m"+this.lable+": \u001B[92m");
		this.value = in.next();
		System.out.print("\u001B[0m");
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getValue() {
		return value;
	}

}
