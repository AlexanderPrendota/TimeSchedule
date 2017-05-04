package net.tableschedule.jsf.bean.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import net.tableschedule.jsf.bean.cache.CacheCities;
import net.tableschedule.jsf.bean.cache.CacheTimeSchedules;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static net.tableschedule.jsf.bean.listener.MQListener.UPDATE_FLAG;

/**
 * Created by aleksandrprendota on 03.05.17.
 */
public class Loader {

    private static final Logger LOG = Logger.getLogger(Loader.class);

    @SuppressWarnings({"unchecked", "unused"})
    public List<TimeSchedule> getTimeScheduleFromServer(){

        List<TimeSchedule> timeSchedules = new ArrayList<TimeSchedule>();

        String url = "http://localhost:8080/schedule/todays";
        Client client = Client.create();
        try {
            WebResource webResource = client.resource(url);
            ObjectMapper mapper = new ObjectMapper();
            ClientResponse response = webResource
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            String output = response.getEntity(String.class);
            timeSchedules = mapper.readValue(output,new TypeReference<List<TimeSchedule>>(){});
            CacheTimeSchedules.getInstance().updateCache(timeSchedules);
        } catch (Exception connectionException) {
            LOG.error("Connection refused in server with data");
        }
        UPDATE_FLAG = false;

        return timeSchedules;
    }

    @SuppressWarnings({"unchecked", "unused"})
    public List<String> getCitiesFromServer(){
        List<String> cities = new ArrayList<String>();
        if (cities.size() == 0){
            Client client = Client.create();
            try {
                WebResource webResource = client.resource("http://localhost:8080/schedule/future/stations");
                ObjectMapper mapper = new ObjectMapper();
                ClientResponse response = webResource
                        .accept(MediaType.APPLICATION_JSON)
                        .get(ClientResponse.class);
                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }
                String output = response.getEntity(String.class);
                cities = mapper.readValue(output, List.class);
                CacheCities.getInstance().updateCache(cities);
            } catch (Exception connectionException) {
                LOG.error("Connection refused in server with city data");
            }
        }
        return cities;
    }
}
