package net.tableschedule.jsf.bean.communication;

import lombok.NoArgsConstructor;
import net.tableschedule.jsf.bean.cache.CacheCities;
import net.tableschedule.jsf.bean.cache.CacheTimeSchedules;
import net.tableschedule.jsf.bean.loader.Loader;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by aleksandrprendota on 03.05.17.
 */
@Stateless(name = DataSourceServiceImp.JNDI)
@NoArgsConstructor
public class DataSourceServiceImp implements DataSourceService {


    public static final String JNDI = "datasourceServiceBean";
    private Loader loader;

    @Override
    public List<String> getCitiesList() {
        loader = new Loader();
        List<String> cities = CacheCities.getInstance().getCache();
        if (cities.size() == 0) {
            cities = loader.getCitiesFromServer();
        }
        return cities;
    }

    @Override
    @SuppressWarnings({"unchecked", "unused"})
    public List<TimeSchedule> getTimeScheduleList() {
        loader = new Loader();
        List<TimeSchedule> timeSchedules = CacheTimeSchedules.getInstance().getCache();
        if (timeSchedules.size() == 0) {
            timeSchedules = loader.getTimeScheduleFromServer();
        }
        return timeSchedules;
    }
}





