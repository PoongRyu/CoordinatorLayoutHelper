package com.github.dubulee.coordinatorlayouthelpersample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.dubulee.coordinatorlayouthelper.CoordinatorLayoutHelperRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GridViewPageFragment extends Fragment {
    private static final int ITEM_COUNT = 33;

    private CoordinatorLayoutHelperRecyclerView mGridView;
    private List<String> mItemList;

    public static GridViewPageFragment createInstance() {
        final GridViewPageFragment pageFragment = new GridViewPageFragment();
        final Bundle bundle = new Bundle();
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGridView = (CoordinatorLayoutHelperRecyclerView) inflater.inflate(R.layout.gridview_page_fragment, container, false);
        setup();
        return mGridView;
    }

    private void setup() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            itemList.add("Item " + i);
        }
        mItemList = itemList;

        final GridRecyclerViewAdapter adapter = new GridRecyclerViewAdapter();
        mGridView.setAdapter(adapter);

        mGridView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerItemViewHolder> {
        private static final int VIEW_TYPE_1 = 0;
        private static final int VIEW_TYPE_2 = 1;
        private static final int VIEW_TYPE_3 = 2;

        public GridRecyclerViewAdapter() {
        }

        @Override
        public GridRecyclerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            final int layoutRes;
            switch (viewType) {
                case VIEW_TYPE_2:
                    layoutRes = R.layout.grid_item_2;
                    break;
                case VIEW_TYPE_3:
                    layoutRes = R.layout.grid_item_3;
                    break;
                default:
                    layoutRes = R.layout.grid_item_1;
                    break;
            }

            final View view = inflater.inflate(layoutRes, parent, false);

            return new GridRecyclerItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GridRecyclerItemViewHolder viewHolder, int position) {
            final TextView titleView = (TextView) viewHolder.itemView.findViewById(R.id.item_title);
            if (titleView != null) {
                titleView.setText(String.valueOf(position));
            }
        }

        @Override
        public int getItemCount() {
            return mItemList == null ? 0 : mItemList.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position % 3;
        }
    }

    public static class GridRecyclerItemViewHolder extends RecyclerView.ViewHolder {
        public GridRecyclerItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
