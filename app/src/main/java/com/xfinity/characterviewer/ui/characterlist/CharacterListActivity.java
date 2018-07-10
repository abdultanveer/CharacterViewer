package com.xfinity.characterviewer.ui.characterlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    Toolbar toolbar;
    TextView appName;
    ToggleButton mToggleButton;
    private boolean isTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appName = findViewById(R.id.appName);
        appName.setText(BuildConfig.app_name);
        determinePaneLayout();

        Log.i(TAG, "Check Pane");
    }

    /**
     * This method determines if a device is tablet or phone by checking the returned FrameLayout instance.
     * For CharacterListActivity, it does not have an instance of FrameLayout with flDetailContainer as ID here;
     * For the tablet using the large activity_character_list, it has an instance of FrameLayout with flDetailContainer as ID here.
     */
    private void determinePaneLayout() {
        FrameLayout fragmentItemDetail = findViewById(R.id.flDetailContainer);
        if (fragmentItemDetail != null) {
            isTwoPane = true;
            CharacterListFragment fragmentItemsList = (CharacterListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemList);
        } else {
            mToggleButton = findViewById(R.id.toggle);
            mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        EventBus.getDefault().post(true);
                    } else {
                        EventBus.getDefault().post(false);
                    }
                }
            });
            CharacterListFragment listFragment = new CharacterListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, listFragment).commit();
        }
    }

    @Override
    public void onItemSelected(Object item) {
        List<String> details = CharacterAdapter.findTitleDes(((ShowCharacter) item).getText());
        String topicTitle = details.get(0);
        String topicContent = details.get(1);
        String url = ((ShowCharacter) item).getIcon().getURL();
        if (isTwoPane) {
            CharacterDetailFragment fragmentItem = CharacterDetailFragment.newInstance(topicTitle, topicContent, url, CharacterListActivity.this);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItem).commit();
        } else {
            Intent i = new Intent(this, CharacterDetailActivity.class);
            i.putExtra("title", topicTitle);
            i.putExtra("content", topicContent);
            i.putExtra("url", url);
            startActivity(i);
        }
    }
}
