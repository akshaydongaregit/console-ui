package com.consoleui.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.consoleui.app.Action;

public class Form implements Component {

	private String name;
	private String id;
	private Object submitResult;

	private Map<String, Component> childs = new LinkedHashMap<String, Component>();
	private Map<String, Action> actions = new LinkedHashMap<String, Action>();
	private Map<String, Consumer<Component>> actionsOnChilds = new HashMap<>();
	private List<String> errors = new ArrayList<String>();

	private Form self;

	public Form() {
		self = this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Component> getChilds() {
		return childs;
	}

	public void setChilds(Map<String, Component> childs) {
		this.childs = childs;
	}

	public void addChildren(Component child) {
		childs.put(child.getId(), child);
	}

	public Map<String, Action> getActions() {
		return actions;
	}

	public void setActions(Map<String, Action> actions) {
		this.actions = actions;
	}

	public void addAction(Action action) {
		actions.put(action.getCommand(), action);
	}

	public Action getActionByCommand(String command) {
		return this.actions.get(command);
	}

	public static Form withId(String id) {
		Form form = new Form();
		form.setId(id);
		return form;
	}

	public Form withName(String name) {
		this.setName(name);
		return this;
	}

	public Form withTextField(String id, String lable) {
		TextField field = new TextField();
		field.setId(id);
		field.setLable(lable);
		this.addChildren(field);
		return this;
	}
	
	public Form withTable(String id, Table table) {
		table.setId(id);
		this.addChildren(table);
		return this;
	}

	public Form onSubmit(Function<Form, Object> function) {
		this.addAction(Action.action("submit", (data) -> {
			submitResult = function.apply(self);
		}));
		return this;
	}

	public Form on(String command, Consumer<Form> action) {
		this.addAction(Action.action(command, (data) -> {
			action.accept(self);
		}));
		return this;
	}
	
	
	public Form withAction(Action action) {
		this.addAction(action);
		return this;
	}

	public Form actionOnChild(String id, Consumer action) {
		this.actionsOnChilds.put(id, action);
		return this;
	}

	public Form extendActionsFrom(Form form) {
		for(Map.Entry<String, Action> action:form.actions.entrySet())
			this.addAction(action.getValue());
		
		return this;
	}
	
	public void printHeader() {
		this.printHorizLine();
		System.out.print("\n+          \u001B[32m\u001B[0m\u001B[102m\u001B[1m    " + this.name + "    \u001B[0m           +");
		this.printHorizLine();
	}
	public void printBody(boolean clearErrors) {
		
		System.out.print("\n+                                                               +");

		//show errors if there any
		for (String error : errors) {
			System.out.print("\n+");
			System.out.printf("\u001B[31m"+" ! %s \u001B[0m",error);
		}
		if(errors.size()>0)
			System.out.print("\n+                                                               +");

		if(clearErrors)
			this.clearErrors();
		
		for (Map.Entry<String, Component> child : childs.entrySet()) {
			Component _child = child.getValue();
			if (child != null)
				_child.show();

			Consumer action = actionsOnChilds.get(child.getKey());
			if (action != null) {
				action.accept(_child.getValue());
			}
		}
		
	}
	public void printHorizLine() {
		System.out.print("\n+---------------------------------------------------------------+");	
	}
	
	public void show(boolean clearErrors) {
		if(this.name!=null) {
			this.printHeader();
		}
		
		if( (childs!=null & childs.size()>0 ) || (errors !=null & errors.size()>0 ) ) 
			printBody(clearErrors);
		
		this.printHorizLine();
		
		// choose and execute action
		int actionIndex = 1;
		List<Action> _actions = new ArrayList<>();

		if (actions.size() > 1) {
			System.out.print("\n+                                                               +");
			int index = 1;
			for (Map.Entry<String, Action> action : actions.entrySet()) {
				if (action.getValue() != null) {
					System.out.print("\n+  \u001B[106m\u001B[1m" + index + ".\u001B[0m Enter \u001B[1m" + index + "\u001B[0m to " + action.getKey());
					_actions.add(action.getValue());
					index++;
				}
			}

			System.out.print("\n+\n+  \u001B[1mChoose any of above option: \u001B[32m");
			Scanner in = new Scanner(System.in);
			actionIndex = in.nextInt();
			System.out.print("\n\u001B[0m+---------------------------------------------------------------+\n");

			Action action = _actions.get(actionIndex - 1);
			if (action != null)
				action.act();
		} else
			for (Map.Entry<String, Action> _action : actions.entrySet()) {
				_action.getValue().act();
				return;
			}

	}

	public void show() {
		this.show(true);
	}
	
	public void addError(String error) {
		this.errors.add(error);
	}

	public void clearErrors() {
		this.errors.clear();
	}

	
	@Override
	public Object getValue() {
		Map<String, Object> values = new HashMap<>();
		for (Map.Entry<String, Component> child : this.childs.entrySet())
			values.put(child.getKey(), child.getValue().getValue());
		return values;
	}
}
