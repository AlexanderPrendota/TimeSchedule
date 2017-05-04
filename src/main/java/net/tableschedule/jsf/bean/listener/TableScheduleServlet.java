package net.tableschedule.jsf.bean.listener;

import lombok.NoArgsConstructor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static net.tableschedule.jsf.bean.listener.MQListener.UPDATE_FLAG;

/**
 * Created by aleksandrprendota on 21.04.17.
 */
@NoArgsConstructor
public class TableScheduleServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print(UPDATE_FLAG);
        resp.getWriter().flush();
    }
}