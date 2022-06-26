package com.globalpayex;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.rxjava.core.Promise;
import io.vertx.core.eventbus.*;
import io.vertx.redis.client.Request;

public class service2v {
	
	public Future<String> getResponse(String reg , Vertx vertx)
	{
		Promise<String> responsefut = Promise.promise();
		try {
			vertx.eventBus().request("vert2", reg,resp->{
				if(resp.succeeded())
				{
					responsefut.complete(reg);
				}
				else
					responsefut.complete("Error");
			});
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		
		
		return responsefut.future();
	}

}
