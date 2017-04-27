package main.controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * фильтр на авторизацию
 */
public class WhiteList implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userLogin = (String) ((HttpServletRequest)servletRequest).getSession().getAttribute("userLogin");

        if (userLogin != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            servletRequest.getRequestDispatcher("login.jsp").forward(servletRequest,servletResponse);
//            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + "/login");
        }
    }

    @Override
    public void destroy() {

    }
}
