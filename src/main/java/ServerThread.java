import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.*;
import java.net.Socket;

class ServerThread extends Thread {

    private Socket client;
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client: " + client.getInetAddress().getLocalHost() + "Connected to server");
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //Read messages from clients
            String mess = br.readLine();
            System.out.println(mess);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // start MQ
        try {
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("1");

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage message=session.createTextMessage("test mq");
            producer.send(message);

            System.out.println("send to mq");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}