import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Main {

    //Выполняет разовую отправку ресиверу через агент RabbitMQ заданное количество сообщений
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String exchangeName = "myExchange";
        String routingKey = "testRoute";

        for (int i=1; i<21; i++) {  //шлем ресиверу 20 сообщений с данным текстом
            byte[] messageBodyBytes = ("тестовое сообщение "+i).getBytes();
            channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, messageBodyBytes);
        }
        channel.close();
        conn.close();
    }

}
