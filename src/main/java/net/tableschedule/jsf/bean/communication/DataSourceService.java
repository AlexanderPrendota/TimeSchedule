package net.tableschedule.jsf.bean.communication;

import net.tableschedule.jsf.bean.model.TimeSchedule;

import java.util.List;

/**
 * Created by aleksandrprendota on 03.05.17.
 */

public interface DataSourceService {

    List<String> getCitiesList();

    List<TimeSchedule> getTimeScheduleList();
}
