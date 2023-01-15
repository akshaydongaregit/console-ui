package com.consoleui.ui;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

import java.lang.reflect.Method;

public class TTable<T> {


	ArrayList<TColumn> colList = new ArrayList<TColumn>();

	public ArrayList<TColumn> getColList() {
		return colList;
	}

	public void setColList(ArrayList<TColumn> colList) {
		this.colList = colList;
	}
	public TTable<T> addColumn(TColumn col)
	{
		this.colList.add(col);		
		return this;
	}
	public TTable<T> addColumn(String header, String property, int width)
	{
		this.colList.add(new TColumn(header, property, width));		
		return this;
	}
	
	public void printHeader()
	{
		int total = drawHorizLine("\u001B[0m\u001B[47m");
		
		out.print("\n  \u001B[47m|");
		for(int i=0;i<colList.size();i++)
		{
			out.print("\u001B[0m\u001B[7m");
			TColumn col = colList.get(i);
			String data = col.getHeader();
			for(int j=0;j<col.getWidth();j++)
				if(j<data.length())
					out.print(data.charAt(j));
				else
					out.print(" ");
			
			out.print("|");
		}
		//out.print("|");
		out.print("\u001B[0m");
		drawHorizLine("\u001B[0m\u001B[47m");
	}
	
	private void printRow(ArrayList<String> list)
	{
		
		out.print("\n  |");
		for(int i=0;i<colList.size();i++)
		{
			TColumn col = colList.get(i);
			String data = list.get(i);
			
			for(int j=0;j<col.getWidth();j++)
				if(j<data.length())
					out.print(data.charAt(j));
				else
					out.print(" ");
			out.print("|");
		}
	}
	
	public int drawHorizLine(String color)
	{
		int total = 0;
		color = color != null? color: "";
		
		out.print("\n  "+color+"+");
		for(int i=0;i<colList.size();i++)
		{
			for(int j=0;j<colList.get(i).getWidth();j++)
				out.print("-");
			out.print("+");
		}	
		out.print("\u001B[0m");	
		return total;
	}
	
	public void printBean(T bean)
	{
		Class clazz = bean.getClass();
		Method[] methods = clazz.getMethods();
		ArrayList<String> list = new ArrayList<String>();
		
		
		for(int i=0;i<colList.size();i++)
		{
			String getter="get"+colList.get(i).getProperty();
			
			for(int j=0;j<methods.length;j++)
			{
				Method method = methods[j];
				if(method.getName().equalsIgnoreCase(getter))
					try {
						list.add(""+method.invoke(bean));
					} catch (Exception e){
						e.printStackTrace();
					}
			}
		}
		
		this.printRow(list);
		this.drawHorizLine("");
	}
	public void printBeans(List<T> beanList)
	{
		for(T item:beanList)
		{
			this.printBean(item);
		}
	}
	
}