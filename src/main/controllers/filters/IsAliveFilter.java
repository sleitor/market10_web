package main.controllers.filters;

import main.models.ConnectionPool;
import main.models.MyException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by User on 26.04.2017.
 */
public class IsAliveFilter implements Filter {
    private static Logger logger = Logger.getLogger(IsAliveFilter.class);
    private static boolean death = false;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            if (checkConnect()) {
                servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (SQLException | MyException e) {
            logger.debug("Ошибка соединения с БД!!!!!");
        }

    }

    @Override
    public void destroy() {

    }

    private boolean checkConnect() throws SQLException, MyException {
            if (ConnectionPool.getInstance().getConnection() == null) {
                death = true;
                logger.debug("Ошибка соединения с БД checkConnect");
                throw new MyException("Ошибка соединения с БД");
            }

            return death;
    }

}
