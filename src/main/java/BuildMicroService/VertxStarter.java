package BuildMicroService;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import static BuildMicroService.MyFirstVerticle.configureLogging;
//import static org.apache.ignite.internal.processors.platform.PlatformIgnition.configuration;

public class VertxStarter {


    public static void main(String[] args) {

        configureLogging();
//        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(new EchoServiceVerticle());
//        vertx.deployVerticle(new WebVerticle());


        VertxOptions options = new VertxOptions().setClustered(true).setClusterHost("localhost");

        Vertx.clusteredVertx(options, resultHandler ->{
            Vertx vertx = resultHandler.result();
            vertx.deployVerticle(new SockJSEventBusBridge());
            vertx.deployVerticle(new simpleVerticle());
            vertx.deployVerticle("js/simipleVerticle.js");

        });


    }
}
