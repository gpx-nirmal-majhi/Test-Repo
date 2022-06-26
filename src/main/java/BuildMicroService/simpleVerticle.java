package BuildMicroService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;
import org.graalvm.compiler.lir.aarch64.AArch64LIRFlagsVersioned;

public class simpleVerticle extends AbstractVerticle{

    @Override
    public void start(){
        vertx.setPeriodic(1000, t->vertx.eventBus().publish("events-feed","server event from java"));
    }


}
