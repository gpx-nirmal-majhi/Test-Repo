package BuildMicroService;

//// Anatomy of microservice
//   1. small in size , single responsibility ---->
//   2. runs in itw own process----> put several verticle in one JVM and definitly run its own process
//   3. has its own data store----->use mongo, sql, oracle it store data
//   4. distributed by default------>yes eventbus is by defualt distributed
//   5. independently develop , deploy, upgrade, scale-->
//   6. potentially heterogeneous/polyglot----> run in many langause
//   7. light - wight communication---> use jsonObject which is fairly light weight

// REACTIVE -- Responsive
//            , Resilent-- stay responsive in case of failure
//            , Elastic -- scalable and maintainable
//            , Message Driven -- Asynchronous message passing between stablish component


// EVERY THING IN VERT.X IS EVENT
// => event - driven ( http request is an event , message btween verticle are event
// and to handle all this vert.x use event loop WHICH CHECK IS THERE ANY EVENT PROCESSING OR NOT AGAIN AND AGAIN AND
// AND fetch the event if there is and send it to handler to handle this
// So vertex event loop have a thread and running and check wheather there is event or not to send it to handler
// this reactive thing handle a lot of event using very less no. of thread
// in between the handler process the event loop  thread will run and check wheater is there any other event is coming or not

// verticle has multireactive pattern so and typically fireup one event loop per core and imporve the cpu utilisation of core
// with out firing up extra application
// most important ONE HANDLER WILL HANDLE BY SAME EVENT LOOP AT A TIME
// so this provide concurrency single


//// EVENT BUS
//both request/response and publish/subscribe
//when need to send to speecific address and take response
//// verticles are communicating by messages and messgaes are received buy handler

//NOTICE:
//never ever ever block the event loop
// need to to blocking stuff? use a worker
// if we need to block the event loop then we need to send the message to Worker and event loop handler will stop working
// then when come response from Worker to event loop handler and non blocking handler working





//public class Basic {
//}
