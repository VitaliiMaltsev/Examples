package edu.javacourse.spring.ioc;

import edu.javacourse.spring.ioc.beans.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Artem Pronchakov | email/xmpp: artem.pronchakov@calisto.email
 */
public class SimpleBeanExample {

    private static Logger log = LoggerFactory.getLogger(SimpleBeanExample.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"springContext.xml"});

        log.debug("\n\n\n");

        final Car car = context.getBean("car", Car.class);
        log.debug("Car model: {}", car.getId());
        log.debug("Car owner: {}", car.getOwner().getName());
        log.debug("Car Model: {}", car.getModel());
        log.debug("Car LastService: {}", car.getLastService());
        log.debug("Car Year: {}", car.getYear());
//        log.info("Car Owner: {}", car.getOwner().getName());
//        log.debug("\n\n\n");
//        final Car cart = context.getBean("cart", Car.class);
//        log.debug("Car model: {}", cart.getId());
//        log.debug("Car Model: {}", cart.getModel());
//        log.debug("Car LastService: {}", cart.getLastService());
//        log.debug("Car Year: {}", cart.getYear());
//        log.info("Car Owner: {}", cart.getOwner().getName());

    }

}
