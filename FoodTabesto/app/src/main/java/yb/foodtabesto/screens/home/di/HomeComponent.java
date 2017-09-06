package yb.foodtabesto.screens.home.di;

import dagger.Component;
import yb.foodtabesto.screens.home.HomeActivity;

/**
 * Dagger component (Home).
 */
@HomeScope
@Component(
        modules = HomeModule.class
)
public interface HomeComponent {

    void inject(HomeActivity activity);

}
