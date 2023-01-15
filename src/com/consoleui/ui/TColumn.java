package com.consoleui.ui;

public class TColumn {
	String header;
	String property;
	int width;
	
	public TColumn(String header, String property, int width) {
		super();
		this.header = header;
		this.property = property;
		this.width = width;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}