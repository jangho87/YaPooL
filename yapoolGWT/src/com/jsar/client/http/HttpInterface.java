package com.jsar.client.http;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.Window;

public class HttpInterface {

  @SuppressWarnings("unused")
  public static void doPost(String url, String postData,RequestCallback requestCallback) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, url);
    builder.setRequestData(postData);
    builder.setHeader("Content-type", "application/x-www-form-urlencoded");
    try {
      Request response = builder.sendRequest(postData, requestCallback);
     
    } catch (RequestException e) {
      Window.alert("Failed to send the request: " + e.getMessage());
      e.printStackTrace();
    }
  }
  
  public static void doGet(String url,RequestCallback requestCallback) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
    try {
      builder.setCallback(requestCallback);
      builder.send();
    } catch (RequestException e) {
      Window.alert("Failed to send the request: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void doDelete(String url, RequestCallback requestCallback) {
    RequestBuilder builder = new RequestBuilder(RequestBuilder.DELETE, url);
    try {
      builder.setCallback(requestCallback);
      builder.send();
    } catch (RequestException e) {
      Window.alert("Failed to send the request: " + e.getMessage());
      e.printStackTrace();
    }
  }
    
  }

