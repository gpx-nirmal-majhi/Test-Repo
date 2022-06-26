package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;

public class Service3 extends AbstractVerticle {
    public static final String ADDRESS = "service1";

    @Override
    public void  start(){
        vertx.eventBus().consumer(ADDRESS, event -> {
            String message = (String) event.body();
            event.reply(message + "-[passed through service3]");
        });
    }

}
