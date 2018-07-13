package com.xfinity.characterviewer.ui.characterdetail;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xfinity.characterviewer.R;
import com.xfinity.characterviewer.ui.characterlist.CharacterListActivity;

/**
 * CharacterDetailFragment is the fragment contains character Details
 */
public class CharacterDetailFragment extends Fragment {
    private String imageUrl;
    private String characterTitle;
    private String characterDetail;
    private Context mContext;
    private String imageTransitionName;

    /**
     * @param title   title of Character
     * @param content detail of Character
     * @param url     Image URL of character
     * @param context activity context
     * @return an instance of CharacterDetailFragment
     */
    public static CharacterDetailFragment newInstance(String title, String content, String url, String imageTransitionName, Context context) {
        CharacterDetailFragment fragmentDetail = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putString(CharacterListActivity.TITLE, title);
        args.putString(CharacterListActivity.CONTENT, content);
        args.putString(CharacterListActivity.URL, url);
        args.putString(CharacterListActivity.ANIMATION, imageTransitionName);
        fragmentDetail.setArguments(args);
        return fragmentDetail;
    }
    public static CharacterDetailFragment newInstance(String title, String content, String url, Context context) {
        CharacterDetailFragment fragmentDetail = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putString(CharacterListActivity.TITLE, title);
        args.putString(CharacterListActivity.CONTENT, content);
        args.putString(CharacterListActivity.URL, url);
        fragmentDetail.setArguments(args);
        return fragmentDetail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        this.imageUrl = getArguments().getString(CharacterListActivity.URL);
        this.characterDetail = getArguments().getString(CharacterListActivity.CONTENT);
        this.characterTitle = getArguments().getString(CharacterListActivity.TITLE);
        this.imageTransitionName = getArguments().getString(CharacterListActivity.ANIMATION, null);
    }

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_detail,
                container, false);
        TextView tvTitle = view.findViewById(R.id.itemTitle);
        ImageView ivImage = view.findViewById(R.id.imageIcon);
        TextView tvDes = view.findViewById(R.id.itemDescription);
        tvTitle.setText(this.characterTitle);
        tvDes.setText(this.characterDetail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && this.imageTransitionName!=null) {
            String imageTransitionName = this.imageTransitionName;
            ivImage.setTransitionName(imageTransitionName);
        }
        Picasso.get()
                .load(String.valueOf(this.imageUrl.isEmpty() ? R.drawable.nophoto : this.imageUrl))
                .placeholder(R.drawable.nophoto)
                .noFade()
                .into(ivImage, this.imageTransitionName==null? null: new Callback() {
                    @Override
                    public void onSuccess() {
                        ((AppCompatActivity) mContext).supportStartPostponedEnterTransition();
                    }
                    @Override
                    public void onError(Exception e) {
                        ((AppCompatActivity) mContext).supportStartPostponedEnterTransition();
                    }
                });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

}
