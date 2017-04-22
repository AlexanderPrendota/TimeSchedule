package net.tableschedule.jsf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

/**
 * Created by aleksandrprendota on 18.04.17.
 */

@Data
@SessionScoped
@NoArgsConstructor
@AllArgsConstructor
@ManagedBean(name = "schedule", eager = true)
public class TimeScheduleClient {

    private TimeScheduleService timeScheduleService = new TimeScheduleService();
    public List<TimeSchedule> timeSchedules = timeScheduleService.getContent();

    public List<TimeSchedule> getTimeSchedules(){
        return timeSchedules;
    }

}

//<ui:remove>
//<f:metadata>
//<f:event type="preRenderView" listener="#{schedule.getContent}" />
//</f:metadata>
//</ui:remove>