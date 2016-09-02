package com.lpf.tools.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/9/2.
 * Description:
 */
public interface PhoneService {
//    @GET("/api/products/getc2cproducts.do")
    @GET("/android/data/1/appcontent.json")

    Call<PhoneResult> getResult(@Query("plat") String phone);
}
