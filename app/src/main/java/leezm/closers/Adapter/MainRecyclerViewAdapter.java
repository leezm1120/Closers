package leezm.closers.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import leezm.closers.Bean.MainListBean;
import leezm.closers.R;


/**
 * Created by Administrator on 2016-9-14.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter{

    private  List<MainListBean> list;
    private Activity context;
    private ImageOptions imageOptions;

    public MainRecyclerViewAdapter(Activity context, List<MainListBean> list){
        this.list = list;
        this.context = context;
        imageOptions= new ImageOptions.Builder()
                .setRadius(DensityUtil.dip2px(5))
                .setImageScaleType(ImageView.ScaleType.CENTER).build();
    }


    class MainHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;

        public MainHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.main_imageView);
            this.textView = (TextView) itemView.findViewById(R.id.main_textView);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(context).inflate(R.layout.main_recycler_view,null);
        RecyclerView.ViewHolder mholer = new MainHolder(mView);
        return mholer;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final MainHolder mainHolder = (MainHolder) holder;
        mainHolder.textView.setText(list.get(position).getItem());
        x.image().bind(mainHolder.imageView,list.get(position).getImgUrl(),imageOptions);
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = mainHolder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(mainHolder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = mainHolder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(mainHolder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //点击事件
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

}
