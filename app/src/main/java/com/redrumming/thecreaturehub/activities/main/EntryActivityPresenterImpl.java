package com.redrumming.thecreaturehub.activities.main;

import com.redrumming.thecreaturehub.channel.ChannelsContainer;

/**
 * Created by ME on 1/2/2016.
 */
public class EntryActivityPresenterImpl implements EntryActivityPresenter{

    private EntryActivityView view;

    public EntryActivityPresenterImpl(EntryActivityView view){
        
        this.view = view;
    }

    @Override
    public String setTitle() {

        return ChannelsContainer.getInstance().getSelectedChannel().getChannelName();
    }
}
