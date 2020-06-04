/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.example.apis.GoogleCalendarAPIs;
import com.example.model.CalendarResponse;
import com.example.model.Item;
import com.example.retrofit.RetrofitClient;
import com.example.utils.DateUtils;
import com.google.actions.api.ActionRequest;
import com.google.actions.api.ActionResponse;
import com.google.actions.api.DialogflowApp;
import com.google.actions.api.ForIntent;
import com.google.actions.api.response.ResponseBuilder;
import com.google.api.client.util.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Implements all intent handlers for this Action. Note that your App must extend from DialogflowApp
 * if using Dialogflow or ActionsSdkApp for ActionsSDK based Actions.
 */
public class MyActionsApp extends DialogflowApp {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyActionsApp.class);

  @ForIntent("Date Intent")
  public ActionResponse welcome(ActionRequest request) throws IOException {
    ResponseBuilder responseBuilder = getResponseBuilder(request);
    Map<String, Object> map =  request.getWebhookRequest().getQueryResult().getParameters();
    String year = (String) map.get("date-year");
    String month = (String) map.get("date-month");
    String day = (String) map.get("date-day");

    Date startDate = DateUtils.createDate(year + month + day);
    if(startDate == null){
      responseBuilder.add("날짜가 잘못되었습니다.");
    } else {
        Call<CalendarResponse> call = RetrofitClient.getInstance().getService().getEventList(new DateTime(startDate), new DateTime(DateUtils.createNextDate(startDate)));
        Response<CalendarResponse> response = call.execute();
        if (response.isSuccessful()){
          List<Item> itemList = response.body().getItems();
          if(itemList != null){
            for (Item item : itemList) {
              String[] times = item.getStart().getDateTime().split("T")[1].split(":");
              String msg = item.getSummary() + " " + times[0] + ":" + times[1] + item.getSummary();
              responseBuilder.add(msg);
            }
          }
        }else {
          responseBuilder.add(response.errorBody().string());
        }
    }

    // responseBuilder.add("SmartCity-Test");
    return responseBuilder.build();
  }

  @ForIntent("bye")
  public ActionResponse bye(ActionRequest request) {
    LOGGER.info("Bye intent start.");
    ResponseBuilder responseBuilder = getResponseBuilder(request);
    ResourceBundle rb = ResourceBundle.getBundle("resources");

    responseBuilder.add(rb.getString("bye")).endConversation();
    LOGGER.info("Bye intent end.");
    return responseBuilder.build();
  }
}
