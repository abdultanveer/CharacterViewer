package com.xfinity.characterviewer.source;

import com.xfinity.characterviewer.model.CharacterSet;
import com.xfinity.characterviewer.source.remote.CharacterRemoteDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *CharacterRepository manages the remote data source and communicate with presenters
 */
@Singleton
public class CharacterRepository {
    private CharacterRemoteDataSource mCharactersRemoteDataSource;

    /**
     * This is a constructor
     * @param charactersRemoteDataSource an remote data source Instance
     */
    @Inject
    public CharacterRepository(CharacterRemoteDataSource charactersRemoteDataSource) {
        this.mCharactersRemoteDataSource = charactersRemoteDataSource;
    }

    /**
     * Make Network request and use the DataListener to handler the response from server
     * @param listener data handler
     */
    public void requestCharacterData(final CharacterDataListener listener) {
        Observable<CharacterSet> observable = mCharactersRemoteDataSource.getCharacterService().getCharacterSet("");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CharacterSet>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CharacterSet characterSet) {
                        listener.handleCharacterResponse(characterSet);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.handleError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface CharacterDataListener {
        void handleCharacterResponse(CharacterSet characterData);
        void handleError();
    }

}
