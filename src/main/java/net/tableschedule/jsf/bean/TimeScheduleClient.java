package net.tableschedule.jsf.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by aleksandrprendota on 18.04.17.
 */

@Data
@RequestScoped
@NoArgsConstructor
@ManagedBean(name = "schedule")
public class TimeScheduleClient {

    public TimeScheduleService timeScheduleService = new TimeScheduleService();
    public List<TimeSchedule> timeSchedules = timeScheduleService.getContent();

    public List<TimeSchedule> getTimeSchedules(){
        return timeSchedules;
    }


}