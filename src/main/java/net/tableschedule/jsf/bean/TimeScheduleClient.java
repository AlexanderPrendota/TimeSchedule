package net.tableschedule.jsf.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

/**
 * Created by aleksandrprendota on 18.04.17.
 */

@Data
@SessionScoped
@NoArgsConstructor
@AllArgsConstructor
@ManagedBean(name = "schedule", eager = true)
public class TimeScheduleClient {

    public List<TimeSchedule> timeSchedules;

    private MQListener mqListener;

    @SuppressWarnings({"unchecked", "unused"})
    public void getContent() throws Exception{


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

        try {
            timeSchedules = mapper.readValue(output, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<TimeSchedule> getTimeSchedules(){
        return timeSchedules;
    }

}
