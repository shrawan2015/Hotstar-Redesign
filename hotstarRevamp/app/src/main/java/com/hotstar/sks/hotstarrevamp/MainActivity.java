package com.hotstar.sks.hotstarrevamp;

import android.app.ActivityOptions;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity  implements  NewsFragment.OnFragmentInteractionListener
        , MovieFragment.OnFragmentInteractionListener , TVFragment.OnFragmentInteractionListener , SportsFragment.OnFragmentInteractionListener , HomeFragment.OnFragmentInteractionListener{

    private Toolbar toolbar;
    private boolean notificationVisible = false;
    private BottomNavigationView mBottomNavigationView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_header_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //noinspection ConstantConditions
        getSupportActionBar().setTitle("hotstar");
        setupBottomNavigation();
        if (savedInstanceState == null) {

            loadHomeFragment();
        }

        bindNavigationDrawer();


    }

    private void setupBottomNavigation() {

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        mBottomNavigationView.forceLayout();
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_home:
                        loadHomeFragment();
                        return true;

                    case R.id.action_tv:
                        loadTVFragment();
                        return true;
                    case R.id.action_movie:
                        loadMovieFragment();
                        return true;
                    case R.id.action_sports:
                        loadSportsFragment();
                        return true;

                    case R.id.action_news:
                        loadNewsFragment();
                        return true;

                }
                return false;
            }
        });
    }


    private void loadHomeFragment() {

        HomeFragment newsFragment = HomeFragment.newInstance("asdfasd","sdfasd");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, newsFragment);
        ft.commit();
    }

    private void loadTVFragment() {

        TVFragment newsFragment = TVFragment.newInstance("asdfasd","sdfasd");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, newsFragment);
        ft.commit();
    }

    private void loadMovieFragment() {

        MovieFragment newsFragment = MovieFragment.newInstance("asdfasd","sdfasd");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, newsFragment);
        ft.commit();
    }

    private void loadSportsFragment() {

        SportsFragment newsFragment = SportsFragment.newInstance("asdfasd","sdfasd");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, newsFragment);
        ft.commit();
    }

    private void loadNewsFragment() {

        NewsFragment newsFragment = NewsFragment.newInstance("asdfasd","sdfasd");
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, newsFragment);
        ft.commit();
    }


    private void bindNavigationDrawer() {


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nvView);

//        val params = snackbarLayout.layoutParams as CoordinatorLayout.LayoutParams
//
//        params.anchorId = child.id
//        params.anchorGravity = Gravity.TOP
//        params.gravity = Gravity.TOP
//        snackbarLayout.layoutParams = params


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                if (id == R.id.action_home) {
                    showSendSnackBar();
                } else if (id == R.id.action_profile) {
                    showSendSnackBar();
                } else if (id == R.id.icon_group) {
                    showSendSnackBar();
                } else if (id == R.id.nav_frag2) {
                    showSendSnackBar();
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }


        });
    }

    private void showSendSnackBar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.placeSnackBar), "message", Snackbar.LENGTH_LONG);

        displaySnackBarWithBottomMargin(snackbar , 0   ,200);
//        Snackbar.make(findViewById(R.id.placeSnackBar), "Show", Snackbar.LENGTH_SHORT).show();

    }

    public void displaySnackBarWithBottomMargin(Snackbar snackbar, int sideMargin, int marginBottom) {
        final View snackBarView = snackbar.getView();
        final CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) snackBarView.getLayoutParams();

        params.setMargins(params.leftMargin + sideMargin,
                params.topMargin,
                params.rightMargin + sideMargin,
                params.bottomMargin + marginBottom);

        snackBarView.setLayoutParams(params);
        snackbar.show();

        Intent intent = new Intent(this, ChildActivity.class);
        intent.putExtra("sdfsf", "MainActivity");
        MainActivity.this.startActivity(intent);
        overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );


//        ActivityOptions options =
//                ActivityOptions.makeCustomAnimation(this, R.anim.fade_in, R.anim.fade_out);
//        MainActivity.this.startActivity(intent , ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//        overridePendingTransition(R.anim.class.sl);



    }

    public void addBottomNavigationBar(){


//        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_map_24dp ,R.color.colorAccent);
//        bottomNavigation.addItem(item1);


//        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
////            public void onTabSelected(int position, boolean wasSelected) {
////                fragment.updateColor(Color.parseColor(colors[position]));
////            }
//        });

//        bottomNavigation.setCurrentItem(0);

//        bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
//        bottomNavigation.setAccentColor(fetchColor(R.color.colorAccent));
//        bottomNavigation.setInactiveColor(fetchColor(R.color.bg_light));
//
//        // Set background color
//        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
//
//// Disable the translation inside the CoordinatorLayout
//        bottomNavigation.setBehaviorTranslationEnabled(false);
//
//// Enable the translation of the FloatingActionButton
////        bottomNavigation.manageFloatingActionButtonBehavior(floatingActionButton);
//
//// Change colors
//        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
//        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
//
//// Force to tint the drawable (useful for font with icon for example)
//        bottomNavigation.setForceTint(true);
//
//// Display color under navigation bar (API 21+)
//// Don't forget these lines in your style-v21
//// <item name="android:windowTranslucentNavigation">true</item>
//// <item name="android:fitsSystemWindows">true</item>
//        bottomNavigation.setTranslucentNavigationEnabled(true);
//
//// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//
//// Use colored navigation with circle reveal effect
//        bottomNavigation.setColored(true);
//
//// Set current item programmatically
//        bottomNavigation.setCurrentItem(1);
//
//// Customize notification (title, background, typeface)
//        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
//
//// Add or remove notification for each item
//        bottomNavigation.setNotification("1", 3);
//// OR
////        AHNotification notification = new AHNotification.Builder()
////                .setText("1")
////                .setBackgroundColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_back))
////                .setTextColor(ContextCompat.getColor(DemoActivity.this, R.color.color_notification_text))
////                .build();
////        bottomNavigation.setNotification(notification, 1);
//
//// Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
//
//// Set listeners
//        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
//            @Override
//            public boolean onTabSelected(int position, boolean wasSelected) {
//                // Do something cool here...
//                return true;
//            }
//        });
//        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
//            @Override public void onPositionChange(int y) {
//                // Manage the new y position
//            }
//        });
    }

    private int fetchColor(@ColorRes int color) {
        return ContextCompat.getColor(this, color);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

