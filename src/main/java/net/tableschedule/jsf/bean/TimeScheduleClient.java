package net.tableschedule.jsf.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.tableschedule.jsf.bean.MQListener.UPDATE_FLAG;

/**
 * Created by aleksandrprendota on 18.04.17.
 */

@Data
@SessionScoped
@NoArgsConstructor
@ManagedBean(name = "schedule")
public class TimeScheduleClient {

    public TimeScheduleService timeScheduleService = new TimeScheduleService();
    public List<TimeSchedule> timeSchedules = timeScheduleService.getContent();

    public List<TimeSchedule> getTimeSchedules(){
        return timeSchedules;
    }

    public void initial() throws IOException{
        System.out.println("in initial");
        if(UPDATE_FLAG) {
            UPDATE_FLAG = false;
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/home.xhtml");
            System.out.println("UPDATE");
        }
    }

}