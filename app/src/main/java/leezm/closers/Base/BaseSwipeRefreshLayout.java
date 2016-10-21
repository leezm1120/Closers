package leezm.closers.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import leezm.closers.R;
import leezm.closers.Utils.LogUtils;

/**
 * Created by Administrator on 2016-10-21.
 */

public class BaseSwipeRefreshLayout extends AppCompatActivity{
    @ViewInject(R.id.swipe_rf_recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.swipe_rf_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private boolean mIsRequestDataRefresh = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                R.color.refresh_progress_2, R.color.refresh_progress_1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.e("下拉");
            }
        });



    }
}
