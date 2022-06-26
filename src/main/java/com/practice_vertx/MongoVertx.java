//package com.practice_vertx;
//
//import io.vertx.core.AbstractVerticle;
//import io.vertx.core.Future;
//import io.vertx.core.Promise;
//import io.vertx.core.json.JsonObject;
//import io.vertx.ext.mongo.MongoClient;
//import io.vertx.ext.web.Router;
//import io.vertx.ext.web.RoutingContext;
//import io.vertx.core.Vertx;
//
//import static io.vertx.core.http.impl.HttpClientConnection.log;
//
//public class MongoVertx extends AbstractVerticle {
//
//	private MongoClient mongoClient;
//
//	@Override
//	public void start(Promise<Void> promise)
//	{
//		System.out.println("Welcome to Vertx");
//		prepareMongoDB().compose(ar-> createServer()).onSuccess(ar-> {
//	            if (ar.succeeded()) {
//	                future.complete();
//	            } else {
//	                log.error("Application failed to start {} ", ar.cause());
//	                future.fail(ar.cause());
//	            }
//	        });
//
//
//	}
//
//	@Override
//	public void stop() {
//		System.out.println("Shutting down application");
//	}
//
//	public static void main(String[] args) {
//		Vertx vertx = Vertx.vertx();
//		vertx.deployVerticle(new MongoVertx(), res ->{
//			if(res.succeeded())
//				System.out.println("Deployment id is:" + res.result());
//			else
//				System.out.println("Deployment failed");
//		});
//	}
//
//	private Future<Void> prepareMongoDB()
//	{
//		Future<Void> future = Future.future(null);
//
//
//		mongoClient = MongoClient.createShared(vertx, Config);
//		mongoClient.getCollections( ar -> {
//			if(ar.succeeded())
//				future.isComplete();
//			else
//				future.failed();
//		});
//
//		return future;
//	}
//
//	private Future<Void> createServer()
//	{
//		final Future<Void> future = Future.future(null);
//		final Router router = Router.router(vertx);
//
//		router.route("/x").handler( c-> {
//
//			c.response().end("working");
//		});
//
//		vertx.createHttpServer().requestHandler(router)
//		.listen(8000, res ->{
//			if(res.succeeded()) {
//				System.out.println("HTTP server running on port 8000");
//				future.isComplete();}
//			else
//			{
//				System.out.println("Could not start a HTTP server"+res.cause());
//				future.failed();
//			}
//
//
//		});
//		return future;
//
//	}
//
//
//
//}
