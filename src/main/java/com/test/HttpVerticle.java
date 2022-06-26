package com.test;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class HttpVerticle extends AbstractVerticle {

	
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		 
		Router router = Router.router(vertx);
		router.get("/test")
		  .handler(this::testRouter);
		
		vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
			if (http.succeeded()) {
				startPromise.complete();
				System.out.println("HTTP server started on port 8888");
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
	
	private void testRouter(RoutingContext ctx) {
//		MongoTest mt=new MongoTest(vertx);
		MongoTest m2=new MongoTest(vertx);
		m2.insert("rr");
		ctx.response().putHeader("content-type", "text/plain").end("insert executed!");
	  }

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new HttpVerticle());
	}
}