package BuildMicroService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFirstVerticle extends AbstractVerticle {

    static {
        configureLogging();
    }

    static void configureLogging() {
    }

    private static final Logger LOG = LoggerFactory.getLogger(MyFirstVerticle.class);


    public static void main(String[] args) {


        Vertx.vertx()
                .createHttpServer()
                .requestHandler(request ->{

                         LOG.info("Starting embedded Vert.x");
            request.response().end("hello world from java with emebeded vert.x");

        }).listen(8080);

    }

    }

