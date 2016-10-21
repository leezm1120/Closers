package leezm.closers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;
import java.util.List;
import leezm.closers.Activity.FashionActivity;
import leezm.closers.Activity.ImageActivity;
import leezm.closers.Activity.MeiZhiActivity;
import leezm.closers.Adapter.MainRecyclerViewAdapter;
import leezm.closers.Base.BaseActivity;
import leezm.closers.Bean.MainListBean;
import leezm.closers.Config.LeeMainListFuction;

public class MainActivity extends BaseActivity {
    private long exitTime = 0;
    private Activity mContext=null;

    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter adapter;

    private  int inta = 0;
    private  String initErrorMsg="";
    private  MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        //实例化主页全局参数
        application = (MyApplication) getApplicationContext();
        setmAdapter(application.getMainListBeen());
    }


    private void setmAdapter(List<MainListBean> qlist){
        //设置recyclerview参数
        adapter = new MainRecyclerViewAdapter(this,qlist);
        adapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                toFunction(position);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    //跳转到功能页
    private void toFunction(int position){
        String fuction = application.getMainListBeen().get(position).getFunctionName();
        if (LeeMainListFuction.PICTURE.equals(fuction)){
            startActivity(new Intent(mContext,ImageActivity.class));
            return;
        }
        if (LeeMainListFuction.FASHION.equals(fuction)){
            startActivity(new Intent(mContext,FashionActivity.class));
            return;
        }
        if (LeeMainListFuction.MEIZHI.equals(fuction)){
            startActivity(new Intent(mContext,MeiZhiActivity.class));
            return;
        }
        else {
            Toast.makeText(mContext,getString(R.string.lee_no_main_tool),Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(), getString(R.string.lee_ext_tip), Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
