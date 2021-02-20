package com.yody.common.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
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


}
