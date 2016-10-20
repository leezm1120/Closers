package leezm.closers;

import android.app.Application;

import org.xutils.x;

import java.util.List;

import leezm.closers.Bean.MainListBean;

/**
 * Created by Administrator on 2016-10-20.
 */

public class MyApplication extends Application {

        private List<MainListBean> mainListBeen;

        public List<MainListBean> getMainListBeen() {
            return mainListBeen;
        }

        public void setMainListBeen(List<MainListBean> mainListBeen) {
            this.mainListBeen = mainListBeen;
        }

        @Override
        public String toString() {
            return "MainList{" +
                    "mainListBeen=" + mainListBeen +
                    '}';
        }


    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
