package com.wilkins.showcase.filters;

import com.wilkins.showcase.service.ExampleService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@WebFilter("/*")
public class TraceIdFilter implements Filter {

    private final ExampleService idService;

    public TraceIdFilter(ExampleService idService) {
        this.idService = idService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("x-trace-id", idService.findExampleId());
        chain.doFilter(request, response);
    }
}
