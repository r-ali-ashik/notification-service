package com.aliashik.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public interface EmailService {

    void prepareAndSend(String templateId, String templateType) throws JSONException;
}
