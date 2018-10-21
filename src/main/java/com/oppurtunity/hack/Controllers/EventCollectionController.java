//package com.oppurtunity.hack.Controllers;
//
//import com.mongodb.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EventCollectionController {
//
//    @Autowired
//    private MongoClient mongoClient;
//
//    @RequestMapping(value="/create_object", method= RequestMethod.GET)
//    public String createCollection(@RequestParam("collection_name") String collectionName, @RequestParam Json json) {
//        System.out.println(collectionName);
//        DB database = mongoClient.getDB("progresstracking");
//        DBCollection collection = database.getCollection("collections");
//        BasicDBObject document = new BasicDBObject();
//        document.put("name", "donation");
//        document.put(json);
//        collection.insert(document);
//        return "donation";
//    }
//
//    @RequestMapping("/get_all_objects")
//    public List<DBObject> getCollection() {
//        DB database = mongoClient.getDB("progresstracking");
//        DBCollection collection = database.getCollection("collections");
//        DBCursor cursor = collection.find();
//        List<DBObject> objects = new ArrayList<>();
//        while(cursor.hasNext()) {
//            objects.add(cursor.next());
//        }
//        return objects;
//    }
//}
