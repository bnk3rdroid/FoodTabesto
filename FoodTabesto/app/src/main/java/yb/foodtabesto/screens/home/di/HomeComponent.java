package yb.foodtabesto.screens.home.di;

import dagger.Component;
import yb.foodtabesto.app.di.AppComponent;
import yb.foodtabesto.screens.home.HomeActivity;

/**
 * Dagger component (Home).
 */
@SuppressWarnings("WeakerAccess")
@HomeScope
@Component(
        dependencies = AppComponent.class,
        modules = HomeModule.class
)
public interface HomeComponent {
    void inject(HomeActivity activity);
}
