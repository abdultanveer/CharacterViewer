package com.xfinity.characterviewer.ui.characterdetail;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xfinity.characterviewer.R;

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
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String url = getIntent().getStringExtra("url");
        detailTitleTv.setText(title);
        if (savedInstanceState == null) {
            fragmentItemDetail = CharacterDetailFragment.newInstance(title, content, url,this);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.flDetailContainer, fragmentItemDetail).commit();
        }
    }
}
