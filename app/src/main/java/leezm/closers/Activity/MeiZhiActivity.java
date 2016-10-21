package leezm.closers.Activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;


import com.google.gson.Gson;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


import leezm.closers.Adapter.MeiZhiRecyclerViewAdapter;
import leezm.closers.Base.BaseActivity;
import leezm.closers.Bean.GankBean;

import leezm.closers.R;
import leezm.closers.Utils.GankApi;
import leezm.closers.Utils.LogUtils;
import leezm.closers.Widget.OnRecyclerViewScrollListener;

@ContentView(R.layout.activity_mei_zhi)
public class MeiZhiActivity extends BaseActivity {
    @ViewInject(R.id.swipe_rf_recyclerview)
    RecyclerView recyclerView;
    @ViewInject(R.id.swipe_rf_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private Activity mContext;
    private MeiZhiRecyclerViewAdapter adapter;
   private GankBean bean;
//    private int lastVisibleItem;
//  private   StaggeredGridLayoutManager staggeredGridLayoutManager;

    //首次加载第一页
    private  int page = 1;
        private List<GankBean.ResultsBean> mlist = new ArrayList<GankBean.ResultsBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        init();
        getData(1);
    }

    private  void init (){
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress_3,
                R.color.refresh_progress_2, R.color.refresh_progress_1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉监听刷新数据
                getData(1);
            }
        });
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
    }

    private  void getData(final int mpage){
        if (swipeRefreshLayout!=null){
            swipeRefreshLayout.setRefreshing(true);
        }
        RequestParams params = new RequestParams(GankApi.getMeiZhi(mpage));
        LogUtils.e(GankApi.getMeiZhi(mpage));
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                bean = new Gson().fromJson(result,GankBean.class);
                LogUtils.e(bean.isError()+"");
                LogUtils.e(bean.getResults().get(0).getUrl());
                if (mpage==1){
                    mlist = bean.getResults();
                    setmAdapter(mlist);
                }else {
                    mlist.addAll(bean.getResults());
                    adapter.notifyDataSetChanged();
                }


                // 防止刷新消失太快，让子弹飞一会儿.
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(mContext,ex.toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void setmAdapter(List<GankBean.ResultsBean> qlist){
        //设置recyclerview参数
        adapter = new MeiZhiRecyclerViewAdapter(mContext,qlist);
        adapter.setOnItemClickListener(new MeiZhiRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnRecyclerViewScrollListener(){
            @Override
            public void onBottom() {
                super.onBottom();
                LogUtils.e("到底部");
                page = page + 1;
                getData(page);
            }
        });
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space=space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left=space/2;
            outRect.right=space/2;
            outRect.bottom=space/2;
//            if(parent.getChildAdapterPosition(view)==0){
                outRect.top=space/2;
//            }
        }
    }

}
