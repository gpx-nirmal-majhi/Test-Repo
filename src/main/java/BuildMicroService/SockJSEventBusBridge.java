package BuildMicroService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.stomp.BridgeOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;

public class SockJSEventBusBridge extends AbstractVerticle {

    private  final Logger LOG =  LoggerFactory.getLogger(EchoServiceVerticle.class);

    @Override
    public void start(){
        LOG.info("started");
        configureEventBusBridge();
    }
    private void configureEventBusBridge(){
        Router router = Router.router(vertx);

        BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions().setAddress("events feed"));


        router.route("/everntbus/*").handler(SockJSHandler.create(vertx).bridge(options));

        router.route().handler(StaticHandler.create("web"));
        vertx.createHttpServer().requestHandler(router::accept).listen(9999);
    }

}
