package net.tableschedule.jsf.bean.listener;


import org.apache.log4j.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import static net.tableschedule.jsf.bean.listener.MQListener.RIGISTER_FLAG;

/**
 * Created by aleksandrprendota on 04.05.17.
 */

@Stateless
public class StarterMQBean {

    private static final Logger LOG = Logger.getLogger(StarterMQBean.class);

    @Schedule(second= "*/30", minute = "*", hour = "*", persistent = false)
    public void registrationMQ(){
        LOG.info("[*] Checking starting MQ....");
        if (RIGISTER_FLAG){
            MQListener mqListener = new MQListener();
            try {
                mqListener.startListener();
                LOG.info("[*] MQ was starting...prepare to listen");
            } catch (Exception e){
                LOG.error("Schedule Bean failed in starting MQ");
            }
        }
    }
}
