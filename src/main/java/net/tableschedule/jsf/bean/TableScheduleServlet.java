package net.tableschedule.jsf.bean;

import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.tableschedule.jsf.bean.MQListener.UPDATE_FLAG;

/**
 * Created by aleksandrprendota on 21.04.17.
 */
@NoArgsConstructor
public class TableScheduleServlet extends HttpServlet{
    private static final Logger LOG = Logger.getLogger(TableScheduleServlet.class);

    private MQListener mqListener;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        mqListener = new MQListener();
        try{
            mqListener.startListener();
        }catch (Exception e){
            LOG.error(e,e);
        }
    }

    @Override
    public void destroy() {
        try {
            mqListener.close();
        } catch (Exception e) {
            LOG.error(e,e);
        }
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(UPDATE_FLAG);
        resp.getWriter().flush();
    }
}