package ru.mbg.nczd.activities.kidz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

import butterknife.BindView;
import ru.mbg.nczd.App;
import ru.mbg.nczd.R;
import ru.mbg.nczd.activities.BaseActivity;
import ru.mbg.nczd.db.models.Child;
import ru.mbg.nczd.utils.Params;

public class ChildInfoActivity extends BaseActivity {

    @BindView(R.id.initials_text_view)
    protected TextView mInitialsTextView;
    @BindView(R.id.omc_text_view)
    protected TextView mOmcTextView;
    @BindView(R.id.date_text_view)
    protected TextView mDateBirthTextView;

    private long childId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_child_info);
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        childId = getIntent().getLongExtra(Params.CHILD_ID_ARG, -1);
        setup();
    }

    @Override
    protected String getToolbarTitle(){
        return getString(R.string.kidz_child_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_child_info, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.action_edit) {
            Intent intent = new Intent(this, AddKidzActivity.class);
            intent.putExtra(Params.CHILD_ID_ARG, childId);
            startActivityForResult(intent, Params.EDIT_CHILD_REQUEST_CODE);
        } else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setup(){
        if (childId != -1){
            Child child = App.getAppDatabase().getChildrenDao().get(childId);
            if (child != null){
                mInitialsTextView.setText(String.format(Locale.getDefault(), "%s %s %s", child.getFirstName(), child.getSecondName(), child.getPatronymic()));
                mOmcTextView.setText(child.getOmc());
                mDateBirthTextView.setText(child.getDateBirth());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == Params.EDIT_CHILD_REQUEST_CODE){
            childId = data.getLongExtra(Params.CHILD_ID_ARG, -1);
            setup();
        }
    }

}
