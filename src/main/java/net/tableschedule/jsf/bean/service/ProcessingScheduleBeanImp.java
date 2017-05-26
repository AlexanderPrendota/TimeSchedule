package net.tableschedule.jsf.bean.service;

import net.tableschedule.jsf.bean.cache.CacheTimeSchedules;
import net.tableschedule.jsf.bean.model.TimeSchedule;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by aleksandrprendota on 19.05.17.
 */

@Stateless
public class ProcessingScheduleBeanImp  implements ProcessingScheduleBean{

    @Override
    @Schedule(second= "*/60", minute = "*", hour = "*", persistent = false)
    public void startProcessing() throws ParseException {
        System.out.println("IM PARSING SCHEDULE");
        Date nowTime = new Date();
        List<TimeSchedule> schedules = CacheTimeSchedules.getInstance().getCache();
        for (TimeSchedule schedule : schedules) {
            DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date date = format.parse(schedule.getTimeDeparture());
            if (date.before(nowTime)){
                schedules.remove(schedule);
            }
        }
    }
}
