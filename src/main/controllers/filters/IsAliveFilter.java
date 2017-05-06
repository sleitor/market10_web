package main.controllers.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр, для проверки соединения с бд перед началом работы приложения.
 * + Добавлена инициализация корзины.
 */
public class IsAliveFilter implements Filter {
    private static Logger logger = Logger.getLogger(IsAliveFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.debug("Проверяем соединение с БД...");

//        Connection con = ConnectionPool.getInstance().getConnection();
//        try {
//            con.close();
//        } catch (SQLException e) {
//            logger.debug("Не можем закрыть соединение");
//        }
//        if (!ConnectionPool.isIsAlive()) {
//            servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
//        } else {
            filterChain.doFilter(servletRequest, servletResponse);
//        }
    }


    @Override
    public void destroy() {

    }

}
