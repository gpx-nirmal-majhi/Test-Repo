package Practice;


//verticle is the code that we write

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.Random;
import java.util.UUID;

public class SensorVerticle extends AbstractVerticle {
    public static final Logger logger = LoggerFactory.getLogger(SensorVerticle.class);
    private static final int httpPort = Integer.parseInt(System.getenv().getOrDefault("HTTP_PORT", "8080"));

    private final String uuid = UUID.randomUUID().toString();
    private int temperature = 21;
    private final Random random = new Random();
    private io.vertx.core.Vertx Vertx;

    @Override
    public  void start(Promise<Void> startPromise){
        vertx.setPeriodic(2000, this::updateTemprature);

        startPromise.complete();

        // Router object define the http endpoint based on the path
        // where should it will go and path to which follow and method
        Router router = Router.router(Vertx );
        // when data will trigger than getData method will handle the request
        // what should we return
        router.get("/data").handler(this::getData);

        //this will create http server and handle the request
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(httpPort)
                .onSuccess(ok->{
                    //this will give information about the http port
                    logger.info("http server running http://127.0.0.1:{}");
                    startPromise.complete();// this will called when the verticle is complete

                })
                .onFailure(startPromise::fail);


    }

    private void getData(RoutingContext context) {
//        logger.info("procesing Http request from {}",context.request().remoteAddress());

        JsonObject payload = new JsonObject().put("uuid", uuid)
                .put("temprature", temperature)
                .put("timestamp",System.currentTimeMillis());


        context.response()
                .putHeader("Content-Type","application/json")
                .setStatusCode(200)
                .end(payload.encode());
    }

    private void updateTemprature(Long id){
        // generate the temperature with some extra
        temperature = (int) (temperature + (random.nextGaussian()/2));
        logger.info("temperature updated: {}");

    }





}
