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
import java.io.PrintWriter;

/**
 * Author: Georgy Gobozov
 * Date: 07.07.13
 */
@WebServlet(name = "JMSClient", urlPatterns = "/jmsClient")
public class JMSClient extends HttpServlet {

    private Logger log = LoggerFactory.getLogger(JMSClient.class);

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/queue/test")
    private Queue queue;

    @Resource(mappedName = "java:/topic/test")
    private Topic topic;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Servlet started!");

        final String destination = request.getParameter("dest");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<html>");
            out.println("<head>");
            out.println("<title>FirstServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href = '/jms-war/jmsClient?dest=queue'>start queue</a></br>");
            out.println("<a href = '/jms-war/jmsClient?dest=topic'>start topic</a></br>");
            out.println("</body>");
            out.println("</html>");
        }
        if ("queue".equals(destination)) {
            sendMessageToQueue();
        } else if ("topic".equals(destination)) {
            sendMessageToTopic();
        } else {
            response.getWriter().write("'dest' parameter need to be provided. queue/topic");


            log.debug("Servlet finished!");
        }
    }

    public void sendMessageToQueue() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < 20; i++) {
                message.setText("Queue message " + (i + 1) + ". " + System.currentTimeMillis());
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

    public void sendMessageToTopic() {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);
            TextMessage message = session.createTextMessage();
            for (int i = 0; i < 20; i++) {
                message.setText("Topic message " + (i + 1) + ". " + System.currentTimeMillis());
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
