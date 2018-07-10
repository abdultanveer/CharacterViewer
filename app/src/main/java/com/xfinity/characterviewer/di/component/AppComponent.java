package com.xfinity.characterviewer.di.component;


import com.xfinity.characterviewer.CharacterViewerApp;
import com.xfinity.characterviewer.di.module.AppModule;
import com.xfinity.characterviewer.di.scope.PerApplication;
import com.xfinity.characterviewer.source.CharacterRepository;

import javax.inject.Singleton;

import dagger.Component;


@PerApplication
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(CharacterViewerApp characterViewerApp);

    CharacterRepository getCharacterRepository();
}
