package com.balazsorban.moviemate.Fragments.Discover;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balazsorban.moviemate.Fragments.Discover.DiscoverContent.DiscoverItem;
import com.balazsorban.moviemate.NetworkUtils.DownloadImageTask;
import com.balazsorban.moviemate.R;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {

    private final List<DiscoverItem> mValues;
    private final DiscoverFragment.OnListFragmentInteractionListener mListener;

    public DiscoverAdapter(List<DiscoverItem> items, DiscoverFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_discover_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).title);
        holder.mVoteAverage.setText((mValues.get(position).vote_average));
        new DownloadImageTask(holder.mPosterView)
                .execute(mValues.get(position).poster_path);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitleView;
        public final TextView mVoteAverage;
        public final ImageView mPosterView;
        public DiscoverItem mItem;

        public ViewHolder(View view) {
            super(view);
            mTitleView = (TextView) view.findViewById(R.id.itemTitle);
            mVoteAverage = (TextView) view.findViewById(R.id.itemRate);
            mPosterView = (ImageView) view.findViewById(R.id.itemPoster);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            DiscoverItem item = mItem;
            mListener.onListFragmentInteraction(item);
        }
    }
}
