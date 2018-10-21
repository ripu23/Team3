package com.oppurtunity.hack.Controllers;

import com.oppurtunity.hack.entities.ModuleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mongodb.*;
import java.util.*;
import com.oppurtunity.hack.entities.Module;

@RestController
public class ObjectCollectionController {

    @Autowired
    private MongoClient mongoClient;

    @RequestMapping(value="/create_object", consumes = "application/json")
    public String createCollection(@RequestBody ModuleWrapper objects) {
        DB database = mongoClient.getDB("progresstracking");
        DBCollection collection = database.getCollection("collections");
        BasicDBObject document = new BasicDBObject();
        document.put("name", objects.getModuleName());
        for(Module mod : objects.getAttributes()) {
            document.put(mod.getId(), mod.getLabel());
        }
        collection.insert(document);
        return "manoj";
    }

    @RequestMapping("/get_all_objects")
    public List<DBObject> getCollections() {
        DB database = mongoClient.getDB("progresstracking");
        DBCollection collection = database.getCollection("collections");
        DBCursor cursor = collection.find();
        List<DBObject> objects = new ArrayList<>();
        while(cursor.hasNext()) {
            objects.add(cursor.next());
        }
        return objects;
    }

    @RequestMapping(value="/get_object", method=RequestMethod.GET)
    public DBObject getCollection(@RequestParam("collectionName") String collectionName) {
        DB database = mongoClient.getDB("progresstracking");
        DBCollection collection = database.getCollection("collections");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("name", collectionName);
        DBCursor cursor = collection.find(whereQuery);
        List<DBObject> objects = new ArrayList<>();
        while(cursor.hasNext()) {
            objects.add(cursor.next());
        }
        return objects.get(0);
    }

    @RequestMapping("/delete_objects")
    public void deleteCollections() {
        DB database = mongoClient.getDB("progresstracking");
        DBCollection collection = database.getCollection("collections");
        collection.drop();
    }

}
