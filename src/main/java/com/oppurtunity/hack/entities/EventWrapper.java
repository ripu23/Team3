package com.oppurtunity.hack.entities;

import java.util.List;

public class EventWrapper {
	private String eventName;
	private String objectName;
	private List<Module> eventmodules;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

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
