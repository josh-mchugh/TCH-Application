package com.redrumming.thecreaturehub.player.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.channel.ChannelItem;
import com.redrumming.thecreaturehub.contentItems.video.VideoItem;
import com.redrumming.thecreaturehub.player.channel.ChannelSectionItem;
import com.redrumming.thecreaturehub.player.comments.CommentAsyncListener;
import com.redrumming.thecreaturehub.player.comments.CommentContainer;
import com.redrumming.thecreaturehub.player.comments.CommentItemType;
import com.redrumming.thecreaturehub.player.comments.CommentsHeaderItem;
import com.redrumming.thecreaturehub.player.comments.NoCommentsItem;
import com.redrumming.thecreaturehub.player.comments.topComments.TopLevelCommentItem;
import com.redrumming.thecreaturehub.player.comments.topComments.TopLevelCommentAsync;
import com.redrumming.thecreaturehub.player.comments.topComments.TopLevelCommentLoadMoreItem;
import com.redrumming.thecreaturehub.player.comments.topComments.TopLevelCommentLoadingItem;
import com.redrumming.thecreaturehub.player.description.DescriptionItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ME on 12/5/2015.
 */
public class DetailsFragment extends Fragment implements CommentAsyncListener, DetailRecyclerAdapterListener{

    private RecyclerView recyclerView;
    private List<DetailItem> items;
    private DetailRecyclerAdapter adapter;

    private VideoItem videoItem;
    private ChannelItem channelItem;

    private CommentContainer commentContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

            videoItem = savedInstanceState.getParcelable("video");
            channelItem = savedInstanceState.getParcelable("channel");

            commentContainer = savedInstanceState.getParcelable("comments");
        }

        if(getArguments() != null){

            videoItem = getArguments().getParcelable("video");
            channelItem = getArguments().getParcelable("channel");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, viewGroup, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.details_recycler_view);

        items = new ArrayList<DetailItem>();

        DescriptionItem descriptionItem = new DescriptionItem();
        descriptionItem.setVideoItem(videoItem);
        items.add(descriptionItem);

        ChannelSectionItem channelSectionItem = new ChannelSectionItem();
        channelSectionItem.setChannelItem(channelItem);
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

        adapter = new DetailRecyclerAdapter(items, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(commentContainer.getCommentItems().size() == 0 && commentContainer.getPageToken() != null){

            TopLevelCommentAsync async = new TopLevelCommentAsync(getActivity(), this);
            async.execute(commentContainer);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("video", videoItem);
        outState.putParcelable("channel", channelItem);

        outState.putParcelable("comments", commentContainer);
    }

    public void updateView(CommentContainer container){

        removeTopLevelCommentLoadMoreItem();
        addCommentsItems(container);
        addLoadMoreItem(container);

        adapter.notifyDataSetChanged();
    }

    public void setLoading(boolean isLoading){

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

            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoadMoreCommentsClick() {

        TopLevelCommentAsync async = new TopLevelCommentAsync(getActivity(), this);
        async.execute(commentContainer);
    }
}
