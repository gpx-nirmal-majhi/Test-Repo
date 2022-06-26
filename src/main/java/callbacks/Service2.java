package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;

public class Service2 extends AbstractVerticle {
    public static final String ADDRESS = "service1";

    @Override
    public void  start(){
        vertx.eventBus().consumer(ADDRESS, event -> event.reply("Reply from service2"));
    }

}
