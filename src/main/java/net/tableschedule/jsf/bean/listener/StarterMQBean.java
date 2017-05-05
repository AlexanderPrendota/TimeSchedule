package net.tableschedule.jsf.bean.listener;


import org.apache.log4j.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 * Created by aleksandrprendota on 04.05.17.
 */

@Stateless
public class StarterMQBean {

    private static final Logger LOG = Logger.getLogger(StarterMQBean.class);
    private MQListener mqListener = new MQListener();

    @PreDestroy
    public void destroy(){
        mqListener.close();
    }

    @Schedule(second= "*/30", minute = "*", hour = "*", persistent = false)
    public void registrationMQ(){
        LOG.info("[*] Checking starting MQ....");
        if (MQListener.RIGISTER_FLAG){
            try {
                mqListener.startListener();
            } catch (Exception e){
                LOG.error("Schedule Bean failed in starting MQ");
            }
        }
    }
}
