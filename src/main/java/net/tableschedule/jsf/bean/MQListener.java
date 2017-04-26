package net.tableschedule.jsf.bean;

import com.rabbitmq.client.*;
import lombok.NoArgsConstructor;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by aleksandrprendota on 21.04.17.
 */
@NoArgsConstructor
public class MQListener extends HttpServlet{

    private final static String QUEUE_NAME = "mylittlequeue";
    public static boolean UPDATE_FLAG = false;


    public void startListener() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
                if (message.contains("update")){
                    UPDATE_FLAG = true;
                    System.out.println("DONE MQ");
                }
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        MQListener mqListener = new MQListener();
        try{
            mqListener.startListener();
        }catch (Exception e){
        }
    }

}

//                     TimeScheduleService timeScheduleService = new TimeScheduleService();
//                     List<TimeSchedule> timeSchedules = timeScheduleService.getContent();
//                     TodaysTimeScheduleSingleton.getInstance().update(timeSchedules);

// 1 way:
// ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
// ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

// 2 way:
//Ajax.update(":maintable");



// another way
//String page = "home.xhtml";
//FacesContext.getCurrentInstance().getExternalContext().redirect(page);