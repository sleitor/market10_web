package main.controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  Фильтр для проверки прав на администратора
 */

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        boolean a = false;

        if (((HttpServletRequest)servletRequest).getSession().getAttribute("userAdmin") != null) {
            a = (boolean) ((HttpServletRequest) servletRequest).getSession().getAttribute("userAdmin");
        }

        if (a) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {

            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + "/error.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
