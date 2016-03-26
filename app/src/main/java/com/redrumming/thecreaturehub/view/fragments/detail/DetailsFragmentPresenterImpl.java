package com.redrumming.thecreaturehub.view.fragments.detail;

import android.os.Bundle;

import com.redrumming.thecreaturehub.api.youtube.channel.model.Channel;
import com.redrumming.thecreaturehub.async.comments.CommentAsyncListener;
import com.redrumming.thecreaturehub.async.comments.top.TopLevelCommentAsync;
import com.redrumming.thecreaturehub.models.content.video.VideoItem;
import com.redrumming.thecreaturehub.models.detail.DetailItem;
import com.redrumming.thecreaturehub.models.detail.channel.ChannelSectionItem;
import com.redrumming.thecreaturehub.models.detail.comments.CommentContainer;
import com.redrumming.thecreaturehub.models.detail.comments.CommentItemType;
import com.redrumming.thecreaturehub.models.detail.comments.CommentsHeaderItem;
import com.redrumming.thecreaturehub.models.detail.comments.NoCommentsItem;
import com.redrumming.thecreaturehub.models.detail.comments.top.TopLevelCommentItem;
import com.redrumming.thecreaturehub.models.detail.comments.top.TopLevelCommentLoadMoreItem;
import com.redrumming.thecreaturehub.models.detail.comments.top.TopLevelCommentLoadingItem;
import com.redrumming.thecreaturehub.models.detail.description.DescriptionItem;
import com.redrumming.thecreaturehub.view.viewholders.detail.DetailRecyclerAdapterListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 1/8/2016.
 */
public class DetailsFragmentPresenterImpl implements DetailsFragmentPresenter, CommentAsyncListener, DetailRecyclerAdapterListener {

    private DetailsFragmentView view;

    private VideoItem videoItem;
    private Channel channel;
    private CommentContainer commentContainer;
    private List<DetailItem> items;

    public DetailsFragmentPresenterImpl(DetailsFragmentView view){

        this.view = view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Bundle arguments) {

        if(savedInstanceState != null){

            videoItem = savedInstanceState.getParcelable("video");
            channel = savedInstanceState.getParcelable("channel");

            commentContainer = savedInstanceState.getParcelable("comments");
        }

        if(arguments != null){

            videoItem = arguments.getParcelable("video");
            channel = arguments.getParcelable("channel");
        }
    }

    @Override
    public void onCreateView() {

        items = new ArrayList<DetailItem>();

        DescriptionItem descriptionItem = new DescriptionItem();
        descriptionItem.setVideoItem(videoItem);
        items.add(descriptionItem);

        ChannelSectionItem channelSectionItem = new ChannelSectionItem();
        channelSectionItem.setChannel(channel);
        items.add(channelSectionItem);

        if(commentContainer == null) {

            if (videoItem.getId() != null || videoItem.getId().isEmpty() == false) {

                commentContainer = new CommentContainer(videoItem.getId());
                items.add(new TopLevelCommentLoadingItem());
            }

        }else {

            //Add Comment Header as we already have comments to display.
            addCommentHeaderItem();

            if(commentContainer.getCommentItems().size() > 0) {

                addCommentsItems(commentContainer);
                addLoadMoreItem(commentContainer);

            }else {

                addNoCommentsFound();
            }
        }

        if(commentContainer.getCommentItems().size() == 0 && commentContainer.getPageToken() != null){

            TopLevelCommentAsync async = new TopLevelCommentAsync(view.getContext(), this);
            async.execute(commentContainer);
        }
    }

    private void addCommentHeaderItem(){

        CommentsHeaderItem commentsHeaderItem = new CommentsHeaderItem();
        items.add(commentsHeaderItem);
    }

    private void addCommentsItems(CommentContainer container){

        for(int i = 0; i < container.getCommentItems().size(); i++){

            CommentItemType comment = container.getCommentItems().get(i);

            if(comment.getItemType() == CommentItemType.TOP_LEVEL_COMMENT){

                items.add((TopLevelCommentItem) comment);
            }
        }
    }

    private void addLoadMoreItem(CommentContainer commentContainer){

        if(commentContainer.getPageToken() != null && commentContainer.getPageToken().isEmpty() == false) {

            items.add(new TopLevelCommentLoadMoreItem());
        }
    }

    private void addNoCommentsFound(){

        items.add(new NoCommentsItem());
    }

    private void removeTopLevelCommentLoadMoreItem(){

        int lastItem = items.size() - 1;
        if(items.get(lastItem).getType() == DetailItem.COMMENT_TOP_LEVEL_LOAD_MORE_ITEM){

            items.remove(lastItem);
        }
    }

    private void removeTopLevelCommentLoadingItem(){

        int lastItem = items.size() - 1;
        if(items.get(lastItem).getType() == DetailItem.COMMENT_TOP_LEVEL_LOADING){

            items.remove(lastItem);
        }
    }

    public void updateView(CommentContainer container){

        removeTopLevelCommentLoadMoreItem();
        addCommentsItems(container);
        addLoadMoreItem(container);

        view.notifyAdapterChange();
    }

    @Override
    public VideoItem getVideoItem() {

        return videoItem;
    }

    @Override
    public Channel getChannel() {

        return channel;
    }

    @Override
    public CommentContainer getCommentContainer() {

        return commentContainer;
    }

    @Override
    public List<DetailItem> getItems() {

        return items;
    }

    @Override
    public DetailRecyclerAdapterListener getDetailRecyclerAdapterListener() {

        return this;
    }

    @Override
    public void onSuccess(CommentContainer container) {

        if(commentContainer.getCommentItems().size() == 0){

            removeTopLevelCommentLoadingItem();
            addCommentHeaderItem();
        }

        if (container.getCommentItems() != null && container.getCommentItems().size() > 0){

            commentContainer.setPageToken(container.getPageToken());
            commentContainer.getCommentItems().addAll(container.getCommentItems());

            updateView(container);

        }else {

            removeTopLevelCommentLoadMoreItem();
            addNoCommentsFound();

            commentContainer.setPageToken(null);

            view.notifyAdapterChange();
        }

    }

    @Override
    public void onLoadMoreCommentsClick() {

        TopLevelCommentAsync async = new TopLevelCommentAsync(view.getContext(), this);
        async.execute(commentContainer);
    }
}
