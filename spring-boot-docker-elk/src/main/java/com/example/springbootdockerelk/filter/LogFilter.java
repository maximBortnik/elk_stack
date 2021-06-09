package com.example.springbootdockerelk.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class LogFilter extends OncePerRequestFilter {

    private static final String MESSAGE_ID = "message_id";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            var requestId = request.getHeader(MESSAGE_ID);
            if (requestId == null) {
                requestId = UUID.randomUUID().toString();
            }
            MDC.put(MESSAGE_ID, requestId);
            log.info("Started process request with {} : {}", MESSAGE_ID, requestId);
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
