package com.globalpayex;

import io.vertx.core.VertxOptions;
import io.vertx.core.*;
import io.vertx.core.eventbus.Message;

public class Verticle1 extends AbstractVerticle {
	
	public void onMessage(Message<String> msg)
	{
		String msgBody = msg.body();
		System.out.println("In coming request" + msgBody);
		msg.reply("Achyut");
	}
	@Override
	public void start(Promise<Void> promise) throws Exception{
		vertx.eventBus().consumer("vert1",this::onMessage);
	}

	
	public static void main(String[] args) {
		VertxOptions options = new VertxOptions();
		Vertx.clusteredVertx(options, c->{
			if(c.succeeded()) {
				c.result().deployVerticle(new Verticle1(), res ->
				{
					if(res.succeeded())
						System.out.println("----deployed");
					else
						System.out.println("----error");
				});	}
			else
				System.out.println("failed");
		});
		
	}
	

}
