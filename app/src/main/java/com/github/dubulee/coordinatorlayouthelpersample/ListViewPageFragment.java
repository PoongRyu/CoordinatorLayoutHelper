package com.github.dubulee.coordinatorlayouthelpersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.dubulee.coordinatorlayouthelper.CoordinatorLayoutHelperRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListViewPageFragment extends Fragment {
    private static final int ITEM_COUNT = 33;

    private CoordinatorLayoutHelperRecyclerView mListView;
    private List<String> mItemList;

    public static ListViewPageFragment createInstance() {
        final ListViewPageFragment pageFragment = new ListViewPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = (CoordinatorLayoutHelperRecyclerView) inflater.inflate(R.layout.listview_page_fragment, container, false);
        setup();
        return mListView;
    }

    private void setup() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            itemList.add("Item " + i);
        }
        mItemList = itemList;
        final ListRecyclerViewAdapter adapter = new ListRecyclerViewAdapter();
        mListView.setAdapter(adapter);
        final LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerItemViewHolder> {
        public ListRecyclerViewAdapter() {
        }

        @Override
        public ListRecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            final View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ListRecyclerItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListRecyclerItemViewHolder viewHolder, int position) {
            final TextView titleView = (TextView) viewHolder.itemView.findViewById(R.id.item_title);
            if (titleView != null) {
                titleView.setText(String.valueOf(position));
            }
        }

        @Override
        public int getItemCount() {
            return mItemList == null ? 0 : mItemList.size();
        }
    }

    public static class ListRecyclerItemViewHolder extends RecyclerView.ViewHolder {
        public ListRecyclerItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
