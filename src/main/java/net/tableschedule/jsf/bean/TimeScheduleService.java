package net.tableschedule.jsf.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleksandrprendota on 22.04.17.
 */
public class TimeScheduleService {

    @SuppressWarnings({"unchecked", "unused"})
    public List<TimeSchedule> getContent() throws Exception{

        Client client = Client.create();
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
        List<TimeSchedule> timeSchedules = new ArrayList<TimeSchedule>();
        try {
            timeSchedules = mapper.readValue(output, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return timeSchedules;
    }
}
