package com.oppurtunity.hack.entities;

import java.util.List;

public class EventWrapper {
	private String eventName;
	private List<Module> eventmodules;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public List<Module> getEventmodules() {
		return eventmodules;
	}
	public void setEventmodules(List<Module> eventmodules) {
		this.eventmodules = eventmodules;
	}
	
	
}
