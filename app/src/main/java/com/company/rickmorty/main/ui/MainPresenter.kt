package com.company.rickmorty.main.ui

import com.company.rickmorty.main.api.RickAndMortyApi
import com.company.rickmorty.main.models.character.CharacterData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter(private val api: RickAndMortyApi) {

    private var mainContract: MainContract? = null

    fun attach(mainContractor: MainContract) {
        this.mainContract = mainContractor
    }

    fun detach() {
        mainContract = null
    }

    fun getCharacterDataFromApi() {
        api.getAllCharacters()
            .enqueue(object : Callback<CharacterData> {

                override fun onResponse(
                    call: Call<CharacterData>,
                    response: Response<CharacterData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        val characters = response.body()
                        mainContract?.showCharacterData(characters!!)
                    }
                }

                override fun onFailure(call: Call<CharacterData>, t: Throwable) {
                    mainContract?.dataFailure(t)
                }
            })
    }
//
//    fun getLocationDataFromApi() {
//        api.getAllLocations().enqueue(object : Callback<LocationData> {
//            override fun onResponse(call: Call<LocationData>, response: Response<LocationData>) {
//                if (response.isSuccessful && response.body() != null) {
//                    val locations = response.body()
//                    mainContract?.showLocationData(locations!!)
//                }
//            }
//
//            override fun onFailure(call: Call<LocationData>, t: Throwable) {
//                mainContract?.dataFailure(t)
//            }
//
//        })
//    }
//
//    fun getEpisodeDataFromApi() {
//        api.getAllEpisodes().enqueue(object : Callback<EpisodeData> {
//            override fun onResponse(call: Call<EpisodeData>, response: Response<EpisodeData>) {
//                if (response.isSuccessful && response.body() != null) {
//                    val episodes = response.body()
//                    mainContract?.showEpisodeData(episodes!!)
//                }
//            }
//
//            override fun onFailure(call: Call<EpisodeData>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }

}