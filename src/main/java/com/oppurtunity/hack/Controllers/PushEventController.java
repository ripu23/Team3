package com.oppurtunity.hack.Controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.oppurtunity.hack.entities.EventWrapper;
import com.oppurtunity.hack.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/get_all_events")
    public Set<String> getCollections() {
        DB database = mongoClient.getDB("progresstracking-events");
        Set<String> colls = database.getCollectionNames();
        return colls;
    }
}
