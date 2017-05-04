package net.tableschedule.jsf.bean.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.xml.internal.ws.client.RequestContext;
import lombok.NoArgsConstructor;
import net.tableschedule.jsf.bean.communication.DataSourceService;
import net.tableschedule.jsf.bean.communication.DataSourceServiceImp;
import net.tableschedule.jsf.bean.listener.MQListener;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aleksandrprendota on 22.04.17.
 */
@NoArgsConstructor
@Stateless(name = TimeScheduleServiceImp.JNDI)
public class TimeScheduleServiceImp implements ServiceSchedule {

    public static final String JNDI = "serviceBean";
    private static final Logger LOG = Logger.getLogger(TimeScheduleServiceImp.class);
    private MQListener mqListener;

    @EJB(beanName = DataSourceServiceImp.JNDI)
    private DataSourceService dataSourceService;

    @PostConstruct
    public void init(){
        mqListener = new MQListener();
        try{
            mqListener.startListener();
        }catch (Exception e){
            LOG.error(e,e);
        }
    }

    @PreDestroy
    public void destroy(){
        try {
            mqListener.close();
        } catch (Exception e) {
            LOG.error(e,e);
        }
    }

    @Override
    public List<TimeSchedule> getContent(){
        return dataSourceService.getTimeScheduleList();
    }

    @Override
    public List<String> getCities(){
        return dataSourceService.getCitiesList();
    }

    @Override
    @SuppressWarnings({"unchecked", "unused"})
    public List<TimeSchedule> getListOfTimeScheduleByStationDepartureSorted(String station) {
        List<TimeSchedule> listTimeScheduleSortedByStationDeparture = new ArrayList<TimeSchedule>();
        List<TimeSchedule> timeSchedules;
        timeSchedules = dataSourceService.getTimeScheduleList();
        for (TimeSchedule timeSchedule : timeSchedules) {
            if(station.equals(timeSchedule.getStationDeparture())){
                listTimeScheduleSortedByStationDeparture.add(timeSchedule);
            }
        }
        return listTimeScheduleSortedByStationDeparture;
    }

}
