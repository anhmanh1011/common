package com.yody.common.utility;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;

public class Prints {
  public static String process(String printForm, Map<String, Object> model, Map<String, String> variableMaps)
      throws IOException, TemplateException {

    String freemarkerTemplateSource = getSourceTemplateFromPrintForm(printForm, variableMaps);

    Template template = getTemplate(freemarkerTemplateSource);

    Writer writer = new StringWriter();

    template.process(model, writer);

    return writer.toString();
  }

  private static String getSourceTemplateFromPrintForm(String printForm, Map<String, String> variableMaps) {
    Set<String> keys = variableMaps.keySet();
    for (String key : keys) {
      printForm = printForm.replace(key, variableMaps.get(key));
    }
    return printForm;
  }

  private static Template getTemplate(String freemarkerTemplateSource) throws IOException {

    StringTemplateLoader stringLoader = new StringTemplateLoader();
    stringLoader.putTemplate("template", freemarkerTemplateSource);

    Configuration configuration = config(stringLoader);

    return configuration.getTemplate("template");
  }

  private static Configuration config(StringTemplateLoader stringLoader) {
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
    configuration.setTemplateLoader(stringLoader);
    configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
    configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
    return configuration;
  }

  public String removeHtmlComment(String htmlCode) {
    String freemarkerCode = htmlCode.replace("<!--", "<#--");
    return freemarkerCode;
  }

  public String removeFreemarkerComment(String freemarkerCode) {
    String htmlCode = freemarkerCode.replace("<#--", "<!--");
    return htmlCode;
  }
}
