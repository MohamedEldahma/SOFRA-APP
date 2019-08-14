package com.example.pro.sofranewapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.data.api.ApiSofraModel;
import com.example.pro.sofranewapp.data.api.RerofitSofraClint;
import com.example.pro.sofranewapp.data.model.clint.pushregistertokenclient.PushRegisterTokenClient;
import com.example.pro.sofranewapp.data.model.clint.removrgistertokenclient.RemovRegisterTokenClient;
import com.example.pro.sofranewapp.helper.HelperMethod;
import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.clintorder.HomeOrderFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.MyOrderViewPgerFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint.LoginOrderFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.loginclint.ProfileClintFragment;
import com.example.pro.sofranewapp.ui.frgment.clintorder.notificationclint.NotifcationOrderClint;
import com.example.pro.sofranewapp.ui.frgment.general.ContactManagmentFragment;
import com.example.pro.sofranewapp.ui.frgment.resturant.offers.MyOffer;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.pro.sofranewapp.helper.HelperMethod.hideView;
import static com.example.pro.sofranewapp.helper.HelperMethod.replaceFrag;

public class OrderFoodActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.nav_view_food)
    NavigationView navViewFood;
    @BindView(R.id.drawer_layout_order)
    DrawerLayout drawerLayoutOrder;
    TextView titel;
    String api_token;
    String refreshedToken;
    ApiSofraModel apiSofraModel;
//    ImageButton imageButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        ButterKnife.bind(this);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        api_token = SharedPrefrancClass.LoadStringData(OrderFoodActivity.this,"CLIENT_API_TOKEN");
//        api_token  = SharedPrefrancClass.LoadStringData(OrderFoodActivity.this, "api_token_clint");
//        api_token = SharedPrefrancClass.LoadStringData(this, "api_token_clint");
        api_token = "K1X6AzRlJFeVbGnHwGYsdCu0ETP1BqYC7DpMTZ3zLvKgU5feHMvsEEnKTpzh";
        titel = (TextView) findViewById(R.id.title_toolParr);
        apiSofraModel = RerofitSofraClint.getClient().create(ApiSofraModel.class);
        pushNotification();
        final DrawerLayout drawer = findViewById(R.id.drawer_layout_order);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view_food);
        navigationView.setNavigationItemSelectedListener(this);

        View navView = navigationView.getHeaderView(0);
        View imageButton = navView.findViewById(R.id.ptt_img_setting);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment login = new LoginOrderFragment();
                selectFragment(login);
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        Fragment homOrder = new HomeOrderFragment();
        selectFragment(homOrder);
        titel.setText("طلب الطعام");


    }


    public void pushNotification() {
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        apiSofraModel.pushNotificationRegisteTokenClint(refreshedToken
                , "android", api_token).enqueue(new Callback<PushRegisterTokenClient>() {
            @Override
            public void onResponse(Call<PushRegisterTokenClient> call, Response<PushRegisterTokenClient> response) {
                String msg = response.body().getMsg();
                if (response.body().getStatus() == 1) {
                    Toast.makeText(OrderFoodActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderFoodActivity.this, "False" + msg, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<PushRegisterTokenClient> call, Throwable t) {
                Toast.makeText(OrderFoodActivity.this, "Erorrrr", Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_order);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.order_food, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_back) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
            Fragment homOrder = new HomeOrderFragment();
            selectFragment(homOrder);
            titel.setText("الرئيسيه");

        } else if (id == R.id.nav_myorder) {
            MyOrderViewPgerFragment myRequewstOrderFragment = new MyOrderViewPgerFragment();
            HelperMethod.replaceFrag(myRequewstOrderFragment, getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);
            titel.setText("طلباتي");
        } else if (id == R.id.nav_alerts) {

            replaceFrag(new NotifcationOrderClint(), getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);
            titel.setText("التنبيهات");

        } else if (id == R.id.nav_new_offer) {
            MyOffer myOffer = new MyOffer();
            replaceFrag(myOffer, getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);
            titel.setText("جديد العروض");

        } else if (id == R.id.nav_about_app) {
            ProfileClintFragment profileClintFragment = new ProfileClintFragment();
            HelperMethod.replaceFrag(profileClintFragment, getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);
            titel.setText("عن التطبيق");


        } else if (id == R.id.nav_term_condetion) {
            titel.setText("الشروط والاحكام");


        } else if (id == R.id.nav_shar_app) {
            titel.setText("شارك التطبيق");


        } else if (id == R.id.nav_contacuus) {
            ContactManagmentFragment contactManagmentFragment = new ContactManagmentFragment();
            HelperMethod.replaceFrag(contactManagmentFragment, getSupportFragmentManager(), R.id.id_fram_Home_nvigation1);
            titel.setText("تواصل معنا");


        } else if (id == R.id.nav_signout_order) {
            SharedPrefrancClass.clean(OrderFoodActivity.this);
            removToken();
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_order);
        drawer.closeDrawer(GravityCompat.START);
//        pushNotification();
        return true;
    }

    public void removToken() {

        apiSofraModel.pushRemoveTokenClint(refreshedToken, api_token)
                .enqueue(new Callback<RemovRegisterTokenClient>() {
                    @Override
                    public void onResponse(Call<RemovRegisterTokenClient> call, Response<RemovRegisterTokenClient> response) {


                    }

                    @Override
                    public void onFailure(Call<RemovRegisterTokenClient> call, Throwable t) {

                    }
                });


    }

    public void selectFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_fram_Home_nvigation1, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
