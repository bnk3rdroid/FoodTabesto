package yb.foodtabesto.app;

import android.app.Application;

import yb.foodtabesto.app.di.AppComponent;
import yb.foodtabesto.app.di.DaggerAppComponent;
import yb.foodtabesto.network.di.NetworkModule;

/**
 * Application class.
 */
public class App extends Application {

    private static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjection();
    }

    private void initInjection() {
        mComponent = DaggerAppComponent.builder().networkModule(new NetworkModule(this)).build();
        mComponent.inject(this);
    }

    public static AppComponent getAppComponent() {
        return mComponent;
    }

}
