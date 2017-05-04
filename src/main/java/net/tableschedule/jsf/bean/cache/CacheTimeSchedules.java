package net.tableschedule.jsf.bean.cache;

import lombok.NoArgsConstructor;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 03.05.17.
 */
@NoArgsConstructor
public class CacheTimeSchedules {

    private static volatile CacheTimeSchedules instance;
    private List<TimeSchedule> cache = new ArrayList<TimeSchedule>();

    public void updateCache(List<TimeSchedule> schedules){
        cache = null;
        cache = schedules;
    }

    public List<TimeSchedule> getCache(){
        return cache;
    }

    public static CacheTimeSchedules getInstance() {
        CacheTimeSchedules localInstance = instance;
        if (localInstance == null) {
            synchronized (CacheTimeSchedules.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CacheTimeSchedules();
                }
            }
        }
        return localInstance;
    }
}
