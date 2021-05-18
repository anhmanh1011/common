package com.yody.common.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;

public class Jsons {

  private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

  public static String toJSON(Object obj) {
    return gson.toJson(obj);
  }

  public static <T> T toObject(String value, Class<T> actualObject) {
    try {
      return gson.fromJson(value, actualObject);
    } catch (JsonSyntaxException ex) {
      return null;
    }
  }

  public static <T> T getObject(String content, Class<T> classInput) {
    String className = getClassName(classInput.getName());
    JSONObject jsonObj = new JSONObject(content);
    JSONObject tmpOjb = (JSONObject) jsonObj.get(className);
    T _r = gson.fromJson(tmpOjb.toString(), classInput);
    return _r;
  }

//	@SuppressWarnings("unchecked")
//	public static <T> T getObject(Class<T> classInput, String content) {
//		Map<String, Object> r = gson.fromJson(content, Map.class);
//		String className = getClassName(classInput.getName());
//		Object objectContent = r.get(className);
//		String innerJson = gson.toJson(objectContent);
//		T _r = gson.fromJson(innerJson, classInput);
//		return _r;
//	}

  public static <T> ArrayList<T> jsonToList(String jsonString, Class<T> T) {
    JSONArray array = new JSONArray();
    try {
      JSONArray jsonArray = new JSONArray(jsonString);
      array = jsonArray;
    } catch (Exception e) {
      JSONObject jsonObject = new JSONObject(jsonString);
      array.put(jsonObject);
    }

    ArrayList<T> data = new ArrayList<>();
    for (int i = 0; i < array.length(); i++) {
      T t = Jsons.toObject(array.get(i).toString(), T);
      data.add(t);
    }
    return data;
  }

  private static String getClassName(String input) {
    if (input == null) {
      return "";
    }
    int index = input.lastIndexOf(".");
    if (index != -1) {
      input = input.substring(index);
    }
    if (input.lastIndexOf(".") != -1) {
      input = input.replace(".", "");
    }
    return input;
  }


  public static final JSONObject toJson(List<String> fields, Object o) {
    ObjectMapper mapper = new ObjectMapper();
    JSONObject object = new JSONObject();
    try {
      String json = mapper.writeValueAsString(o);
      JsonNode root = mapper.readTree(json);
      fields.forEach(field -> {
        JsonNode node = root.get(field);
        if (node != null) {
          if (Numbers.isNumeric(node.asText())) {
            object.put(field, new BigDecimal(node.asText()));
          } else {
            object.put(field, node.asText());
          }
        }
      });
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return object;

  }



}
