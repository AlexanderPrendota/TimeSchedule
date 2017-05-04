package net.tableschedule.jsf.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
