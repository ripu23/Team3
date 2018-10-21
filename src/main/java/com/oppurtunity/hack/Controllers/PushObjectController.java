package com.oppurtunity.hack.Controllers;

import com.mongodb.*;
import com.oppurtunity.hack.entities.Module;
import com.oppurtunity.hack.entities.ModuleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/object")
public class PushObjectController {

    @Autowired
    private MongoClient mongoClient;

    @RequestMapping(value="createObject", consumes = "application/json")
    public ResponseEntity createCollection(@RequestBody ModuleWrapper objects) {
        DB database = mongoClient.getDB("progresstracking-objects");
        DBCollection collection = database.getCollection(objects.getModuleName());
        BasicDBObject document = new BasicDBObject();
        for(Module mod : objects.getAttributes()) {
            document.put(mod.getId(), mod.getLabel());
        }
        collection.insert(document);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @RequestMapping("/getObjectDetails")
    public Set<String> getCollection(@RequestParam("moduleName") String moduleName) {
        DB database = mongoClient.getDB("progresstracking-objects");
        DBCollection collection = database.getCollection(moduleName);
        Set<String> set = collection.findOne().keySet();
        Set<String> output = new HashSet<>();
        for(String s: set) {
            if(!s.equals("_id")) {
                output.add(s);
            }
        }
        return output;
    }

    @RequestMapping("/getAllObjects")
    public Set<String> getCollections() {
        DB database = mongoClient.getDB("progresstracking-objects");
        Set<String> colls = database.getCollectionNames();
        return colls;
    }
}
