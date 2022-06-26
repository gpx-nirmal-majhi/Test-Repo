package com.book_vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.rxjava.core.Vertx;

public class BookVertx extends AbstractVerticle {
	
	private long counter = 1;
	
	@Override
	public void start(Promise<Void> promise) throws Exception
	{
		
		
		vertx.createHttpServer().requestHandler(c ->{
			c.response().end("Hello");
			
		}).listen(8000,ar -> {
			if(ar.succeeded())
			{
				promise.complete();
			}
			else
			{
				promise.fail(ar.cause());
			}
		});
	}
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new BookVertx());
	}

}
