package com.yto.delivery;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.billy.cc.core.component.CC;
import com.billy.cc.core.component.CCResult;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yto.base.activity.MvvmActivity;
import com.yto.base.viewmodel.MvvmBaseViewModel;
import com.yto.delivery.databinding.ActivityMainBinding;
import com.yto.delivery.otherfragments.AccountFragment;
import com.yto.delivery.otherfragments.CategoryFragment;
import com.yto.delivery.otherfragments.ServiceFragment;

import java.lang.reflect.Field;

public class MainActivity extends MvvmActivity<ActivityMainBinding, MvvmBaseViewModel> {
    private Fragment mHomeFragment ;
    private CategoryFragment mCategoryFrament = new CategoryFragment();
    private ServiceFragment mServiceFragment = new ServiceFragment();
    private AccountFragment mAccountFragment = new AccountFragment();
    private Fragment mCurrentFragment;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MvvmBaseViewModel getViewModel() {
        return null;
    }

    @Override
    public int getBindingVariable() {
        return 0;
    }
    @Override
    protected void onRetryBtnClick() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CCResult result = CC.obtainBuilder(getString(R.string.Scan)).setActionName(getString(R.string.Scan_ActionName_getMainScanFragment)).build().call();
        mHomeFragment = (Fragment) result.getDataMap().get(getString(R.string.Scan_parameter_fragment));
        mCurrentFragment = mHomeFragment;
        setToolBar();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//为了解决BottomView(最多五个)超过三个标签后不显示Text的问题
            disableShiftMode(viewDatabinding.bottomView);
        }
        //处理底部Tab切换
        handlerBottomClickEnvent();
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(viewDatabinding.container.getId(),mHomeFragment);
        fragmentTransaction.commit();

    }

    private void setToolBar() {
        //Set Toobar
        setSupportActionBar(viewDatabinding.toolbar);
        //使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标，对应id为Android.R.id.home，对应ActionBar.DISPLAY_SHOW_HOME
        /*getSupportActionBar().setDisplayShowHomeEnabled(true);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.menu_home));*/
        viewDatabinding.titleCenterTv.setText(getString(R.string.menu_home)); //在toolbar 中使用自定义布局的方式
    }

    /**
     * 界面底部Tab页切换逻辑
     */
    public void handlerBottomClickEnvent(){
        viewDatabinding.bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment seleteFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.menu_home:
                        seleteFragment = mHomeFragment;
                        break;
                    case R.id.menu_categories:
                        seleteFragment = mCategoryFrament;
                        break;
                    case R.id.menu_services:
                        seleteFragment = mServiceFragment;
                        break;
                    case R.id.menu_account:
                        CCResult result = CC.obtainBuilder("MyCenter").setActionName("getMyCenterFragment").build().call();
                        seleteFragment = (Fragment) result.getDataMap().get("fragment");
                        break;
                    default:
                        seleteFragment = mHomeFragment;
                        break;
                }
                switchFragment(mCurrentFragment,seleteFragment);
                mCurrentFragment = seleteFragment;
                setTitleAndBtn(menuItem);
                return true;//如果返回false 界面底部Bottom时颜色不会变化
            }
        });
    }

    private void setTitleAndBtn(@NonNull MenuItem menuItem) {
      /*  if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(menuItem.getTitle());
        }*/
         if(getSupportActionBar() != null) {
            viewDatabinding.titleCenterTv.setText(menuItem.getTitle());
        }
       if(mCurrentFragment != mHomeFragment){
            viewDatabinding.titleRightBtn.setVisibility(View.GONE);
        }else{
            viewDatabinding.titleRightBtn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 解决BottomNavigationView 选项超过三个不显示text的问题
     *  注意：BottomNavigationView中的选项最多五个
     * @param view
     */
    @SuppressLint("RestrictedApi")
    private void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
        }
    }

    /**
     * 界面底部Tab页切换
     * @param fromFragment
     * @param toFragment
     */
    private void switchFragment(Fragment fromFragment,Fragment toFragment){
        if (fromFragment != toFragment) {
            FragmentManager manger = getSupportFragmentManager();
            FragmentTransaction transaction = manger.beginTransaction();
            if (!toFragment.isAdded()) {
                if (fromFragment != null) {
                    transaction.hide(fromFragment);
                }
                if (toFragment != null) {
                    transaction.add(R.id.container, toFragment).commit();

                }

            } else {
                if (fromFragment != null) {
                    transaction.hide(fromFragment);
                }
                if (toFragment != null) {
                    transaction.show(toFragment).commit();
                }

            }
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                break;
            }
            // case blocks for other MenuItems (if any)
        }
        return true;
    }
}
