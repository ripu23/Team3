package com.oppurtunity.hack.Controllers;

import com.mongodb.*;
import com.oppurtunity.hack.entities.EventWrapper;
import com.oppurtunity.hack.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

public class EventCollectionController {

	@Autowired
	private MongoClient mongoClient;

	@RequestMapping(value="/create_events", consumes = "application/json")
	public String createCollection(@RequestBody EventWrapper objects) {
		System.out.println("event creation");
		DB database = mongoClient.getDB("progresstracking");
		DBCollection collection = database.getCollection("collections");
		BasicDBObject document = new BasicDBObject();
		document.put("eventname", objects.getEventName());
		for(Module mod : objects.getEventmodules()) {
			document.put(mod.getId(), mod.getLabel());
		}
		collection.insert(document);
		return "manoj";
	}

	@RequestMapping("/get_all_events")
	public List<DBObject> getCollection() {
		DB database = mongoClient.getDB("progresstracking");
		DBCollection collection = database.getCollection("collections");
		DBCursor cursor = collection.find();
		List<DBObject> objects = new ArrayList<>();
		while(cursor.hasNext()) {
			objects.add(cursor.next());
		}
		return objects;
	}
}
