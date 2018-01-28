package ru.mbg.nczd.reception;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.BindView;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;

public class ReceptionActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.apply_button)
    protected Button mApplyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_reception);
        super.onCreate(savedInstanceState);
    }
}
