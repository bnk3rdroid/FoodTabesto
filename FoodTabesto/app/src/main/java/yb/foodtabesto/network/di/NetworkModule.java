package yb.foodtabesto.network.di;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import yb.foodtabesto.app.di.AppScope;
import yb.foodtabesto.rx.RxSchedulers;

@Module
public class NetworkModule {

    private final Context mContext;

    public NetworkModule(Context context) {
        mContext = context;
    }

    @AppScope
    @Provides
    OkHttpClient providesHttpClient(HttpLoggingInterceptor logger, Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logger);
        builder.cache(cache);
        return builder.build();
    }

    @AppScope
    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @AppScope
    @Provides
    GsonConverterFactory providesGsonClient() {
        return GsonConverterFactory.create();
    }

    @AppScope
    @Provides
    RxJava2CallAdapterFactory providesCallAdapterFactory(RxSchedulers schedulers) {
        return RxJava2CallAdapterFactory.createWithScheduler(schedulers.internet());
    }

    @AppScope
    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @AppScope
    @Provides
    File provideCacheFile() {
        return mContext.getFilesDir();
    }

}