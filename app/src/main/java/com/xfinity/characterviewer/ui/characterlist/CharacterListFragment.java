package com.xfinity.characterviewer.ui.characterlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xfinity.characterviewer.R;
import com.xfinity.characterviewer.data.source.CharacterRepository;
import com.xfinity.characterviewer.model.CharacterSet;
import com.xfinity.characterviewer.model.ShowCharacter;
import com.xfinity.characterviewer.ui.CharacterAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * CharacterListFragment is the fragment which actually resides in activity to show a list of characters
 */
public class CharacterListFragment extends Fragment implements CharacterAdapter.RecyclerViewClickListener,CharacterListContract.IView {
    private static final String TAG = "CharacterListFragment";
    private List<ShowCharacter> dataSource;
    private CharacterListPresenter mCharacterListPresenter;
    private CharacterAdapter adapter;
    private RecyclerView recyclerView;
    private Context mContext;
    private OnItemSelectedListener listener;

    @Override
    public void recyclerViewListClicked(View v, int position) {
        listener.onItemSelected(dataSource.get(position));
    }
    @Subscribe
    public void onEvent(Boolean b){
        for(ShowCharacter topic:dataSource) topic.setUseGrid(b);
        if(b){
            this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        }else{
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;

        dataSource = new ArrayList<>();
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement CharacterListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CharacterAdapter();
        adapter.setContext(mContext);
        adapter.setListener(this);
        mCharacterListPresenter = new CharacterListPresenter(this, CharacterRepository.getInstance());
    }

    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);
        recyclerView = view.findViewById(R.id.rvItems);
        makeRequest();
        EventBus.getDefault().register(this);
        return view;
    }

    /**
     * Request Character Data from Presenter
     */
    void makeRequest() {
        mCharacterListPresenter.requestCharacterData();
    }
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setAdapter(CharacterSet characterData) {
        this.dataSource = characterData.getShowCharacters();
        adapter.setDataSource(this.dataSource);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public interface OnItemSelectedListener {
        void onItemSelected(Object item);
    }

    @Override
    public void onDetach() {
        mContext = null;
        super.onDetach();
    }
}
