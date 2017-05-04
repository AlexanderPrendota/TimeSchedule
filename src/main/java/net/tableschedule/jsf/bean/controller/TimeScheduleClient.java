package net.tableschedule.jsf.bean.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import net.tableschedule.jsf.bean.service.ServiceSchedule;
import net.tableschedule.jsf.bean.service.TimeScheduleServiceImp;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static net.tableschedule.jsf.bean.listener.MQListener.UPDATE_FLAG;

/**
 * Created by aleksandrprendota on 18.04.17.
 */

@Data
@RequestScoped
@NoArgsConstructor
@ManagedBean(name = "schedule")
public class TimeScheduleClient {

    @ManagedProperty(value = "#{param.station}")
    private String station;

    @EJB(beanName = TimeScheduleServiceImp.JNDI)
    public ServiceSchedule serviceSchedule;

    public List<String> cities;
    public List<TimeSchedule> timeSchedules;

    @PostConstruct
    public void init() {
        if (station == null || station.equals("")){
            timeSchedules = serviceSchedule.getContent();
        } else {
            timeSchedules = serviceSchedule.getListOfTimeScheduleByStationDepartureSorted(station);
        }
        cities = serviceSchedule.getCities();
        UPDATE_FLAG=false;
    }

    public List<TimeSchedule> getTimeSchedules(){
        return timeSchedules;
    }

    public String getStation(){
        if (station == null) {
            return "All ways";
        } else{
            return station;
        }
    }

    public List<String> getCities(){
        return cities;
    }

}