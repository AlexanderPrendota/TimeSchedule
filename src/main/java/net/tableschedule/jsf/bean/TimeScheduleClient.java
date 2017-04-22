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

    public List<TimeSchedule> getTimeSchedules(){
        return timeSchedules;
    }

}
