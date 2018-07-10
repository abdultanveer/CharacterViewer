package com.xfinity.characterviewer.ui.characterdetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xfinity.characterviewer.R;

/**
 * CharacterDetailFragment is the fragment contains character Details
 */
public class CharacterDetailFragment extends Fragment {
    private String imageUrl;
    private String characterTitle;
    private String characterDetail;
    private Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        this.imageUrl = getArguments().getString("url");
        this.characterDetail = getArguments().getString("content");
        this.characterTitle = getArguments().getString("title");
    }

    @Override
    public void onAttach(Context mContext) {
        super.onAttach(mContext);
        this.mContext = mContext;
    }

    /**
     *
     * @param title title of Character
     * @param content detail of Character
     * @param url Image URL of character
     * @param context activity context
     * @return an instance of CharacterDetailFragment
     */
    public static CharacterDetailFragment newInstance(String title, String content, String url, Context context){
        CharacterDetailFragment fragmentDetail = new CharacterDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        args.putString("url", url);
        fragmentDetail.setArguments(args);
        return fragmentDetail;
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
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.nophoto)
                .error(R.drawable.nophoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(mContext)
                .load(this.imageUrl)
                .apply(options)
                .into(ivImage);
        return view;
    }
    @Override
    public void onDetach(){
        super.onDetach();
        mContext = null;
    }

}
