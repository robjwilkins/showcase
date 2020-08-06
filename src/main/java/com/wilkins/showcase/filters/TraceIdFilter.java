package com.wilkins.showcase.filters;

import com.wilkins.showcase.service.ExampleService;
import lombok.RequiredArgsConstructor;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@RequiredArgsConstructor
public class TraceIdFilter implements Filter {

    private final ExampleService idService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("x-trace-id", idService.findExampleId());
        chain.doFilter(request, response);
    }
}
