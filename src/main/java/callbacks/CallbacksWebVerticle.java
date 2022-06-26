package callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import org.jgroups.blocks.RequestHandler;

//
public class CallbacksWebVerticle extends AbstractVerticle {
    private static final Logger LOG = LoggerFactory.getLogger(CallbacksWebVerticle.class);

    @Override
    public void start(){
        LOG.info("Starting CallbacksWebVerticle");
        vertx.createHttpServer().requestHandler(new RequestHandler().listen(9999));
    }


    private class RequestHandler implements Handler<HttpServerRequest>{

        // this handle the request like some request come to service1 , some come to service2
        // and then combine result to service3

        @Override
        public void handle(HttpServerRequest request){
            //send a msg to service1
            vertx.eventBus().send(Service1.ADDRESS, "message to service1", reply1->{
                String replyFromService1 = (String) reply1.result.body();

                //send a msg to service 2
                vertx.eventBus().send(Service2.ADDRESS,"message to service2",reply2 ->{
                    String replyFromService2 = (String) reply2.result.body();

                    //send the result from both service calls to service3
                    String combinedResult = replyFromService1 + " " +replyFromService2;
                    vertx.eventBus().send(Service3.ADDRESS, combinedResult, reply3->{
                        request.response().end("CallbacksWebVerticle - response from Service3 :",+ reply3.result().body());
                    });
                });
            });
        }
    }
}
