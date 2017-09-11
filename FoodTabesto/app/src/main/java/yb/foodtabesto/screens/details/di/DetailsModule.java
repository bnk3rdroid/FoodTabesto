package yb.foodtabesto.screens.details.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import yb.foodtabesto.data.Order;
import yb.foodtabesto.network.api.FoodApi;
import yb.foodtabesto.rx.RxSchedulers;

/**
 * DI module for Details.
 */
@Module
public class DetailsModule {

    private final int mId;

    public DetailsModule(int id) {
        mId = id;
    }

    @DetailsScope
    @Provides
    Observable<Order> providesOrderObservable(FoodApi foodApi, RxSchedulers schedulers) {
        return foodApi.orderFood(mId)
                .subscribeOn(schedulers.internet())
                .observeOn(schedulers.androidThread());
    }
}
