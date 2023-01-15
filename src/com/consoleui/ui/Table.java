package com.consoleui.ui;

import java.util.List;
import java.util.Map;

public class Table<T> implements Component {

	private String id;
	private List<String> columns;
	private List<T> data;
	TTable<T> table = new TTable<T>();
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void show() {
		table.printHeader();
		table.printBeans(data);
	}

	@Override
	public Object getValue() {
		return data;
	}
	
	public Object setData(List<T> data) {
		this.data = data;
		return this;
	}
	
	public Table<T> withData(List<T> data) {
		this.setData(data);
		return this;
	}
	
	public Table<T> withColumn(String header, String property, int width) {
		this.table.addColumn(new TColumn(header, property, width));
		return this;
	}
	
	
}
