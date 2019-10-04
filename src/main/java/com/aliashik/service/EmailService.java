package com.aliashik.service;

import org.codehaus.jettison.json.JSONException;

import java.io.IOException;

public interface EmailService {

    void prepareAndSend(String leaveId) throws JSONException, IOException;
}
