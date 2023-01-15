package com.consoleui.app;

import java.util.HashMap;
import java.util.Map;

public class App {

	private Map<String,Action> actions = new HashMap<>();
	private Action defualtAction = null;
	
	public Map<String,Action> getActions() {
		return actions;
	}
	
	public Action getActionByCommand(String command) {
		return actions.get(command);
	}
	
	public void setActions(Map<String,Action> actions) {
		 this.actions = actions;
	}
	
	public void addAction(Action action) {
		actions.put(action.getCommand(),action);
	}
	
	public static App with() {
		App app = new App();
		return app;
	}

	public static App withDefaultAction(Action action) {
		App app = new App();
		app.addAction(action);
		app.defualtAction = action;
		return app;
	}
	
	public App withAction(Action action) {
		this.addAction(action);
		return this;
	}
	
	public App defaultAction(Action action) {
		this.addAction(action);
		return this;
	}
	
	public void start() {
		if(this.defualtAction!=null) {
			this.defualtAction.act();
		}
	}
	public void act(String actionCommand, Object data) {
		Action action = this.actions.get(actionCommand);
		if(action!=null)
			action.act(data);
	}
	
	public void act(String actionCommand) {
		this.act(actionCommand,null);
	}
	
}
