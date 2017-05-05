package net.tableschedule.jsf.bean.service;

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
import java.util.List;

/**
 * Created by aleksandrprendota on 22.04.17.
 */
@NoArgsConstructor
@Stateless(name = TimeScheduleServiceImp.JNDI)
public class TimeScheduleServiceImp implements ServiceSchedule {

    public static final String JNDI = "serviceBean";

    @EJB(beanName = DataSourceServiceImp.JNDI)
    private DataSourceService dataSourceService;

    @Override
    public List<TimeSchedule> getContent(){
        return dataSourceService.getTimeScheduleList();
    }

    @Override
    public List<String> getCities(){
        return dataSourceService.getCitiesList();
    }

    @Override
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
