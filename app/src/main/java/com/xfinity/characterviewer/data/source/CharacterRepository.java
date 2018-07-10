package com.xfinity.characterviewer.data.source;

import com.xfinity.characterviewer.data.source.remote.CharacterRemoteDataSource;
import com.xfinity.characterviewer.data.source.remote.RetrofitHelper;
import com.xfinity.characterviewer.model.CharacterSet;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *CharacterRepository manages the remote datasource and communicate with presenters
 */
public class CharacterRepository {
    private CharacterRemoteDataSource mCharactersRemoteDataSource;

    /**
     * This is a constructor
     * @param charactersRemoteDataSource an remote datasource Instance
     */
    public CharacterRepository(CharacterRemoteDataSource charactersRemoteDataSource) {
        this.mCharactersRemoteDataSource = charactersRemoteDataSource;
    }

    /**
     *
     * @return an instance of CharacterRepository
     */
    public static CharacterRepository getInstance() {
        CharacterRemoteDataSource remoteDataSource = new CharacterRemoteDataSource(new RetrofitHelper());
        return new CharacterRepository(remoteDataSource);
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
