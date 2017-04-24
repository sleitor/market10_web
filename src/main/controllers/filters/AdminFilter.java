package main.controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by User on 23.04.2017.
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Boolean admin = (Boolean) ((HttpServletRequest)servletRequest).getAttribute("userAdmin");

        if (admin) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {

            ((HttpServletResponse)servletResponse).sendRedirect(((HttpServletRequest)servletRequest).getContextPath() + "/error.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
