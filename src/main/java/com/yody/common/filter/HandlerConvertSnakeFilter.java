package com.yody.common.filter;

import com.google.common.base.CaseFormat;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(-2147483648)
@Slf4j
public class HandlerConvertSnakeFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final Map<String, String[]> formattedParams = new ConcurrentHashMap<>();

    for (String param : request.getParameterMap().keySet()) {
      String formattedParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
      formattedParams.put(formattedParam, request.getParameterValues(param));
    }

    filterChain.doFilter(new HttpServletRequestWrapper(request) {
      @Override
      public String getParameter(String name) {
        return formattedParams.containsKey(name) ? formattedParams.get(name)[0] : null;
      }

      @Override
      public Enumeration<String> getParameterNames() {
        return Collections.enumeration(formattedParams.keySet());
      }

      @Override
      public String[] getParameterValues(String name) {
        return formattedParams.get(name);
      }

      @Override
      public Map<String, String[]> getParameterMap() {
        return formattedParams;
      }
    }, response);
  }
}
