package com.xfinity.characterviewer.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
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
import com.xfinity.characterviewer.di.qualifier.ActivityContext;
import com.xfinity.characterviewer.di.scope.PerFragment;
import com.xfinity.characterviewer.model.ShowCharacter;
import com.xfinity.characterviewer.util.CharacterAnalyzer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * CharacterAdapter is the generic adapter for both list view and grid view.
 */
@PerFragment
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {
    private final static int GRID_VIEW = 0;
    private final static int LIST_VIEW = 1;

    private int layoutRes = 0;
    /**
     *
     * @param dataSource ShowCharacter DataSource from server
     */
    public void setDataSource(List<ShowCharacter> dataSource) {
        this.dataSource = dataSource;
    }

    public void setListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }


    private List<ShowCharacter> dataSource;
    private RecyclerViewClickListener listener;
    private Context mContext;

    @Inject
    public CharacterAdapter(@ActivityContext Context context){
        mContext = context;
    }

    public CharacterAdapter(List<ShowCharacter> dataSource, RecyclerViewClickListener listener, Context mContext) {
        this.dataSource = dataSource;
        this.listener = listener;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CharacterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case GRID_VIEW:
                layoutRes = R.layout.grid_character_layout;
                break;
            case LIST_VIEW:
                layoutRes = R.layout.list_character_layout;
                break;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return dataSource.get(position).isUseGrid() ? GRID_VIEW:LIST_VIEW;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.MyViewHolder holder, int position) {
        if(holder.titleIv!=null){
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.nophoto)
                    .error(R.drawable.nophoto)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH);
            Glide.with(mContext)
                    .load(dataSource.get(position).getIcon().getURL())
                    .apply(options)
                    .into(holder.titleIv);
           ViewCompat.setTransitionName(holder.titleIv, String.valueOf(dataSource.get(position).getText().hashCode()));
        }else if(holder.titleTv!=null){
            List<String> tx;
            ShowCharacter dataBean = dataSource.get(position);
            tx = CharacterAnalyzer.findTitleDes(dataBean.getText());
            holder.titleTv.setText(tx.get(0));
            ViewCompat.setTransitionName(holder.titleTv, String.valueOf(dataSource.get(position).getText().hashCode()));

        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface RecyclerViewClickListener {
       void onCharacterItemClicked(ShowCharacter characterItem, View v);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView titleTv;
        ImageView titleIv;
        public MyViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title);
            titleIv = itemView.findViewById(R.id.characterImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(layoutRes==R.layout.grid_character_layout)
                        listener.onCharacterItemClicked(dataSource.get(getLayoutPosition()), titleIv);
                    else
                        listener.onCharacterItemClicked(dataSource.get(getLayoutPosition()), titleTv);
                }
            });
        }
    }

}
