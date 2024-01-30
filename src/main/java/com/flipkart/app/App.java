//package com.flipkart.app;
//
//import com.flipkart.controller.HelloRestController;
//import io.dropwizard.Application;
//import io.dropwizard.Configuration;
//import io.dropwizard.setup.Bootstrap;
//import io.dropwizard.setup.Environment;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**s
// * Hello world!
// *
// */
//public class App extends Application<Configuration> {
//    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
//
//    @Override
//    public void initialize(Bootstrap<Configuration> b) {
//    }
//
//    @Override
//    public void run(Configuration c, Environment e) throws Exception {
//        LOGGER.info("Registering REST resources");
//        //  e.jersey().register(new EmployeeRESTController(e.getValidator()));
//        e.jersey().register(new HelloRestController());
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        new App().run(args);
//    }
//}
package com.flipkart.app;

import com.flipkart.config.AppConfig;
import com.flipkart.controller.AdminController;
import com.flipkart.controller.CustomerController;
import com.flipkart.controller.FlipFitController;
import com.flipkart.controller.GymOwnerController;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<AppConfig> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) throws Exception {
        new App().run("server");
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
    }

    @Override
    public void run(AppConfig configuration, Environment environment) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        LOGGER.info("Registering REST resources");
        System.out.println("HERE");
        environment.jersey().register(new AdminController());
        environment.jersey().register(new CustomerController());
        environment.jersey().register(new GymOwnerController());
        environment.jersey().register(new FlipFitController());
    }
}