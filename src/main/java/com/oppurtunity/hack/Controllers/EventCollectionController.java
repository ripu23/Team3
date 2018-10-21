package com.oppurtunity.hack.Controllers;

import com.mongodb.*;
import com.oppurtunity.hack.entities.EventWrapper;
import com.oppurtunity.hack.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class EventCollectionController {

	@Autowired
	private MongoClient mongoClient;

	@RequestMapping(value="/createEvent", consumes = "application/json")
	public ResponseEntity<Object> createCollection(@RequestBody EventWrapper objects) {
		try {
			System.out.println("event creation");
			DB database = mongoClient.getDB("progresstracking-events");
			DBCollection collection = database.createCollection(objects.getModuleName(), null);
			BasicDBObject document = new BasicDBObject();
			document.put("object", "test");
			for(Module mod : objects.getAttributes()) {
				document.put(mod.getLabel(), "test");
			}
			collection.insert(document);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@RequestMapping("/get_all_events")
	public List<DBObject> getCollection() {
		DB database = mongoClient.getDB("progresstracking-events");
		DBCollection collection = database.getCollection("collections");
		DBCursor cursor = collection.find();
		List<DBObject> objects = new ArrayList<>();
		while(cursor.hasNext()) {
			objects.add(cursor.next());
		}
		return objects;
	}

	@RequestMapping(value="/get_event", method= RequestMethod.GET)
	public Set<String> getCollection(@RequestParam("eventName") String eventName) {
		DB database = mongoClient.getDB("progresstracking-events");
		System.out.println(eventName);
		DBCollection collection = database.getCollection(eventName);
		Set<String> set = collection.findOne().keySet();
		Set<String> output = new HashSet<>();
		for(String s: set) {
			if(!s.equals("_id")) {
				output.add(s);
			}
		}
		return output;
	}
}
