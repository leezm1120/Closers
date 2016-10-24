package leezm.closers.Bean;

import java.io.File;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016-10-20.
 */

public class ClosersInitBean extends BmobObject{
    private String splashImgUrl;
    private String version;
    private String upDateTime;
    private  Boolean adControl;
    private BmobFile file;
    private String upDateInfo;

    @Override
    public String toString() {
        return "ClosersInitBean{" +
                "splashImgUrl='" + splashImgUrl + '\'' +
                ", version='" + version + '\'' +
                ", upDateTime='" + upDateTime + '\'' +
                ", adControl=" + adControl +
                ", file=" + file +
                ", upDateInfo='" + upDateInfo + '\'' +
                '}';
    }

    public String getUpDateInfo() {
        return upDateInfo;
    }

    public void setUpDateInfo(String upDateInfo) {
        this.upDateInfo = upDateInfo;
    }

    public BmobFile getFile() {
        return file;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }

    public Boolean getAdControl() {
        return adControl;
    }

    public void setAdControl(Boolean adControl) {
        this.adControl = adControl;
    }



    public String getSplashImgUrl() {
        return splashImgUrl;
    }

    public void setSplashImgUrl(String splashImgUrl) {
        this.splashImgUrl = splashImgUrl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(String upDateTime) {
        this.upDateTime = upDateTime;
    }
}
