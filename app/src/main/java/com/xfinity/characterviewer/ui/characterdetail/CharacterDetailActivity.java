package com.xfinity.characterviewer.ui.characterdetail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xfinity.characterviewer.R;
import com.xfinity.characterviewer.ui.characterlist.CharacterListActivity;

/**
 * CharacterDetailActivity is the activity only responsible for
 * showing detail of a chosen character, it contains a Detail Fragment.
 */
public class CharacterDetailActivity extends AppCompatActivity {

    private CharacterDetailFragment fragmentItemDetail;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);
        mToolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(mToolbar);
        TextView detailTitleTv = findViewById(R.id.characterTitle);
        String title = getIntent().getStringExtra(CharacterListActivity.TITLE);
        String content = getIntent().getStringExtra(CharacterListActivity.CONTENT);
        String url = getIntent().getStringExtra(CharacterListActivity.URL);
        String imageTransitionName = getIntent().getStringExtra(CharacterListActivity.ANIMATION);
        detailTitleTv.setText(title);
        if (savedInstanceState == null) {
            fragmentItemDetail = CharacterDetailFragment.newInstance(title, content, url, imageTransitionName,this);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItemDetail).commit();
        }
    }
}
