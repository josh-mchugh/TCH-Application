package com.redrumming.thecreaturehub.navigation;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.redrumming.thecreaturehub.util.FragmentTags;
import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.drawer.DrawerRecyclerAdapter;
import com.redrumming.thecreaturehub.util.RecyclerOnItemClickListener;

/**
 * Created by ME on 12/10/2015.
 */
public class NavigationDrawerHelper implements NavigationDrawerHelperView{

    private static NavigationDrawerHelper INSTANCE = null;

    private Activity activity;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerView recyclerView;

    private NavigationDrawerHelperPresenter presenter;

    private NavigationDrawerHelper(){

    }

    public static NavigationDrawerHelper get(){

        if(INSTANCE == null){

            INSTANCE = new NavigationDrawerHelper();
        }

        return INSTANCE;
    }

    public void init(Activity activity){

        this.activity = activity;
        presenter = new NavigationDrawerHelperPresenterImpl(this);

        recyclerView = (RecyclerView) activity.findViewById(R.id.drawer_recycler_view);
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);

        initializeDrawerLayout();
        initializeRecyclerView();
    }



    private void initializeDrawerLayout(){

        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        initializeDrawerToggle();

        drawerLayout.post(new Runnable() {

            @Override
            public void run() {

                drawerToggle.syncState();
            }
        });

        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void initializeDrawerToggle(){

        drawerToggle = new ActionBarDrawerToggle(
                activity,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                ((AppCompatActivity) activity).supportInvalidateOptionsMenu();

                ((AppCompatActivity) activity).getSupportActionBar().setTitle(presenter.getTitle());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                ((AppCompatActivity) activity).supportInvalidateOptionsMenu();

                ((AppCompatActivity) activity).getSupportActionBar().setTitle(R.string.app_name);
            }
        };
    }

    private void initializeRecyclerView(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);

        DrawerRecyclerAdapter adapter = new DrawerRecyclerAdapter(presenter.getDrawerItems());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(activity, itemClickListener));
    }

    public ActionBarDrawerToggle getDrawerToggle() {

        return drawerToggle;
    }

    public boolean isDrawerOpen(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            return true;
        }

        return false;
    }

    public void closeDrawer(){

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public boolean onDrawerOptionsItemSelected(MenuItem item){

        return drawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick() {

        drawerLayout.closeDrawer(GravityCompat.START);

        updateViewPagerFragments();
    }

    private void updateViewPagerFragments(){

        TabbedContent tabbedContent  = (TabbedContent) ((AppCompatActivity) activity).getSupportFragmentManager().findFragmentByTag(TabbedContent.TAG);

        if(tabbedContent != null){

            tabbedContent.updateTabbedContent();
        }
    }

    private RecyclerOnItemClickListener.OnItemClickListener itemClickListener = new RecyclerOnItemClickListener.OnItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {

            presenter.onClick(position);
        }
    };
}
