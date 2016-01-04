package com.redrumming.thecreaturehub.navigation.drawer;

import com.redrumming.thecreaturehub.drawer.DrawerItem;

import java.util.List;

/**
 * Created by ME on 1/3/2016.
 */
public interface NavigationDrawerHelperPresenter {

    List<DrawerItem> getDrawerItems();
    String getTitle();
    void onClick(int position);
}
