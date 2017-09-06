package yb.foodtabesto.screens.home.di;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import yb.foodtabesto.data.Food;
import yb.foodtabesto.screens.home.FoodAdapter;

/**
 * DI module for Home.
 */
@Module
public class HomeModule {

    @HomeScope
    @Provides
    public FoodAdapter providesFoodAdapter() {
        return new FoodAdapter(new ArrayList<Food>());
    }

}
