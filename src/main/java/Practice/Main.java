package Practice;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();


        // so here we can create more than one verticle of sensorVerticle and per thread one verticle
        // so parallel processing is improved
        vertx.deployVerticle(new SensorVerticle());
//        vertx.deployVerticle("SensorVerticle", new DeploymentOptions().setInstances(4));




    }
}
