package main.controllers.filters;

import main.models.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by User on 26.04.2017.
 */
public class IsAliveFilter implements Filter {
    private static Logger logger = Logger.getLogger(IsAliveFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.debug("Проверяем соединение с БД...");

        ConnectionPool.getInstance().getConnection();

        if (!ConnectionPool.isIsAlive()) {
            servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {

    }

}
