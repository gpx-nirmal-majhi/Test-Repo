package BuildMicroService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
//import org.slf4j.Logger;

public class EchoServiceVerticle extends AbstractVerticle {
    public static final String ADDRESS = "Echo - service";
    private  final Logger LOG =  LoggerFactory.getLogger(EchoServiceVerticle.class);

    @Override
    public void start(){
        LOG.info("starting echo Service");

        vertx.eventBus().consumer(EchoServiceVerticle.ADDRESS, message ->{
            JsonObject messageBody = (JsonObject) message.body();
            LOG.info("Received message :"+ messageBody);

            messageBody.put("passedThrough","echo - service");
            LOG.info("Sending reply:" + messageBody);

            message.reply(messageBody);
        });
    }
}
