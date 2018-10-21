package com.oppurtunity.hack.Controllers;

import com.mongodb.*;
import com.oppurtunity.hack.entities.EventWrapper;
import com.oppurtunity.hack.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/event")
public class PushEventController {

    @Autowired
    private MongoClient mongoClient;

    @RequestMapping(value="createEvent", consumes = "application/json")
    public ResponseEntity createCollection(@RequestBody EventWrapper objects) {
        DB database = mongoClient.getDB("progresstracking-events");
        DBCollection collection = database.getCollection(objects.getModuleName());
        BasicDBObject document = new BasicDBObject();
        document.put("object", objects.getObjectName());
        for(Module mod : objects.getAttributes()) {
            document.put(mod.getId(), mod.getLabel());
        }
        collection.insert(document);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping("/getAllEvents")
    public Set<String> getCollections() {
        DB database = mongoClient.getDB("progresstracking-events");
        Set<String> colls = database.getCollectionNames();
        return colls;
    }

    @RequestMapping(value="/getEventDetails", method= RequestMethod.GET)
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

    @RequestMapping(value="/getEventsForObject", method= RequestMethod.GET)
    public Map<String, Set<String>> getMapping(@RequestParam("objectName") String objectName) {
        DB database = mongoClient.getDB("progresstracking-events");
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> output = new HashSet<>();
        Set<String> collections = database.getCollectionNames();
        for(String collection : collections) {
            DBCollection dBcollection = database.getCollection(collection);
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("object", objectName);
            DBCursor cursor = dBcollection.find(whereQuery);
            if(cursor.size() > 0) {
                output.add(dBcollection.getName());
            }
        }
        map.put(objectName, output);
        return map;
    }
}
