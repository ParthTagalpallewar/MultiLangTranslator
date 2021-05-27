package com.android.translateapplication.data.retrofit;

import com.android.translateapplication.data.responses.languagesResponse.LanguageResponse;
import com.android.translateapplication.data.responses.multilanguageResponse.MultiLanguageResponseModel;
import com.android.translateapplication.data.responses.translationResposne.TranslationResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    String BASE_URL = "https://api.cognitive.microsofttranslator.com/";

    @GET("languages")
    Call<LanguageResponse> getLanguages(
            @Query("api-version") String version,
            @Query("scope")String translation
    );

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key:9a45fd46efcb41a686d18c3ef6b7e1da",
            "Ocp-Apim-Subscription-Region:southeastasia"
    })
    @POST("translate")
    Call<ArrayList<TranslationResponse>> getTranslation(
            @Query("api-version") String version,
            @Query("to")String desireLang,
            @Query("from")String from,
            @Body JsonArray Text

    );


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json",
            "Ocp-Apim-Subscription-Key:9a45fd46efcb41a686d18c3ef6b7e1da",
            "Ocp-Apim-Subscription-Region:southeastasia"
    })
    @POST("translate")
    Call<ArrayList<MultiLanguageResponseModel>> getMultiLangTranslation(
            @Query("api-version") String version,
            @Query("to")String english,
            @Query("to")String nativeLang,
            @Query("to")String desireLang,
            @Query("from")String from,
            @Body JsonArray Text

    );


}