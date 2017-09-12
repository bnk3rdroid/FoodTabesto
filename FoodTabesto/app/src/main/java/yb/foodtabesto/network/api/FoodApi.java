package yb.foodtabesto.network.api;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Path;
import yb.foodtabesto.data.Order;

/**
 * Retrofit endpoints for the API.
 */
public interface FoodApi {

    @POST("/order/{id_product}")
    Observable<Order> orderFood(@Path("id_product") int id);

}
