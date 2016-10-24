package leezm.closers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import leezm.closers.Base.BaseActivity;
import leezm.closers.Bean.ClosersInitBean;
import leezm.closers.Bean.MainListBean;
import leezm.closers.Config.DevelopersConfig;
import leezm.closers.Config.LeeADConfig;
import leezm.closers.Config.LeeBmobConfig;
import leezm.closers.Config.LeeSharedPreferences;
import leezm.closers.Utils.AlertDialogUtils;
import leezm.closers.Utils.LeeComUtils;
import leezm.closers.Utils.LogUtils;

@ContentView(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @ViewInject(R.id.splash_img_view)
    private ImageView imageView;
    @ViewInject(R.id.splash_text)
    private TextView textView;
    @ViewInject(R.id.splash_progressBar)
    private ProgressBar mprogressBar;

    private Activity mContext;
    private  MyApplication application;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    //判断能否跳转到主页（强更）
    private boolean canJump = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = this;
        //实例化主页全局参数
        application = (MyApplication) getApplicationContext();
        //init Bmob
        Bmob.initialize(mContext, LeeBmobConfig.BMOB_APP_ID);
        init();
        initMainData();
        if (DevelopersConfig.ISDEBUG){
            Toast.makeText(mContext,R.string.lee_debug,Toast.LENGTH_SHORT).show();
        }
    }

    //获取主页的数据
    private void initMainData(){
        textView.setText(getString(R.string.lee_get_ol_main));
        //获取在线数据
        BmobQuery<MainListBean> query = new BmobQuery<>();
        query.order("id");
        query.findObjects(new FindListener<MainListBean>() {
            @Override
            public void done(List<MainListBean> list, BmobException e) {
                if (e==null){
                    application.setMainListBeen(list);
                    //加载主页数据成功
                    textView.setText(getString(R.string.lee_get_ol_main_suc));
                    //延时1秒后跳转
                    LogUtils.e("canJump:"+canJump);
                    if (canJump){
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(mContext,MainActivity.class));
                                mContext.finish();
                            }
                        },1000);
                    }else {
                        //显示强更dialog
                        LogUtils.e("隐藏控件");
                        textView.setVisibility(View.GONE);
                        mprogressBar.setVisibility(View.GONE);
                        showDialog();
                    }
                }else {
                    //加载主页数据失败
                    textView.setText(getString(R.string.lee_get_ol_main_fail)+":"+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //获取在线参数
    private void init(){

        preferences = this.getSharedPreferences(LeeSharedPreferences.SP_BMOB_INIT_TIME,Context.MODE_PRIVATE);
        editor = preferences.edit();
        String date = preferences.getString(LeeSharedPreferences.DATA_DAY,"0");
        String splashUrl = preferences.getString(LeeSharedPreferences.SPLASH_URL,"assets://splash.jpg");
        canJump = !preferences.getBoolean(LeeSharedPreferences.SHOW_UP_DATE,false);
        setADConfig(preferences.getBoolean(LeeSharedPreferences.AD_CONTROL,false));

        //获取本地上次得到的闪屏图链接加载
        x.image().bind(imageView,splashUrl);

        //每天只进行一次获取在线参数
        if (DevelopersConfig.ISDEBUG){
            getOnlineConfig();
        }else if (!(new Date().getDate()+"").equals(date)){
            textView.setText(getString(R.string.lee_get_ol_cf));
            getOnlineConfig();
        }
        LogUtils.e("日:"+new Date().getDate());
    }

    //加载在线参数
    private  void getOnlineConfig(){
        BmobQuery<ClosersInitBean> query = new BmobQuery<>();
        query.findObjects(new FindListener<ClosersInitBean>() {
            @Override
            public void done(List<ClosersInitBean> list, BmobException e) {
                if (e==null){
                    //加载在线参数成功
                    textView.setText(getString(R.string.lee_get_ol_cf_suc));
                    LogUtils.e(list.get(0).toString());
                    editor.putString(LeeSharedPreferences.DATA_DAY,new Date().getDate()+"");
                    editor.putString(LeeSharedPreferences.SPLASH_URL,list.get(0).getSplashImgUrl());
                    if (!LeeComUtils.getVersionName(mContext).equals(list.get(0).getVersion())){
                        editor.putBoolean(LeeSharedPreferences.SHOW_UP_DATE,true);
                    }else {
                        editor.putBoolean(LeeSharedPreferences.SHOW_UP_DATE,false);
                    }
                    editor.putBoolean(LeeSharedPreferences.AD_CONTROL,list.get(0).getAdControl());
                    editor.commit();
                    setADConfig(list.get(0).getAdControl());
                }else {
                    //加载在线参数失败
                    textView.setText(getString(R.string.lee_get_ol_cf_fail)+":"+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //显示强更dialog
    private  void showDialog(){
        BmobQuery<ClosersInitBean> query = new BmobQuery<>();
        query.findObjects(new FindListener<ClosersInitBean>() {
            @Override
            public void done(final List<ClosersInitBean> list, BmobException e) {
                if (e==null){
                    LogUtils.e("显示更新dialog,本地版本号："+LeeComUtils.getVersionName(mContext)+"，在线版本号："+list.get(0).getVersion());
                    AlertDialogUtils.showDialog(mContext, getString(R.string.lee_found_update), list.get(0).getUpDateInfo(),getString(R.string.lee_sure_update),getString(R.string.lee_sure_cancel), new AlertDialogUtils.OnClickYesListener() {
                        @Override
                        public void onClickYes() {
                            LogUtils.e("onClickYes");
                            //这里调用BMOB下载新版本
                            goDownLoad(list.get(0).getFile());
                        }
                    }, new AlertDialogUtils.OnClickNoListener() {
                        @Override
                        public void onClickNo() {
                            LogUtils.e("onClickNo");
                            startActivity(new Intent(mContext,MainActivity.class));
                            mContext.finish();
                        }
                    });
                }
            }
        });

    }

    //调用BMOB下载更新
    private void goDownLoad(BmobFile bmobFile){
                    final File saveFile = new File(Environment.getExternalStorageDirectory(), bmobFile.getFilename());
                    bmobFile.download(new DownloadFileListener() {
                        @Override
                        public void done(String s, BmobException e) {
                            LogUtils.e(s);
                            editor.putBoolean(LeeSharedPreferences.SHOW_UP_DATE,false);
                            editor.commit();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setDataAndType(Uri.fromFile(saveFile), "application/vnd.android.package-archive");
                            startActivity(intent);
                        }

                        @Override
                        public void onProgress(Integer integer, long l) {
                        LogUtils.e("下载进度："+integer+","+l);
                            textView.setVisibility(View.VISIBLE);
                            textView.setText(getString(R.string.lee_downloading)+integer+"%");
                        }
                    });
    }

    //设置广告开关
    private  void setADConfig(boolean adConfig){
        if (adConfig){
            LeeADConfig.AD_SWITCH = true;
        }else {
            LeeADConfig.AD_SWITCH = false;
        }
    }

}
