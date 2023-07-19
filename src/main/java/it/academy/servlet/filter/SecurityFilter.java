package it.academy.servlet.filter;

import it.academy.dto.UserDto;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static it.academy.utils.constants.DataUI.LOGGED_USER;
import static it.academy.utils.constants.DataUI.LOGIN_JSP;
import static it.academy.utils.constants.DataUI.URL_SEC_FILTER;

@WebFilter(urlPatterns = {URL_SEC_FILTER})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        UserDto loggedUser = (UserDto) session.getAttribute(LOGGED_USER);

        if (loggedUser == null) {
            ((HttpServletResponse) servletResponse).sendRedirect(LOGIN_JSP);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
