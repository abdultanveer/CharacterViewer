package com.xfinity.characterviewer.di.component;

import com.xfinity.characterviewer.di.module.CharacterListModule;
import com.xfinity.characterviewer.di.scope.PerFragment;
import com.xfinity.characterviewer.ui.characterlist.CharacterListFragment;


import dagger.Component;

@PerFragment
@Component(modules = {CharacterListModule.class}, dependencies = {AppComponent.class})
public interface CharacterListComponent {
    void inject(CharacterListFragment characterListFragment);
}
