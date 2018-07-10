package com.xfinity.characterviewer.di.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.xfinity.characterviewer.di.qualifier.ActivityContext;
import com.xfinity.characterviewer.di.scope.PerFragment;
import com.xfinity.characterviewer.source.CharacterRepository;
import com.xfinity.characterviewer.ui.characterlist.CharacterListContract;
import com.xfinity.characterviewer.ui.characterlist.CharacterListFragment;
import com.xfinity.characterviewer.ui.characterlist.CharacterListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CharacterListModule {

    private CharacterListContract.IView view;
    private Context context;

    public CharacterListModule(CharacterListContract.IView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    @PerFragment
    CharacterListContract.IView providesView() {
        return view;
    }

    @Provides
    @PerFragment
    @ActivityContext
    Context providesContext(){
        return context;
    }

    @Provides
    @PerFragment
    CharacterListContract.IPresenter providesCharacterListPresenter(CharacterListFragment listFragmentRef, CharacterRepository characterRepository) {
        return new CharacterListPresenter(listFragmentRef, characterRepository);
    }

    @Provides
    @PerFragment
    LinearLayoutManager providesLinearLayoutManager(){
        return new LinearLayoutManager(context);
    }

}
