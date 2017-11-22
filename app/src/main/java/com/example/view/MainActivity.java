package com.example.view;

import android.media.DrmInitData;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private List<WashCarShopBean> delicacyCleanList = new ArrayList<>();
    private AdapterCommonCleanRv mAdapterCommonCleanRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"data deleted",Snackbar.LENGTH_LONG)
                        .setAction("undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ToastUtil.show("snack",MainActivity.this);
                            }
                        }).show();
            }
        });
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapterCommonCleanRv = new AdapterCommonCleanRv(this, delicacyCleanList);
        recyclerView.setAdapter(mAdapterCommonCleanRv);
        initData();
    }

    private void initData() {
        OkHttpUtils.get()
                .url("http://www.xinhangxian.cn/main/api/appapi/shop.ashx?op=xiche&code=008002009&orderid=1&area=&lat=30.934044&lng=121.46017&pageindex=1")
                .build()
                .execute(new JsonStringCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        List<WashCarShopBean> washCarShops = GsonUtils.fromJsonArray(result, WashCarShopBean.class);
                        delicacyCleanList.clear();
                        delicacyCleanList.addAll(washCarShops);
                        mAdapterCommonCleanRv.notifyDataSetChanged();
                    }

                    @Override
                    public void onFail(String error) {

                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.back:
                ToastUtil.show("back",this);
                break;
            case R.id.delete:
                ToastUtil.show("delete",this);
                break;
            case R.id.setting:
                ToastUtil.show("setting",this);
                break;
            default:
        }
        return true;
    }
}
