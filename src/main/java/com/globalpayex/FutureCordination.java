package com.globalpayex;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.NetServer;
import io.vertx.ext.web.Router;

public class FutureCordination extends AbstractVerticle {
	
	public HttpServer httpserver1;
	public HttpServer httpserver2;
	
	@Override
	public void start(Promise<Void> startPromise) throws Exception
	{
		httpserver1 = vertx.createHttpServer();
		httpserver2 = vertx.createHttpServer();
		
		Router httpRouter1 = Router.router(vertx);
		Router httpRouter2 = Router.router(vertx);
		
		httpRouter1.route().handler(c -> {
			HttpServerResponse response = c.response();
			response.putHeader("name", "HttpAchyut");
			response.end("Http1 Working");
		});
		
		httpRouter2.route().handler(c -> {
			HttpServerResponse response = c.response();
			response.putHeader("name", "HttpAchyut");
			response.end("Http2 Working");
		});
		
		httpserver1.requestHandler(httpRouter1);
		httpserver2.requestHandler(httpRouter2);
		
		Future<HttpServer> server1 = httpserver1.listen(8000);
		Future<HttpServer> server2 = httpserver2.listen(8001);
		
		CompositeFuture.all(server1, server2).onComplete(ar -> {
			  if (ar.succeeded()) {
			    System.out.println("All instance Server are working");
			    startPromise.complete();
			  } else {
			   startPromise.fail("Not working");
			   System.out.println("Not all instance server are working");
			    
			  }
			});
		
		
		
	}
	 public static void main(String[] args) {
		  Vertx.vertx().deployVerticle(new FutureCordination(),deployHandler -> {
			  if(deployHandler.succeeded()){
                 System.out.println
                         (" Verticle Deployed");
             } else {
                 System.out.println
                         (deployHandler.cause().getMessage());
             }
		  });
	}

}
