package yb.foodtabesto.app.di;

import dagger.Component;
import yb.foodtabesto.app.App;
import yb.foodtabesto.network.api.FoodApi;
import yb.foodtabesto.network.di.FoodApiModule;
import yb.foodtabesto.network.di.NetworkModule;
import yb.foodtabesto.rx.RxSchedulers;
import yb.foodtabesto.rx.di.RxModule;

/**
 * Dagger component for the application.
 */
@AppScope
@Component(
        modules = {
                RxModule.class,
                NetworkModule.class,
                FoodApiModule.class
        }
)
public interface AppComponent {

    void inject(App app);

    RxSchedulers rxSchedulers();
    FoodApi foodApi();
}
