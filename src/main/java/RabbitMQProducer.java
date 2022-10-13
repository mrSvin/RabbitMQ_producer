import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMQProducer {

    String userName;
    String host;
    int port;

    public RabbitMQProducer(String userName, String host, int port) {
        this.userName = userName;
        this.host = host;
        this.port = port;
    }


    public void sendMessage(String message) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(userName);
        factory.setPassword(userName);
        factory.setVirtualHost("/");
        factory.setHost(host);
        factory.setPort(port);
        try (
                Connection conn = factory.newConnection();
                Channel channel = conn.createChannel()
        ) {

            String exchangeName = "myExchange";
            String routingKey = "testRoute";

            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
