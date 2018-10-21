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
        DB database = mongoClient.getDB("progresstracking-objects");
        DBCollection collection = database.createCollection(objects.getName(), null);
        BasicDBObject document = new BasicDBObject();
        for(Module mod : objects.getModules()) {
            document.put(mod.getLabel(), "test");
        }
        collection.insert(document);
        return "manoj";
    }



    @RequestMapping(value="/get_object", method=RequestMethod.GET)
    public Set<String> getCollection(@RequestParam("collectionName") String collectionName) {
        DB database = mongoClient.getDB("progresstracking-objects");
        System.out.println(collectionName);
        DBCollection collection = database.getCollection(collectionName);
        Set<String> set = collection.findOne().keySet();
        Set<String> output = new HashSet<>();
        for(String s: set) {
            if(!s.equals("_id")) {
                output.add(s);
            }
        }
        return output;
    }

    @RequestMapping("/delete_objects")
    public void deleteCollections() {
        DB database = mongoClient.getDB("progresstracking-objects");
        DBCollection collection = database.getCollection("collections");
        collection.drop();
    }

}
