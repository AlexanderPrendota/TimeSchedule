package net.tableschedule.jsf.bean.listener;

import com.rabbitmq.client.*;
import net.tableschedule.jsf.bean.cache.CacheCities;
import net.tableschedule.jsf.bean.cache.CacheTimeSchedules;
import net.tableschedule.jsf.bean.communication.DataSourceService;
import net.tableschedule.jsf.bean.communication.DataSourceServiceImp;
import net.tableschedule.jsf.bean.loader.Loader;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by aleksandrprendota on 26.04.17.
 */
public class MQListener {

    public static volatile boolean UPDATE_FLAG = false;
    private Channel channel;
    private Loader loader;
    private final static String QUEUE_NAME = "mylittlequeue";
    private static final Logger LOG = Logger.getLogger(MQListener.class);


    public void startListener() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection;
        loader = new Loader();
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        } catch (Exception e) {
            LOG.error("Connection refused with MQ-server");
        }

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                if (message.contains("update")){
                    CacheCities.getInstance().updateCache(loader.getCitiesFromServer());
                    CacheTimeSchedules.getInstance().updateCache(loader.getTimeScheduleFromServer());
                    UPDATE_FLAG = true;
                }
            }
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, consumer);
        } catch (Exception e) {
            LOG.error("Connection refused with MQ-server in channel.basicConsume");
        }
    }

    public void close() throws IOException, TimeoutException {
        try{
            channel.close();
        } catch (Exception e){
            LOG.error("Error with closing MQ");
        }

    }
}
