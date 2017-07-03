package com.balazsorban.moviemate.Fragments.Discover;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.balazsorban.moviemate.Fragments.Discover.DiscoverContent.DiscoverItem;
import com.balazsorban.moviemate.R;

import org.json.JSONException;

public class DiscoverFragment extends Fragment{

    private OnListFragmentInteractionListener mListener;
    private DiscoverAdapter mAdapter;
    private RecyclerView mDiscoverList;

    public DiscoverFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mDiscoverList = (RecyclerView) getView().findViewById(R.id.discover_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        mDiscoverList.setLayoutManager(gridLayoutManager);
        mDiscoverList.setHasFixedSize(true);

        try {
            String API_KEY = getString(R.string.themoviedb_api_key);
            mAdapter = new DiscoverAdapter(new DiscoverContent(API_KEY).getItems(), mListener);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mDiscoverList.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DiscoverItem item);
    }
}
