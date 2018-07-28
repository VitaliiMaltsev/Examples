package edu.javacourse.ejb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyJMSClient", urlPatterns = "/MyjmsClient")
public class MyJMSClient extends HttpServlet {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/myQueue")
    private Queue queue;


    private Logger log = LoggerFactory.getLogger(MyJMSClient.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < 20; i++) {
                message.setText("This is message " + (i + 1) + ". " + System.currentTimeMillis());
                log.debug("Sending message: " + message.getText());
                messageProducer.send(message);
                log.debug("message sent");
            }
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException ex) {
            ex.printStackTrace();
            log.debug(ex.getMessage());
        }
    }
}
