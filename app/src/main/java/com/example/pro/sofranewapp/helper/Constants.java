package com.example.pro.sofranewapp.helper;

public abstract class Constants {

    public static class SharedKeys {
        public static final String SHARED_PREF_NAME = "My_Shared";
        public static final String RESTAURANT_SHARED_PREF_NAME = "resturant_Shared";

        /* UserClient Type Key */
        public static final String USER_TYPE = "user_type";

        /* API Token Key */
        public static final String RESTAURANT_API_TOKEN = "resturant_api_token";
        public static final String CLIENT_API_TOKEN = "client_api_token";

        /* CompleetRestaurant Register EditProfileData Method Key */
        public static final String RESTAURANT_ID = "rest_id";
        public static final String RESTAURANT_REGION_ID = "regionId";
        public static final String RESTAURANT_NAME = "name";
        public static final String RESTAURANT_EMAIL = "email";
        public static final String RESTAURANT_DELIVERY_COAST = "DeliveryCost";
        public static final String RESTAURANT_MINIMUM_CHARGER = "MinimumCharger";
        public static final String RESTAURANT_PHONE = "phone";
        public static final String RESTAURANT_WHATS_APP = "whatsapp";
        public static final String RESTAURANT_AVAILABILITY = "avilability";


        public static final String RESTAURANT_REGION = "region";
        public static final String RESTAURANT_CITY = "city";

        /* Login EditClient EditProfileData Method Key */
        public static final String CLIENT_NAME = "clint_name";
        public static final String CLIENT_EMAIL = "clint_email";
        public static final String CLIENT_PHONE = "clint_phone";
        public static final String CLIENT_REGION_ID = "clint_regionId";
        public static final String CLIENT_ADDRESS = "clint_address";
        public static final String CLIENT_CITY = "clint_city";
        public static final String CLIENT_REGION = "clint_region";
    }

    public static class FragmentsKeys {

        /* Communication (RestaurantFragment & RestaurantDetailsFragment) */
        public static final String ID = "id";

        /* Communication (TapFoodMenuFragment & MenuTapItemDetailsFragment) */
        public static final String PHOTO = "photo";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String PRICE = "price";
        public static final String TIME = "time";
        public static final String _ID = "id";


        /* ConnectWithUsFragment */
        public static final String COMPLAINT = "complaint";
        public static final String SUGGESTION = "suggestion";
        public static final String INQUIRY = "inquiry";

        /* Orders Basket Fragment EditProfileData Keys */
        public static final String ORDER_NAME = "orde_name";
        public static final String ORDER_PHOTO = "orde_photo";
        public static final String ORDER_PRICE = "orde_price";
        public static final String ORDER_QUANTITY = "orde_quantity";
        public static final String ORDER_ID = "orde_id";
        public static final String ORDER_SPECIAL = "orde_special";

        /* Update My Product CompleetItem */
        public static final String PRODUCT_PHOTO = "pro_photo";
        public static final String PRODUCT_NAME = "pro_name";
        public static final String PRODUCT_DESCRIPTION = "pro_description";
        public static final String PRODUCT_PRICE = "pro_price";
        public static final String PRODUCT_TIME = "pro_time";
        public static final String PRODUCT_ID = "pro_id";

    }

}
