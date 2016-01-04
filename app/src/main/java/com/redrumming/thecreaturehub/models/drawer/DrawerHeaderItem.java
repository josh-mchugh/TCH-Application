package com.redrumming.thecreaturehub.models.drawer;

/**
 * Created by ME on 7/22/2015.
 */
public class DrawerHeaderItem implements DrawerItem{

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getType() {
        return HEADER;
    }
}
