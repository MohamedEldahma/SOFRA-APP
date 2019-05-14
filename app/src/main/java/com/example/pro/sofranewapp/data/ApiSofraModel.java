package com.example.pro.sofranewapp.data;

import com.example.pro.sofranewapp.data.model.clint.clientnewpassword.NewPasswordClient;
import com.example.pro.sofranewapp.data.model.clint.listrestitems.ListResturantItems;
import com.example.pro.sofranewapp.data.model.clint.loginclient.LoginClient;
import com.example.pro.sofranewapp.data.model.clint.myorderclint.MyOrderClint;
import com.example.pro.sofranewapp.data.model.clint.registerClient.RegisterClient;
import com.example.pro.sofranewapp.data.model.clint.resetpasswordclient.ResetPasswordClient;
import com.example.pro.sofranewapp.data.model.general.cities.Cities;
import com.example.pro.sofranewapp.data.model.general.getcommentreview.GetCommentReview;
import com.example.pro.sofranewapp.data.model.general.regions.Regions;
import com.example.pro.sofranewapp.data.model.general.restaurantdetail.RestaurantDetail;
import com.example.pro.sofranewapp.data.model.general.restaurantslslt.RestaurantsLslt;
import com.example.pro.sofranewapp.data.model.resturant.accceptorderresturant.AccceptOrderResturant;
import com.example.pro.sofranewapp.data.model.resturant.additem.AddItem;
import com.example.pro.sofranewapp.data.model.resturant.addnewofferResturant.AddNewOfferResturant;
import com.example.pro.sofranewapp.data.model.resturant.categories.Categories;
import com.example.pro.sofranewapp.data.model.resturant.myitem.MyItem;
import com.example.pro.sofranewapp.data.model.resturant.myoffers.MyOffers;
import com.example.pro.sofranewapp.data.model.resturant.myorderresturant.MyOrderResturant;
import com.example.pro.sofranewapp.data.model.resturant.registerrestaurant.RegisterRestaurant;
import com.example.pro.sofranewapp.data.model.resturant.restaurantlogin.RestaurantLogin;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiSofraModel {

    @POST("client/login")
    @FormUrlEncoded
    Call<LoginClient> userAddLogin(@Field("email") String email
            , @Field("password") String password);

    @POST("client/register")
    @FormUrlEncoded
    Call<RegisterClient> userAddRegister(@Field("name") String name
            , @Field("email") String email
            , @Field("password") String password
            , @Field("password_confirmation") String password_confirmation
            , @Field("phone") String phone
            , @Field("address") String address
            , @Field("region_id") int region_id);

    @POST("client/reset-password")
    @FormUrlEncoded
    Call<ResetPasswordClient> addResetPassword(@Field("email") String email);

    @POST("client/new-password")
    @FormUrlEncoded
    Call<NewPasswordClient> addNewPassword(@Field("code") String code
            , @Field("password") String password
            , @Field("password_confirmation") String password_confirmation);


    @GET("cities")
    Call<Cities> getCities();

    @GET("regions")
    Call<Regions> getRegions(@Query("city_id") int city_id);

//  @GET("regions-not-paginated")
//    Call<RegionsNotPaginated>getRegionsNotPaginat(@Query("name") String name
//                                    , @Query("city_id") int city_id);
//
//  @GET("cities-not-paginated")
//    Call<CitiesNotPaginated>getCitiesNotPaginat(@Query("name") String name );
//


    @POST("restaurant/register")
    @Multipart
    Call<RegisterRestaurant> addRegisterResturant(@Part("name") RequestBody name
            , @Part("email") RequestBody email
            , @Part("password") RequestBody password
            , @Part("password_confirmation") RequestBody password_confirmation
            , @Part("phone") RequestBody phone
            , @Part("whatsapp") RequestBody whatsapp
            , @Part("region_id") RequestBody region_id
            , @Part("categories[]") List<RequestBody> categories
            , @Part("delivery_cost") RequestBody delivery_cost
            , @Part("minimum_charger") RequestBody minimum_charger
            , @Part MultipartBody.Part photo
            , @Part("availability") RequestBody availability);


    @POST("restaurant/login")
    @FormUrlEncoded
    Call<RestaurantLogin> addLoginResturant(@Field("email") String email,
                                           @Field("password") String password);

    @GET("restaurant/my-items")
    Call<MyItem> getMyItemFood(@Query("api_token") String api_token,
                               @Query("page") int page);

    @Multipart
    @POST("restaurant/new-item")
    Call<AddItem> addMyItemFood(@Part("description") RequestBody description,
                                @Part("price") RequestBody price,
                                @Part("preparing_time") RequestBody preparing_time,
                                @Part("name") RequestBody name,
                                @Part("api_token") RequestBody api_token,
                                @Part MultipartBody.Part photo);

    @GET("categories")
    Call<Categories> getCaregoriesResturant();


    @GET("restaurants")
    Call<RestaurantsLslt> getResturantList(@Query("page") int page);

    @GET("restaurant")
    Call<RestaurantDetail> getResturantDetail(@Query("restaurant_id") int restaurant_id);

    @GET("items")
    Call<ListResturantItems> getResturantItem(@Query("restaurant_id") int restaurant_id,
                                              @Query("page") int page);
    @GET("restaurant/reviews")
    Call<GetCommentReview>getComent(@Query("restaurant_id") int restaurant_id,
                                    @Query("page") int page);
    @POST("client/restaurant/review")
    @FormUrlEncoded
    Call<String>addComment(@Field("rate") int rate,
                           @Field("comment") String comment,
                           @Field("restaurant_id") int restaurant_id,
                           @Field("api_token") String api_token);
   @GET("client/my-orders")
    Call<MyOrderClint>getMyOrder(@Query("api_token") String api_token,
                                 @Query("state") String state,
                                 @Query("page") int page);
   @POST("client/confirm-order")
   @FormUrlEncoded
    Call<AccceptOrderResturant>confirmOrderClint(@Field("order_id") int order_id,
                                                 @Field("api_token")String api_token);
    @POST("client/decline-order")
    @FormUrlEncoded
    Call<AccceptOrderResturant>cancelOrderClint(@Field("order_id") int order_id,
                                  @Query("api_token") String api_token);
    @GET("restaurant/my-offers")
    Call<MyOffers>getmyOffer(@Query("api_token") String api_token,
                             @Query("page") int page);
    @GET("restaurant/my-orders")
    Call<MyOrderResturant> getMyOrderResturant(@Query("api_token") String api_token,
                                               @Query("state") String state,
                                               @Query("page")  int page);
    @POST("restaurant/accept-order")
    @FormUrlEncoded
    Call<AccceptOrderResturant>acceptOrderRestrant(@Field("api_token") String api_token,
                                                  @Field("order_id") String order_id);
    @POST("restaurant/reject-order")
    @FormUrlEncoded
    Call<AccceptOrderResturant>cancelOrderRestrant(@Field("api_token") String api_token,
                                                  @Field("order_id") String order_id);

    @POST("restaurant/confirm-order")
    @FormUrlEncoded
    Call<AccceptOrderResturant>confirmOrderRestrant(@Field("api_token") String api_token,
                                                   @Field("order_id") String order_id);


    @Multipart
    @POST("restaurant/new-offer")
    Call<AddNewOfferResturant> addNewOfferRest(@Part("description") RequestBody description,
                                               @Part("price") RequestBody price,
                                               @Part("starting_at") RequestBody starting_at,
                                               @Part("name") RequestBody name,
                                               @Part MultipartBody.Part photo,
                                               @Part("ending_at") RequestBody ending_at,
                                               @Part("api_token") RequestBody api_token);











 /* @GET("ListRestaurantsFilter")
  Call<String>getLRestaurantsFilter(@Query("keywork")String keywork,
                                    @Query("region_id") int region_id,
                                    @Query("categories[0]") List <RequestBody> categories,
                                    @Query("availability") boolean availability,
                                    @Query("page") int page );*/


}
