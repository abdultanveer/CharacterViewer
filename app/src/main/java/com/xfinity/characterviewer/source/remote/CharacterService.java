package com.xfinity.characterviewer.source.remote;

import com.xfinity.characterviewer.model.CharacterSet;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * CharacterService provides methods to request ShowCharacter Set from server
 */
public interface CharacterService {
    @GET
    Observable<CharacterSet> getCharacterSet(@Url String emptyString);
}
