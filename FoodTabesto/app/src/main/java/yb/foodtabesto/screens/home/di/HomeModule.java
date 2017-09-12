package yb.foodtabesto.screens.home.di;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import yb.foodtabesto.data.Food;
import yb.foodtabesto.rx.RxSchedulers;
import yb.foodtabesto.screens.home.FoodAdapter;

/**
 * DI module for Home.
 */
@SuppressWarnings("WeakerAccess")
@Module
public class HomeModule {

    //Some random food thumbnails
    private static final String[] mImages = new String[]{
            "https://www.splenda.com/sites/splenda_us/files/styles/original_size/public/taco-images/0-0-recipe-taco-2_0.png?itok=Qkh6m3rt",
            "https://is1-ssl.mzstatic.com/image/thumb/Purple71/v4/ff/b1/63/ffb163a8-d416-a817-1f5a-e885eb7a6a93/source/256x256bb.jpg",
            "https://i.pinimg.com/736x/2a/19/cc/2a19cc41b71fe23142e06d41973dc7d0.jpg",
            "https://students.usask.ca/images/icons/healthier-food-choices.png",
            "https://i.pinimg.com/736x/2e/38/0c/2e380ca7c915736e75d904e6ad4724f9.jpg"
    };

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
            Random ran = new Random();
            int pos = ran.nextInt(mImages.length);
            foodData.add(new Food(i + 1, "Food " + (i + 1), i + ",99 €", mImages[pos]));
        }

        return Observable.just(foodData)
                .observeOn(schedulers.compute())
                .subscribeOn(schedulers.androidThread());
    }

}
