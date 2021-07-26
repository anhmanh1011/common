package com.yody.common.filter;

import com.google.common.base.CaseFormat;
import com.yody.common.utility.Strings;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

@Component
@Order(-2147483647)
@Slf4j
public class HandlerConvertSnakeFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (request instanceof DefaultMultipartHttpServletRequest) {
      val defaultMultipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;

      MultiValueMap<String, MultipartFile> multiFileMap = defaultMultipartHttpServletRequest.getMultiFileMap();
      for (String param : multiFileMap.keySet()) {
        String formattedParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
        if (!Strings.compare(formattedParam, param)) {
          multiFileMap.put(formattedParam, multiFileMap.get(param));
          multiFileMap.remove(param);
        }
      }
      Map<String, String[]> parameterMap = new HashMap<>();
      for (String param : defaultMultipartHttpServletRequest.getParameterMap().keySet()) {
        String formattedParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
        String[] values = defaultMultipartHttpServletRequest.getParameterValues(param);
        for (String v : values) {
          if (v.contains(",")) {
            String[] s = v.split(",");
            parameterMap.put(formattedParam, s);
          } else {
            parameterMap.put(formattedParam, new String[]{v});
          }
        }
      }
      filterChain.doFilter(new DefaultMultipartHttpServletRequest(request) {
        @Override
        public MultiValueMap<String, MultipartFile> getMultipartFiles() {
          return multiFileMap;
        }

        @Override
        public Map<String, String[]> getMultipartParameters() {
          return parameterMap;
        }
      }, response);
      return;
    }
    final Map<String, String[]> formattedParams = new ConcurrentHashMap<>();
    for (String param : request.getParameterMap().keySet()) {
      String formattedParam = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, param);
      String[] values = request.getParameterValues(param);
      for (String v : values) {
        if (v.contains(",")) {
          String[] s = v.split(",");
          formattedParams.put(formattedParam, s);
        } else {
          formattedParams.put(formattedParam, new String[]{v});
        }
      }

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
