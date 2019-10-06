package com.aliashik.service;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;

public interface EmailService {

    void prepareAndSend(Integer templateId, JSONObject payload) throws JSONException, IOException;
}
