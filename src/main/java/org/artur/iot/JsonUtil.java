package org.artur.iot;

import com.google.gson.Gson;

import elemental.json.JsonObject;
import elemental.json.impl.JreJsonFactory;

public class JsonUtil {

    public static JsonObject beanToJson(Object object) {
        String personsJson = new Gson().toJson(object);
        return new JreJsonFactory().parse(personsJson);
    }

    public static <T> T jsonToBean(JsonObject json, Class<T> type) {
        return new Gson().fromJson(json.toJson(), type);
    }

}
