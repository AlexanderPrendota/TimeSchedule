package net.tableschedule.jsf.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bootsfaces.render.E;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSchedule {

    private String train;
    private String stationDeparture;
    private String timeDeparture;
    private String stationArrival;
    private String timeArrival;

}
