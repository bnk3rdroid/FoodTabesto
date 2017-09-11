package yb.foodtabesto.screens.details.di;

import dagger.Component;
import yb.foodtabesto.app.di.AppComponent;
import yb.foodtabesto.screens.details.DetailsActivity;

/**
 * DI component for Details.
 */
@DetailsScope
@Component(
        dependencies = AppComponent.class,
        modules = DetailsModule.class
)
interface DetailsComponent {
    void inject(DetailsActivity detailsActivity);
}
