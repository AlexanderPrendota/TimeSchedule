package net.tableschedule.jsf.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import javax.ws.rs.core.MediaType;
import static net.tableschedule.jsf.bean.MQListener.UPDATE_FLAG;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 22.04.17.
 */
@NoArgsConstructor
public class TimeScheduleService  {

    public List<TimeSchedule> timeSchedules = new ArrayList<TimeSchedule>();
    private static final Logger LOG = Logger.getLogger(TimeScheduleService.class);

    @SuppressWarnings({"unchecked", "unused"})
    public List<TimeSchedule> getContent(){

        Client client = Client.create();
        try {
            WebResource webResource = client.resource("http://localhost:8080/schedule/todays");
            ObjectMapper mapper = new ObjectMapper();
            ClientResponse response = webResource
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            String output = response.getEntity(String.class);
            timeSchedules = mapper.readValue(output, List.class);
        } catch (Exception connectionException) {
            LOG.error("Connection refused in server with data");
        }
        UPDATE_FLAG = false;
        return timeSchedules;
    }

}
