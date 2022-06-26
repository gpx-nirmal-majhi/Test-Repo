package com.globalpayex;

import io.vertx.core.Future;
import io.vertx.rxjava.core.Promise;

public class service1 {
	
	public Future<String> getResponse(String reg)
	{
		Promise<String> responsefut = Promise.promise();
		new Thread(()->{
			try {
				Thread.sleep(2000);
			}
		catch (InterruptedException e) {
			e.printStackTrace();
			}
			responsefut.complete("Achyut");
		}).start();
		
		return responsefut.future();
	}

}
