package leezm.closers.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import org.xutils.view.annotation.ViewInject;

import leezm.closers.BaseActivity;
import leezm.closers.R;

public class FashionActivity extends BaseActivity {

    @ViewInject(R.id.fashion_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion);
    }
}
