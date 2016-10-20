package leezm.closers.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-10-20.
 */

public class ClosersInitBean extends BmobObject{
    private String splashImgUrl;
    private String version;
    private String upDateTime;

    @Override
    public String toString() {
        return "ClosersInitBean{" +
                "splashImgUrl='" + splashImgUrl + '\'' +
                ", version='" + version + '\'' +
                ", upDateTime='" + upDateTime + '\'' +
                '}';
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
