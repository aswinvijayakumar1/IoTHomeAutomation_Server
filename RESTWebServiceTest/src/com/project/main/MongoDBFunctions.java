package com.project.main;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.project.model.DeviceModel;

public class MongoDBFunctions {

	Date date = new Date();
	MongoClient mongoClient = new MongoClient("mongodb://diptic:I0tproject12$@ds051831.mongolab.com:51831/iot");	
	MongoDatabase db = mongoClient.getDatabase("iot");
	MongoCollection<Document> mongoColl = db.getCollection("deviceDetails");
	
	public void insertNewDocument(DeviceModel device) {
		
		Document doc = new Document("id", device.getId())
	    .append("temperature", device.getTemperature())
	    .append("date", device.getDate());
		
		mongoColl.insertOne(doc);
	}
	
	public void getDocument(String id) {
		Document doc = new Document("id", id);
		FindIterable<Document> cursor = mongoColl.find(doc);
		System.out.println(cursor.first());
	}
		
	public void getAllDocuments() {
		FindIterable<Document> iterable = mongoColl.find();
		MongoCursor<Document> cursor = iterable.iterator();
		
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	}
}