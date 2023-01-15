package com.consoleui.app;

import java.util.function.Consumer;

public class Action {
	
	public Consumer action;
	public String command;
	
	public Consumer getAction() {
		return action;
	}

	public void setAction(Consumer action) {
		this.action = action;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	};
	
	public void act(Object data) {
		this.action.accept(data);
	}
	
	public void act() {
		this.action.accept(null);
	}
	
	public static Action action(String command, Consumer _action) {
		Action action = new Action();
		action.setCommand(command);
		action.setAction(_action);
		return action;
	}
}
