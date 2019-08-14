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

import com.example.pro.sofranewapp.helper.SharedPrefrancClass;
import com.example.pro.sofranewapp.ui.frgment.resturant.HomeSelFragment;
import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.ui.frgment.resturant.loginResturant.LoginResturantFragment;
import com.example.pro.sofranewapp.ui.frgment.resturant.item.MyItemFragment;
import com.example.pro.sofranewapp.ui.frgment.resturant.offers.MyOffer;
import com.example.pro.sofranewapp.ui.frgment.resturant.order.SubmitOrderResturantFragment;

import static com.example.pro.sofranewapp.helper.HelperMethod.replaceFrag;

public class SelFoodActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String api_tokenResturant;
    TextView  titel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_food);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        api_tokenResturant = SharedPrefrancClass.LoadStringData(SelFoodActivity.this,"api_tokenResturant");
        titel =(TextView)findViewById(R.id.title_toolParr);



       final DrawerLayout drawer = findViewById(R.id.drawer_layout_sel);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_sel);
        navigationView.setNavigationItemSelectedListener(this);
        View navView=navigationView.getHeaderView(0);
        View imageButton = navView.findViewById(R.id.ptt_img_setting);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginResturant();
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        loginResturant();

    }
    public  void  loginResturant(){
        Fragment loginResturant = new LoginResturantFragment();
        addFragment(loginResturant);
//        titel.setText("تسجيل الدخول");

    }
    public void addFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.id_fram_Home_nvigation1,fragment);
        fragmentTransaction.addToBackStack(null).commit();

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_sel);
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
            replaceFrag(new LoginResturantFragment(),getSupportFragmentManager(),R.id.id_fram_Home_nvigation1);
            titel.setText("الصفحه الرئيسيه");

            // Handle the camera action
        } else if (id == R.id.nav_myorder) {
            MyItemFragment myItemFragment = new MyItemFragment();
            replaceFrag(myItemFragment,getSupportFragmentManager(),R.id.id_fram_Home_nvigation1);
            titel.setText("منتجاتي");

        } else if (id == R.id.nav_alerts) {
            SubmitOrderResturantFragment submitOrderResturantFragment = new SubmitOrderResturantFragment();
            replaceFrag(submitOrderResturantFragment,getSupportFragmentManager(),R.id.id_fram_Home_nvigation1);
            titel.setText("الطلبات المقدمه");

        } else if (id == R.id.nav_new_offer) {
            MyOffer myOffer = new MyOffer();
            replaceFrag(myOffer,getSupportFragmentManager(),R.id.id_fram_Home_nvigation1);
            titel.setText("عروضي");

        } else if (id == R.id.nav_about_app) {
            titel.setText("عن التطبيق");

        } else if (id == R.id.nav_term_condetion) {
            titel.setText("الشروط والاحكام");

        }else if (id == R.id.nav_shar_app) {
            titel.setText("شارك التطبيق ");

        }else if (id == R.id.nav_contacuus) {
            titel.setText("تواصل معنا");

        }else if (id == R.id.nav_signout_sel) {
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout_sel);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
