package com.xfinity.characterviewer.ui.characterlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.xfinity.characterviewer.BuildConfig;
import com.xfinity.characterviewer.R;
import com.xfinity.characterviewer.model.ShowCharacter;
import com.xfinity.characterviewer.ui.CharacterAdapter;
import com.xfinity.characterviewer.ui.characterdetail.CharacterDetailActivity;
import com.xfinity.characterviewer.ui.characterdetail.CharacterDetailFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * CharacterListActivity is the Activity shows A list of Characters in either grid or list views
 */
public class CharacterListActivity extends AppCompatActivity implements CharacterListFragment.OnItemSelectedListener{
    public static final String TAG = CharacterListActivity.class.getSimpleName();
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String URL = "url";
    public static final String ANIMATION = "animation";
    public static final String TOGGLE_STATE = "toggle_state";
    Toolbar toolbar;
    TextView appName;
    ToggleButton mToggleButton;
    private boolean isGrid=false;
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_character_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appName = findViewById(R.id.appName);
        appName.setText(BuildConfig.app_name);
        mToggleButton = findViewById(R.id.toggle);

        if(!isTwoPane && bundle !=null && mToggleButton!=null) {
            isGrid = bundle.getBoolean(TOGGLE_STATE);
            mToggleButton.setChecked(isGrid);
        }
        determinePaneLayout();
        Log.i(TAG, "Check Pane");
    }

    /**
     * This method determines if a device is tablet or phone by checking the returned FrameLayout instance.
     * For CharacterListActivity, it does not have an instance of FrameLayout with flDetailContainer as ID here;
     * For the tablet using the large activity_character_list, it has an instance of FrameLayout with flDetailContainer as ID here.
     */
    private void determinePaneLayout()
    {
        FrameLayout fragmentItemDetail = findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
        } else {
            mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        EventBus.getDefault().post(true);
                        isGrid = true;
                    } else {
                        EventBus.getDefault().post(false);
                        isGrid = false;
                    }
                }
            });
            CharacterListFragment listFragment = new CharacterListFragment();
            Bundle b = new Bundle();
            b.putBoolean(TOGGLE_STATE, isGrid);
            listFragment.setArguments(b);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, listFragment).commit();
        }
    }

    /**
     * This is the implemented method from interface OnItemSelectedListener in CharacterListFragment.java
     * @param item the item passed from recyclerViewListClicked(View v, int position); item is the datasource at
     *             position
     */
    @Override
    public void onItemSelected(ShowCharacter item, View v) {
        List<String> details = CharacterAdapter.findTitleDes(item.getText());
        String topicTitle = details.get(0);
        String topicContent = details.get(1);
        String url =  item.getIcon().getURL();
        if (isTwoPane) {
            CharacterDetailFragment fragmentItem = CharacterDetailFragment.newInstance(topicTitle, topicContent, url,CharacterListActivity.this);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItem).commit();
        } else {
            Intent i = new Intent(this, CharacterDetailActivity.class);
            i.putExtra(TITLE, topicTitle);
            i.putExtra(CONTENT, topicContent);
            i.putExtra(URL, url);
            i.putExtra(ANIMATION, ViewCompat.getTransitionName(v));
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, ViewCompat.getTransitionName(v));
            startActivity(i, optionsCompat.toBundle());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putBoolean(TOGGLE_STATE, isGrid);
    }
}
