package it.academy.servlet.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static it.academy.utils.constants.DataUI.URL_FILTER;
import static it.academy.utils.constants.DataUI.UTF_8;

@WebFilter(urlPatterns = {URL_FILTER})
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws ServletException, IOException {

        request.setCharacterEncoding(UTF_8);
        response.setCharacterEncoding(UTF_8);
        chain.doFilter(request, response);
    }
}
