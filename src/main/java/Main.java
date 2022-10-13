
public class Main {

    //Выполняет разовую отправку ресиверу через агент RabbitMQ заданное количество сообщений
    public static void main(String[] args) throws Exception {

        RabbitMQProducer rabbitMQProducer = new RabbitMQProducer("guest","127.0.0.1",5672);
        rabbitMQProducer.sendMessage("example message");

    }

}
