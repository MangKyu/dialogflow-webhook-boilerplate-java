package com.example;

import com.example.model.CalendarResponse;
import com.example.model.Item;
import com.example.retrofit.RetrofitClient;
import com.example.utils.DateUtils;
import com.google.api.client.util.DateTime;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

public class CalendarQuickStart {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        String year = "2020년";
        String month = "6월";
        String day = "2일";
        StringBuilder sb = new StringBuilder("\n");

        Date startDate = DateUtils.createDate(year + month + day);
        Call<CalendarResponse> call = RetrofitClient.getInstance().getService().getEventList(new DateTime(startDate), new DateTime(DateUtils.createNextDate(startDate)));
        Response<CalendarResponse> response = call.execute();
        if (response.isSuccessful()) {
            List<Item> itemList = response.body().getItems();
            if (itemList != null) {
                for (Item item : itemList) {
                    String[] times = item.getStart().getDateTime().split("T")[1].split(":");
                    String msg = item.getSummary() + " " + times[0] + ":" + times[1] + item.getSummary();
                    sb.append(msg);
                }
            }
        } else {
            sb.append(response.errorBody().string());
        }
        System.out.println(sb.toString());
    }

}
