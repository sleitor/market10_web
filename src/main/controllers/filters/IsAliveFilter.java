package main.controllers.filters;

import main.models.ConnectionPool;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

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

        Connection con = ConnectionPool.getInstance().getConnection();
        try {
            con.close();
        } catch (SQLException e) {
            logger.debug("Не можем закрыть соединение");
        }
        if (!ConnectionPool.isIsAlive()) {
            servletRequest.getRequestDispatcher("error.jsp").forward(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);

//            HashMap temp = (HashMap) ((HttpServletRequest)servletRequest).getSession().getAttribute("cart");
//            if (temp == null) {
//                logger.debug("Инициализация корзины...");
//                HashMap<Long, Integer> cart = new HashMap<>();
//                ((HttpServletRequest)servletRequest).getSession().setAttribute("cart", cart);
//            }
        }
    }


    @Override
    public void destroy() {

    }

}
