package com.example.retrofit;

import com.example.model.CalendarResponse;
import com.google.api.client.util.DateTime;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("events")
    Call<CalendarResponse> getEventList(
            @Query(value = "timeMin") DateTime timeMin,
            @Query(value = "timeMax") DateTime timeMax
    );

}
