package net.tableschedule.jsf.bean.communication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.NoArgsConstructor;
import net.tableschedule.jsf.bean.cache.CacheCities;
import net.tableschedule.jsf.bean.cache.CacheTimeSchedules;
import net.tableschedule.jsf.bean.loader.Loader;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static net.tableschedule.jsf.bean.listener.MQListener.UPDATE_FLAG;

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





