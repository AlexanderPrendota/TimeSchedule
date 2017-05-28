import net.tableschedule.jsf.bean.communication.DataSourceServiceImp;
import net.tableschedule.jsf.bean.loader.Loader;
import net.tableschedule.jsf.bean.model.TimeSchedule;
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
public class TestCommunicationLayer {

    @Mock
    private Loader loader;

    @InjectMocks
    private DataSourceServiceImp sourceServiceImp;


    @Test
    public void testGetCitiesList(){
        when(loader.getCitiesFromServer()).thenReturn(new ArrayList<String>());
        sourceServiceImp.getCitiesList();
        verify(loader).getCitiesFromServer();
    }

    @Test
    public void testGetTimeScheduleList(){
        when(loader.getTimeScheduleFromServer()).thenReturn(new ArrayList<TimeSchedule>());
        sourceServiceImp.getTimeScheduleList();
        verify(loader).getTimeScheduleFromServer();
    }
}
