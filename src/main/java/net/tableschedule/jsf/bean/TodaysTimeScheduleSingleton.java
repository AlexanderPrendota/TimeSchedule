package net.tableschedule.jsf.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 23.04.17.
 */
@NoArgsConstructor
@SessionScoped
@ManagedBean(name = "cache")
public class TodaysTimeScheduleSingleton {

    private static volatile TodaysTimeScheduleSingleton instance;

    @Getter
    private List<TimeSchedule> todaysSchedule = new ArrayList<TimeSchedule>();

    @Getter
    @Setter
    public int flagToUpdate = 0;
    
    public void update(List<TimeSchedule> timeSchedules) throws IOException{
        todaysSchedule = null;
        todaysSchedule = timeSchedules;
    }

    public static TodaysTimeScheduleSingleton getInstance() {
        TodaysTimeScheduleSingleton localInstance = instance;
        if (localInstance == null) {
            synchronized (TodaysTimeScheduleSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new TodaysTimeScheduleSingleton();
                }
            }
        }
        return localInstance;
    }
}
