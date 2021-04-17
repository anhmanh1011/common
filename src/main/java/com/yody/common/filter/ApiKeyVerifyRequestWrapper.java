package com.yody.common.filter;

import org.springframework.http.MediaType;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/** The Class ApiKeyVerifiRequestWrapper. */
public class ApiKeyVerifyRequestWrapper extends HttpServletRequestWrapper {

  /** The body. */
  private String body;

  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request The request to wrap
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws IllegalArgumentException if the request is null
   */
  public ApiKeyVerifyRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = null;
    try(InputStream inputStream = request.getInputStream()) {
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
    ServletInputStream servletInputStream =
        new ServletInputStream() {
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
