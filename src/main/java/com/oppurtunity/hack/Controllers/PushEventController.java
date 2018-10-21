package com.oppurtunity.hack.Controllers;

import com.mongodb.*;
import com.oppurtunity.hack.entities.DataWrapper;
import com.oppurtunity.hack.entities.EventWrapper;
import com.oppurtunity.hack.entities.Module;
import com.oppurtunity.hack.entities.ObjectDataModule;
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
    public ResponseEntity createCollection(@RequestBody DataWrapper objects) {
        DB database = mongoClient.getDB("progresstracking-events");
        DBCollection eventCollection = database.getCollection(objects.getEventDataWrapper().getModuleName());
        DBCollection objectCollection = database.getCollection(objects.getObjectDataWrapper().getModuleName());

        //event mapping
        BasicDBObject document1 = new BasicDBObject();
        document1.put("object", objects.getObjectDataWrapper().getModuleName());
        for(ObjectDataModule mod : objects.getEventDataWrapper().getAttributes()) {
            document1.put(mod.getLabel(), mod.getValue());
        }
        eventCollection.insert(document1);

        // object mapping
        BasicDBObject document2 = new BasicDBObject();
        for(ObjectDataModule mod : objects.getObjectDataWrapper().getAttributes()) {
            document2.put(mod.getLabel(), mod.getValue());
        }
        objectCollection.insert(document2);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping("/getAllEvents")
    public Set<String> getCollections() {
        DB database = mongoClient.getDB("progresstracking-events");
        Set<String> colls = database.getCollectionNames();
        return colls;
    }

    @RequestMapping(value="/getEventDetails", method= RequestMethod.GET)
    public Set<String> getCollection(@RequestParam("moduleName") String eventName) {
        DB database = mongoClient.getDB("progresstracking-events");
        System.out.println(eventName);
        DBCollection collection = database.getCollection(eventName);
        Set<String> set = collection.findOne().keySet();
        Set<String> output = new HashSet<>();
        for(String s: set) {
            if(!s.equals("_id") && !s.equals("object") && !s.equals("uniqueId")) {
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
