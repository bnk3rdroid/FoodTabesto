package yb.foodtabesto.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Useful threads for Rx processes.
 */
public class DefaultSchedulers implements RxSchedulers {

    private static Executor BACKGROUND_EXECUTOR = Executors.newCachedThreadPool();
    private static Scheduler BACKGROUND_SCHEDULERS = Schedulers.from(BACKGROUND_EXECUTOR);
    private static Executor INTERNET_EXECUTOR = Executors.newCachedThreadPool();
    @SuppressWarnings("WeakerAccess")
    public static Scheduler INTERNET_SCHEDULERS = Schedulers.from(INTERNET_EXECUTOR);

    @Override
    public Scheduler runOnBackground() {
        return BACKGROUND_SCHEDULERS;
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler compute() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler androidThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler internet() {
        return INTERNET_SCHEDULERS;
    }

}