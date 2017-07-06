package zxzq.xiaobai.ypbhscshop;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import zxzq.xiaobai.ypbhscshop.base.utils.TestFragment;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener {
private BottomBar mBottomBar;

    private TestFragment mHomeFragment;
    private TestFragment mCategoryFragment;
    private TestFragment mCartFragment;
    private TestFragment mMineFragment;

    private Fragment mCurrentFragment;// 记录一下当前展示的Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomBar= (BottomBar) findViewById(R.id.bottom_bar);
        mBottomBar.setOnTabSelectListener(this);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        // 根据tabId,处理不同的事件：切换展示不同的Fragment
        switch (tabId){
            case R.id.tab_home://首页

                if (mHomeFragment==null){
                    mHomeFragment = TestFragment.newInstance("HomeFragment");

                }
                // 切换Fragment
                switchFragment(mHomeFragment);
                break;
            case R.id.tab_category://分类

                if (mCategoryFragment==null){
                    mCategoryFragment = TestFragment.newInstance("CategoryFragment");

                }
                // 切换
                switchFragment(mCategoryFragment);
                break;
            case R.id.tab_cart://购物车
                if (mCartFragment==null){
                    mCartFragment = TestFragment.newInstance("CartFragment");

                }
                switchFragment(mCartFragment);
                break;
            case R.id.tab_mine://我的
                if (mMineFragment==null){
                    mMineFragment = TestFragment.newInstance("MineFragment");

                }
                switchFragment(mMineFragment);
                break;
            default:
                throw new UnsupportedOperationException("unSupport");
        }
    }


    // 作用：就是帮助进行Fragment的切换
    private void switchFragment(Fragment fragment) {
        // replace       和add、show、hide的方式。

        if (mCurrentFragment==fragment) return;
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        // 隐藏当前Fragment
        if (mCurrentFragment!=null){
            transaction.hide(mCurrentFragment);
        }
        if (fragment.isAdded()){
            // 如果已经添加到FragmentManager里面，就展示
            transaction.show(fragment);
        }else {

            // 为了方便找到Fragment，设置Tag
            String tag;
            // 做一个判断，添加不同的tag
            if (fragment instanceof TestFragment){
                tag = ((TestFragment)fragment).getArgumentsText();
            }else {
                // 将fragment的类名作为tag
                tag = fragment.getClass().getName();
            }
            // 添加
            transaction.add(R.id.layout_container,fragment,tag);
        }
        transaction.commit();

        // 重新记录一下当前的Fragment
        mCurrentFragment = fragment;
    }

    // 处理一下返回键
    @Override
    public void onBackPressed() {
        if (mCurrentFragment!=mHomeFragment){
            // 如果不在首页上，就切换到首页
            mBottomBar.selectTabWithId(R.id.tab_home);
            return;
        }

        // 如果不是首页，不关闭程序，退到后台运行
        moveTaskToBack(true);
    }
}
