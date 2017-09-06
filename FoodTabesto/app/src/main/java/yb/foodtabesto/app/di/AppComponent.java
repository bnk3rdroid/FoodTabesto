package yb.foodtabesto.app.di;

import dagger.Component;
import yb.foodtabesto.app.App;
import yb.foodtabesto.rx.RxSchedulers;
import yb.foodtabesto.rx.di.RxModule;

/**
 * Dagger component for the application.
 */
@AppScope
@Component(
        modules = RxModule.class
)
public interface AppComponent {

    void inject(App app);

    RxSchedulers rxSchedulers();

}
