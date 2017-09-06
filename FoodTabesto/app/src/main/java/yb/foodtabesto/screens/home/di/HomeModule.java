package yb.foodtabesto.screens.home.di;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import yb.foodtabesto.data.Food;
import yb.foodtabesto.rx.RxSchedulers;
import yb.foodtabesto.screens.home.FoodAdapter;

/**
 * DI module for Home.
 */
@Module
public class HomeModule {

    @HomeScope
    @Provides
    FoodAdapter providesFoodAdapter() {
        return new FoodAdapter(new ArrayList<>());
    }

    @HomeScope
    @Provides
    Observable<List<Food>> providesFoodDataObservable(RxSchedulers schedulers) {
        List<Food> foodData = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            foodData.add(new Food("Food name", "1,99 €",
                    "https://students.usask.ca/images/icons/healthier-food-choices.png"));
        }

        return Observable.just(foodData)
                .observeOn(schedulers.compute())
                .subscribeOn(schedulers.androidThread());
    }

}
