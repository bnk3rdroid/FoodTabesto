package yb.foodtabesto.app.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * DI scope for the application.
 */
@Retention(RetentionPolicy.RUNTIME)
@Scope
public @interface AppScope {
}
