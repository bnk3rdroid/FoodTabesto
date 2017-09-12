package yb.foodtabesto.rx.di;

import dagger.Module;
import dagger.Provides;
import yb.foodtabesto.app.di.AppScope;
import yb.foodtabesto.rx.DefaultSchedulers;
import yb.foodtabesto.rx.RxSchedulers;

@Module
public class RxModule {

    @AppScope
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new DefaultSchedulers();
    }

}
