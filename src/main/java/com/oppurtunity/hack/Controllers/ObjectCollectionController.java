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
        System.out.println("donation");
        DB database = mongoClient.getDB("progresstracking");
        DBCollection collection = database.getCollection("collections");
        BasicDBObject document = new BasicDBObject();
        document.put("name", objects.getName());
        for(Module mod : objects.getModules()) {
            document.put(mod.getId(), mod.getLabel());
        }
        collection.insert(document);
        return "manoj";
    }

    @RequestMapping("/get_all_objects")
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
