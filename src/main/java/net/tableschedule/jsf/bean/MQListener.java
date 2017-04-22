package net.tableschedule.jsf.bean;

import com.rabbitmq.client.*;
import lombok.AllArgsConstructor;
import org.omnifaces.util.Ajax;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

/**
 * Created by aleksandrprendota on 21.04.17.
 */

@AllArgsConstructor
public class MQListener{

    private final static String QUEUE_NAME = "mylittlequeue";


    public void startListener() throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                if (message.contains("update")){
                     TimeScheduleService timeScheduleService = new TimeScheduleService();
                     List<TimeSchedule> timeSchedules = timeScheduleService.getContent();

                    // 1 way:
                    // ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    // ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

                    // 2 way:
                    //Ajax.update(":maintable");

                    // another way
                    //String page = "home.xhtml";
                    //FacesContext.getCurrentInstance().getExternalContext().redirect(page);
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
