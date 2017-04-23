package net.tableschedule.jsf.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 23.04.17.
 */
@NoArgsConstructor
public class TodaysTimeScheduleSingleton {

    private static volatile TodaysTimeScheduleSingleton instance;

    @Getter
    private List<TimeSchedule> todaysSchedule = new ArrayList<TimeSchedule>();


    public void update(List<TimeSchedule> timeSchedules){
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
