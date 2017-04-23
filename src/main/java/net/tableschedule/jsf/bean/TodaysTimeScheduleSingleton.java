package net.tableschedule.jsf.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 23.04.17.
 */
@NoArgsConstructor
@ManagedBean(name = "cache")
public class TodaysTimeScheduleSingleton {

    private static volatile TodaysTimeScheduleSingleton instance;

    @Getter
    private List<TimeSchedule> todaysSchedule = new ArrayList<TimeSchedule>();

    public int flagToUpdate = 0;


    public void update(List<TimeSchedule> timeSchedules){
        todaysSchedule = null;
        todaysSchedule = timeSchedules;
        System.out.println("UPDATE");
        flagToUpdate++;
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
