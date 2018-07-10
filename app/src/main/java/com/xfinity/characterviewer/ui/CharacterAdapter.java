package com.xfinity.characterviewer.ui;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.xfinity.characterviewer.model.ShowCharacter;

import java.util.ArrayList;
import java.util.List;

/**
 * CharacterAdapter is the generic adapter for both list view and gridview.
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {
    private final static int GRID_VIEW = 0;
    private final static int LIST_VIEW = 1;
    private List<ShowCharacter> dataSource;
    private RecyclerViewClickListener listener;
    private Context mContext;
    /**
     *
     * @param dataSource ShowCharacter DataSource from server
     */
    public void setDataSource(List<ShowCharacter> dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param listener Recyclerview click listener
     */
    public void setListener(RecyclerViewClickListener listener) {
        this.listener = listener;
    }

    public void setContext(Context context) {
        mContext = context;

    }

    public CharacterAdapter(){

    }

    public CharacterAdapter(List<ShowCharacter> dataSource, RecyclerViewClickListener listener, Context mContext) {
        this.dataSource = dataSource;
        this.listener = listener;
        this.mContext = mContext;
    }

    /**
     *
     * @param inputTx returned text containing character title and detail, separated with " - "
     * @return a list with title and description contained
     */
    public static List<String> findTitleDes(String inputTx) {
        List<String> titleDes = new ArrayList<>();
        int quoteIdx = inputTx.indexOf(" - ");
        if (quoteIdx < 0) {
            titleDes.add(inputTx);
            titleDes.add(inputTx);
        } else {
            titleDes.add(inputTx.substring(0, quoteIdx));
            titleDes.add(inputTx.substring(quoteIdx + 3));
        }
        return titleDes;
    }

    @NonNull
    @Override
    public CharacterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutRes = 0;
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
        }else{
            List<String> tx;
            ShowCharacter dataBean = dataSource.get(position);
            tx = findTitleDes(dataBean.getText());
            holder.titleTv.setText(tx.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
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
                    listener.recyclerViewListClicked(v, getLayoutPosition());
                }
            });
        }
    }
}
