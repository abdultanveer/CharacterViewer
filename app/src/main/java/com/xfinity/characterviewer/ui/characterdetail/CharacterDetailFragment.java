package com.xfinity.characterviewer.ui.characterdetail;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xfinity.characterviewer.R;

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
        args.putString("title", title);
        args.putString("content", content);
        args.putString("url", url);
        args.putString("animation", imageTransitionName);
        fragmentDetail.setArguments(args);
        return fragmentDetail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        this.imageUrl = getArguments().getString("url");
        this.characterDetail = getArguments().getString("content");
        this.characterTitle = getArguments().getString("title");
        this.imageTransitionName = getArguments().getString("animation");
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            String imageTransition = this.imageTransitionName;
            ivImage.setTransitionName(imageTransitionName);
        }

        Picasso.get()
                .load(String.valueOf(this.imageUrl.isEmpty() ? R.drawable.nophoto : this.imageUrl))
                .placeholder(R.drawable.nophoto)
                .noFade()
                .into(ivImage, new Callback() {
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
