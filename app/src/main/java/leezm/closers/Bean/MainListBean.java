package leezm.closers.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016-9-14.
 */
public class MainListBean extends BmobObject {
    private  int id;
    private String item;
    private String imgUrl;
    private String functionName;

    public String getItem() {
        return item;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MainListBean{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", functionName='" + functionName + '\'' +
                '}';
    }
}
