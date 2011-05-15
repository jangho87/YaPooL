package com.jsar.client.unit;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.jsar.client.http.AbstractRequestCallback;
import com.jsar.client.json.ProfileJson;

public class MyProfileUnit extends AbstractUnit {
  public static MyProfileUnit myProfileUnit;
  
  
  public MyProfileUnit(){
    myProfileUnit=this;
    
    
    
    
    
    VerticalPanel verticalPanel=new VerticalPanel();
    RootPanel.get("myProfileContainer").add(verticalPanel);
    
    
  }
  
  public void displayProfile(String owner) {
    
    
  }
  
  public class DisplayProfileCallBack extends AbstractRequestCallback{

    @Override
    public void onResponseReceived(Request request, Response response) {
      ProfileJson profileJson=new ProfileJson(response.getText());
      
    }
    
  }
  
  @Override
  public String getContainerId() {
    return "myProfileContainer";
  }



}
