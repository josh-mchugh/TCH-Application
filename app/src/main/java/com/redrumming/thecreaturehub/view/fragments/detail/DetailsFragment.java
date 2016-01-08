package com.redrumming.thecreaturehub.view.fragments.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.redrumming.thecreaturehub.R;
import com.redrumming.thecreaturehub.view.viewholders.detail.DetailRecyclerAdapter;

/**
 * Created by ME on 12/5/2015.
 */
public class DetailsFragment extends Fragment implements DetailsFragmentView {

    private DetailsFragmentPresenter presenter;

    private RecyclerView recyclerView;
    private DetailRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new DetailsFragmentPresenterImpl(this);
        presenter.onCreate(savedInstanceState, getArguments());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, viewGroup, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.details_recycler_view);

        presenter.onCreateView();

        adapter = new DetailRecyclerAdapter(presenter.getItems(), presenter.getDetailRecyclerAdapterListener());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable("video", presenter.getVideoItem());
        outState.putParcelable("channel", presenter.getChannelItem());

        outState.putParcelable("comments", presenter.getCommentContainer());
    }

    public void setLoading(boolean isLoading){

    }

    @Override
    public void notifyAdapterChange() {

        adapter.notifyDataSetChanged();
    }

    @Override
    public Context getContext() {

        return getActivity();
    }
}
