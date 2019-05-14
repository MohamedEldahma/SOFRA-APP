package com.example.pro.sofranewapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.pro.sofranewapp.R;
import com.example.pro.sofranewapp.helper.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.ptt_food_demand)
    Button pttFoodDemand;
    @BindView(R.id.ptt_food_sel)
    Button pttFoodSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ptt_food_demand, R.id.ptt_food_sel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ptt_food_demand:
//                SharedPrefManager.getInstance(SplashActivity.this).setKeyOrderSell("ORDERFOOD");
//                finish();
                startActivity(new Intent(SplashActivity.this,OrderFoodActivity.class));

                break;
            case R.id.ptt_food_sel:
//                SharedPrefManager.getInstance(SplashActivity.this).setKeyOrderSell("SELFOOD");
//                finish();
                startActivity(new Intent(SplashActivity.this,SelFoodActivity.class));
                break;
        }
    }
}
