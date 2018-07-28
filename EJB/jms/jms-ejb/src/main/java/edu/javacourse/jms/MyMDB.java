package edu.javacourse.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "MyMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/myQueue")
})
public class MyMDB implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MyMDB.class);

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        try {
            log.debug("Message :{}",textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
