package com.revolve44.fragments22;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //api.openweathermap.org/data/2.5/weather?q=moscow&appid=1b87fee17221ed7893aa488cff08bfa2

    public static String BaseUrl = "https://api.openweathermap.org/";
    public static String CITY;
    public static String AppId = "1b87fee17221ed7893aa488cff08bfa2";
    public static String MC = "&units=metric&appid=";

    public TextView temperatureText;
    public TextView windText;

    public static String lat = "80.75";
    public static String lon = "35.61";
    public static String metric = "metric";


    //Variables
    public float NominalPower = 100;//????????????????????????????????
    public float CurrentPower;
    public float cloud;
    public float wind;
    public float temp;
    public boolean isDataAvailable = false;
    public final LinkedHashMap<Long, Float> dataMap = new LinkedHashMap<>();

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final float TEXT2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove action bar, if i add .hide() before setContentView() its immediately works! Wonders!!!
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        if (NominalPower>0){
            getCurrentData();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void getCurrentData() {
        CITY = "Graz";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherResponse> call = service.getCurrentWeatherData(CITY, metric, AppId);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    isDataAvailable = true;
                    cloud = weatherResponse.clouds.all;
                    temp = weatherResponse.main.temp;
                    wind = weatherResponse.wind.speed;
                    if (cloud >-1 ){
                        CurrentPower = NominalPower - NominalPower*(cloud/100);
                    }else{
                        CurrentPower = 404;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Fail in Response"+t.getMessage();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        Call<WeatherForecastResponse> forecastCall = service.getDailyData(CITY, MainActivity.AppId);
        forecastCall.enqueue(new Callback<WeatherForecastResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherForecastResponse> forecastCall, @NonNull Response<WeatherForecastResponse> response) {
                if (response.code() == 200) {
                    WeatherForecastResponse weatherResponse = response.body();
                    assert weatherResponse != null;
                    ArrayList<WeatherResponse> list = weatherResponse.list;
                    if (dataMap.size() == 0){
                        for(WeatherResponse wr: list){
                            dataMap.put((long)wr.dt * 1000, NominalPower * wr.clouds.all / 100);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherForecastResponse> forecastCall, @NonNull Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Fail in Response"+t.getMessage();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        Context context = getApplicationContext();
        CharSequence text = "Hello toast! " + cloud + " and "+ temp;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    public Float getCurrentPowerData() {
        return CurrentPower;
    }
    public String getCityData() {
        return CITY;
    }
    public Float getTempData() {
        return temp;
    }
    public Float getWindData() { return wind; }
    public Boolean isDataAvailable(){ return isDataAvailable; }
    public LinkedHashMap<Long, Float> getDataPointsData() { return dataMap; }
    public Float getNominalPower() {return NominalPower;}
    public void runforecast() {
        getCurrentData();
    }



}
