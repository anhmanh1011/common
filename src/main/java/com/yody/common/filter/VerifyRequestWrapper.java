package com.yody.common.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.ToString;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * The Class ApiKeyVerifiRequestWrapper.
 */
@ToString
public class VerifyRequestWrapper extends HttpServletRequestWrapper {

  /**
   * The body.
   */
  private String body;
  private Map<String, String> headerMap = new HashMap<String, String>();

  public void addHeader(String name, String value) {
    headerMap.put(name, value);
  }

  @Override
  public String getHeader(String name) {
    String headerValue = super.getHeader(name);
    if (headerMap.containsKey(name)) {
      headerValue = headerMap.get(name);
    }
    return headerValue;
  }

  @Override
  public Enumeration<String> getHeaderNames() {
    List<String> names = Collections.list(super.getHeaderNames());
    for (String name : headerMap.keySet()) {
      names.add(name);
    }
    return Collections.enumeration(names);
  }

  @Override
  public Enumeration<String> getHeaders(String name) {
    List<String> values = Collections.list(super.getHeaders(name));
    if (headerMap.containsKey(name)) {
      values.add(headerMap.get(name));
    }
    return Collections.enumeration(values);
  }

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request The request to wrap
   * @throws IOException              Signals that an I/O exception has occurred.
   * @throws IllegalArgumentException if the request is null
   */
  public VerifyRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;
    try (InputStream inputStream = request.getInputStream()) {
      if (inputStream != null) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        char[] charBuffer = new char[128];
        int bytesRead = -1;
        while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
          stringBuilder.append(charBuffer, 0, bytesRead);
        }
      } else {
        stringBuilder.append("");
      }
    } catch (IOException ex) {
      throw ex;
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException ex) {
          throw ex;
        }
      }
    }
    body = stringBuilder.toString();
  }


  /* (non-Javadoc)
   * @see javax.servlet.ServletRequestWrapper#getInputStream()
   */
  @Override
  public ServletInputStream getInputStream() throws IOException {
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
    ServletInputStream servletInputStream = new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

      public int read() throws IOException {
        return byteArrayInputStream.read();
      }

    };
    return servletInputStream;
  }

  /* (non-Javadoc)
   * @see javax.servlet.ServletRequestWrapper#getReader()
   */
  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(this.getInputStream()));
  }

  /**
   * Gets the body.
   *
   * @return the body
   */
  public String getBody() {
    return body;
  }

  /**
   * Sets the body.
   *
   * @param body the new body
   */
  public void setBody(String body) {
    this.body = body;
  }

  @Override
  public String getContentType() {
    String contentType = super.getContentType();
    return contentType == null ? MediaType.APPLICATION_JSON_VALUE : contentType;
  }
}
