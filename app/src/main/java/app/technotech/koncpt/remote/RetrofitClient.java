package app.technotech.koncpt.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    //private static final String BASE_URL = "http://koncpt.technotechindia.co.in/api/v1/";
//    private static final String BASE_URL = "https://koncptkritatechnosolutions.in/api/v1/";
//    private static final String BASE_URL = "http://koncptkritatechnosolutions.com/api/v1/";
//    private static final String BASE_URL = "https://koncptkritatechnosolutions.com/api/v1/";
    //private static final String BASE_URL = "https://dev.koncptkritatechnosolutions.com/api/v1/";
    //private static final String BASE_URL = "https://7hillstechnosolutions.com/app/mobile_api/"; //dev
    private static final String BASE_URL = "https://enlytmed.in/app/mobile_api/"; //live

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
