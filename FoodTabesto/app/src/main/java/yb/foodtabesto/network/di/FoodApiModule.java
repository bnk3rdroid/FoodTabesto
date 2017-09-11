package yb.foodtabesto.network.di;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yb.foodtabesto.BuildConfig;
import yb.foodtabesto.app.di.AppScope;
import yb.foodtabesto.network.api.FoodApi;

/**
 * DI module for the API.
 */
@Module
public class FoodApiModule {

    @AppScope
    @Provides
    FoodApi providesRetrofit(OkHttpClient client, GsonConverterFactory converterFactory,
                                     RxJava2CallAdapterFactory callAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .client(client)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build().create(FoodApi.class);
    }

}
