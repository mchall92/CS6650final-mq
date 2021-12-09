
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;

import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

class ServerThread extends Thread {

    private Socket client;
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        System.out.println("Receiving message from spring boot");
        String mess = "mess test";
        String groupId = "groupId test";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //Read messages from clients
            mess = br.readLine();
            System.out.println("Received msg: " + mess);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.setPrettyPrinting().create();

            MessageEntity m = gson.fromJson(mess, MessageEntity.class);

            System.out.println("Starting to send to mq");

        // start MQ

            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic(m.getGroupId().toString());

            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage message=session.createTextMessage(mess);
            producer.send(message);

            System.out.println("sent to mq");

        } catch (IOException | JMSException e) {
            e.printStackTrace();
        }
    }
}