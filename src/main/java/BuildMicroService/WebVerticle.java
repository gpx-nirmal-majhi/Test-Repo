package BuildMicroService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import org.slf4j.ILoggerFactory;

public class WebVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(WebVerticle.class);

    @Override
    public void start(){
        LOG.info("Starting the webVerticle");
        vertx.createHttpServer().requestHandler(request -> {
            // this below message is send to the
            // EchoServiceVerticle
            JsonObject message = new JsonObject()
                    .put("text", "helloWorld")
                    .put("queryString",request.query());

            LOG.info("sending mesagage" + message);
            
            
// below WebVerticle is used to communicate with the EchoServiceVerticle 
            // eventbus send the message from the webverticles to echoserviceVerticla 
            // and after processing it will reply some msg back to eventbus and this msg will go to http reply
            // use the vertx API to send msg
            // So after sending the msg to EchoserviceVerticles the webserviceverticle event loop waiting for
            // the response come back from the EchoSERviceVerticle
            vertx.eventBus().send(EchoServiceVerticle.ADDRESS, message,reply ->{
                JsonObject replyBody = (JsonObject) reply.result().body();
                LOG.info("Received reply : "+ replyBody);

                request.response().end(replyBody.encodePrettily());
            });
        });
    }

}
