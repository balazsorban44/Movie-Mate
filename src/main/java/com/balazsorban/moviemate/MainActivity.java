package com.balazsorban.moviemate;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.balazsorban.moviemate.Fragments.Discover.DiscoverContent.DiscoverItem;
import com.balazsorban.moviemate.Fragments.Discover.DiscoverFragment;
import com.balazsorban.moviemate.Fragments.Discover.DiscoverFragment.OnListFragmentInteractionListener;


public class MainActivity extends FragmentActivity implements OnListFragmentInteractionListener{

    private TextView mTextMessage;
    private Toast mToast;
    DiscoverFragment mDiscoverFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    createFragment();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_settings);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onListFragmentInteraction(DiscoverItem item) {
        mToast = Toast.makeText(this, "Clicked " + item.title, Toast.LENGTH_SHORT);
        mToast.show();
    }

    void createFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mDiscoverFragment = new DiscoverFragment();
        fragmentTransaction.replace(R.id.discover_fragment, mDiscoverFragment);
        fragmentTransaction.commit();
    }
}
