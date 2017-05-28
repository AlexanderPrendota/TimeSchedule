import net.tableschedule.jsf.bean.communication.DataSourceService;
import net.tableschedule.jsf.bean.model.TimeSchedule;
import net.tableschedule.jsf.bean.service.TimeScheduleServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by aleksandrprendota on 27.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceLayer {

    @Mock
    private DataSourceService dataSourceService;

    @InjectMocks
    private TimeScheduleServiceImp scheduleServiceImp;


    @Test
    public void testGetContent(){
        when(dataSourceService.getTimeScheduleList()).thenReturn(new ArrayList<TimeSchedule>());
        scheduleServiceImp.getContent();
        verify(dataSourceService).getTimeScheduleList();
    }

    @Test
    public void testGetCities(){
        when(dataSourceService.getCitiesList()).thenReturn(new ArrayList<String>());
        scheduleServiceImp.getCities();
        verify(dataSourceService).getCitiesList();
    }

    @Test
    public void testSortedScheduleByStationDeparture(){
        when(dataSourceService.getTimeScheduleList()).thenReturn(new ArrayList<TimeSchedule>());
        scheduleServiceImp.getListOfTimeScheduleByStationDepartureSorted("Yaroslavl");
        verify(dataSourceService).getTimeScheduleList();
    }
}
