package com.test;

import java.util.List;
import java.util.ArrayList;

import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class MongoTest{

	MongoClient mongoClient;
	public MongoTest(Vertx vertx) {		
		final JsonObject Config  = new JsonObject()
				.put("connection_string", "mongodb://localhost:27017")
				.put("db_name", "database");
		mongoClient = MongoClient.createShared(vertx, Config);
	}

	public void insert(String name) {
		int n = 20;
		Integer k =0 ;
		List<JsonObject> arr = new ArrayList<JsonObject>(n);
		for(JsonObject x : arr)
		{
			x.put("name", k.toString()).put("_id",k.toString());
			++k;
		}
		
		
			try {
				Future<String> result= mongoClient.insert("trainingTest5", new JsonObject().put("name", name).put("_id",name));
				result.onComplete(res->{
					System.out.println(res.result());
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		Future<String> future=this.execute("fail");
		future.onSuccess(res->{
			System.out.println("success");
		}).onFailure(res->{
			System.out.println("failed");			
		});
		System.out.println("completed");
	}
	
	public Future<String> execute(String param){
		Promise<String> promise= Promise.promise();
		//logic
		for(int i=0;i<10000;i++) {
			
	
		}
		if(param.equals("success")) {
			promise.complete("success");			
		}else {
			promise.fail("failed due to error");			
		}
		
		return promise.future();
	}
	
public String executeStr(){
		String s="test";
		
		return s;
	}
}
