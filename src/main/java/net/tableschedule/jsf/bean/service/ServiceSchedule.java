package net.tableschedule.jsf.bean.service;

import net.tableschedule.jsf.bean.model.TimeSchedule;

import java.util.List;

/**
 * Created by aleksandrprendota on 03.05.17.
 */
public interface ServiceSchedule {

    List<TimeSchedule> getContent();

    List<String> getCities();

    List<TimeSchedule> getListOfTimeScheduleByStationDepartureSorted(String station);
}
