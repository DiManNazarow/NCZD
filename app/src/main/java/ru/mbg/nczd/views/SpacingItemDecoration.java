package ru.mbg.nczd.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Дмитрий on 20.01.2018.
 */

public class SpacingItemDecoration extends RecyclerView.ItemDecoration {

    private final int VERTICAL_SPACE = 18;

    private final int verticalSpaceHeight;

    public SpacingItemDecoration(int verticalSpaceHeight) {
        this.verticalSpaceHeight = verticalSpaceHeight;
    }

    public SpacingItemDecoration() {
        this.verticalSpaceHeight = VERTICAL_SPACE;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = verticalSpaceHeight;
        }
    }

}
