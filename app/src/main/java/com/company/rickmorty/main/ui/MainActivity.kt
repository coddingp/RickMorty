package com.company.rickmorty.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.rickmorty.databinding.ActivityMainBinding
import com.company.rickmorty.main.adapter.RickMortyAdapter
import com.company.rickmorty.main.api.RetrofitClient
import com.company.rickmorty.main.api.RickAndMortyApi
import com.company.rickmorty.main.models.character.CharacterData
import com.company.rickmorty.main.models.episode.EpisodeData
import com.company.rickmorty.main.models.location.LocationData
import timber.log.Timber

class MainActivity : AppCompatActivity(), MainContract {

    private val api: RickAndMortyApi =
        RetrofitClient.getRetrofit().create(RickAndMortyApi::class.java)

    private val mainPresenter = MainPresenter(api)

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {

            mainPresenter.attach()
            mainPresenter.getCharacterDataFromApi()
            mainRecyclerView.layoutManager = LinearLayoutManager(baseContext)
            val adapter = RickMortyAdapter()
            mainRecyclerView.adapter = adapter

            setContentView(root)
        }

//        mainPresenter.getLocationDataFromApi()
//        mainPresenter.getEpisodeDataFromApi()
    }

    override fun showCharacterData(data: CharacterData) {
        Timber.i(data.toString())
        Timber.i(data.results[5].name)
    }

    override fun showLocationData(data: LocationData) {
        Timber.i(data.toString())
        Timber.i(data.results[5].residents[6])
    }

    override fun showEpisodeData(data: EpisodeData) {
        Timber.i(data.toString())
        Timber.i(data.info.count.toString())
    }

    override fun dataFailure(t: Throwable) {
        Timber.e(t.message)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detach()
    }
}