package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class CallbacksVertxStarter extends AbstractVerticle {


    public static void main(String[] args) {

        Vertx vertx1 = Vertx.vertx();

        vertx1.deployVerticle(new Service1());
        vertx1.deployVerticle(new Service2());
        vertx1.deployVerticle(new Service3());

        vertx1.deployVerticle(new CallbacksVertxStarter());


    }
}
