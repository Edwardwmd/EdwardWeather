package com.edwardwmd.weather.weight.citypickview.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecoration extends RecyclerView.ItemDecoration{
    private int mSpanCount;
    private int mSpace;

    public GridItemDecoration(int spanCount, int space) {
        this.mSpanCount = spanCount;
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % mSpanCount;

        outRect.left = column * mSpace / mSpanCount;
        outRect.right = mSpace - (column + 1) * mSpace / mSpanCount;
        if (position >= mSpanCount) {
            outRect.top = mSpace;
        }
    }
}
