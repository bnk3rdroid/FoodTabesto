package yb.foodtabesto.screens.details.di;

import dagger.Component;
import yb.foodtabesto.app.di.AppComponent;
import yb.foodtabesto.screens.details.DetailsActivity;

/**
 * DI component for Details.
 */
@SuppressWarnings("WeakerAccess")
@DetailsScope
@Component(
        dependencies = AppComponent.class,
        modules = DetailsModule.class
)
public interface DetailsComponent {
    void inject(DetailsActivity detailsActivity);
}
