package leezm.closers.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-10-24.
 */

public class EquipmentBean extends BmobObject {
    private int level;
    private String imgUrl;
    private String title;
    private String type;
    private int wlAtt;
    private int mfAtt;
    private int attSpd;
    private String whiteInfo;
    private String midInfo;
    private String yellowInfo;
    private String setInfo;

    @Override
    public String toString() {
        return "EquipmentBean{" +
                "level=" + level +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", wlAtt=" + wlAtt +
                ", mfAtt=" + mfAtt +
                ", attSpd=" + attSpd +
                ", whiteInfo='" + whiteInfo + '\'' +
                ", midInfo='" + midInfo + '\'' +
                ", yellowInfo='" + yellowInfo + '\'' +
                ", setInfo='" + setInfo + '\'' +
                '}';
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWlAtt() {
        return wlAtt;
    }

    public void setWlAtt(int wlAtt) {
        this.wlAtt = wlAtt;
    }

    public int getMfAtt() {
        return mfAtt;
    }

    public void setMfAtt(int mfAtt) {
        this.mfAtt = mfAtt;
    }

    public int getAttSpd() {
        return attSpd;
    }

    public void setAttSpd(int attSpd) {
        this.attSpd = attSpd;
    }

    public String getWhiteInfo() {
        return whiteInfo;
    }

    public void setWhiteInfo(String whiteInfo) {
        this.whiteInfo = whiteInfo;
    }

    public String getMidInfo() {
        return midInfo;
    }

    public void setMidInfo(String midInfo) {
        this.midInfo = midInfo;
    }

    public String getYellowInfo() {
        return yellowInfo;
    }

    public void setYellowInfo(String yellowInfo) {
        this.yellowInfo = yellowInfo;
    }

    public String getSetInfo() {
        return setInfo;
    }

    public void setSetInfo(String setInfo) {
        this.setInfo = setInfo;
    }
}
