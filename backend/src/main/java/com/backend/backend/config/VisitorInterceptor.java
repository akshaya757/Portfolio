package com.backend.backend.config;

import com.backend.backend.model.VisitorLog;
import com.backend.backend.repository.VisitorLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class VisitorInterceptor implements HandlerInterceptor {

    private final VisitorLogRepository logRepository;


    public VisitorInterceptor(VisitorLogRepository logRepository) {
        this.logRepository = logRepository;
    }
    private String detectDevice(String userAgent) {
        userAgent = userAgent.toLowerCase();
        if (userAgent.contains("mobile")) return "Mobile";
        else if (userAgent.contains("tablet")) return "Tablet";
        else return "Desktop";
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        VisitorLog log = new VisitorLog();
        String userAgent = request.getHeader("User-Agent");
        log.setIp(request.getRemoteAddr());
        log.setUserAgent(request.getHeader("User-Agent"));
        log.setVisitTime(LocalDateTime.now());
        log.setDeviceType(detectDevice(userAgent));

        logRepository.save(log);
        return true;
    }
}
