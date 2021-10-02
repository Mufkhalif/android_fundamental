package com.example.movieapps.ui.tv

import androidx.lifecycle.ViewModel
import com.example.movieapps.data.TvEntity
import com.example.movieapps.utils.DataDummy

class TvViewModel : ViewModel() {
    fun getTv(): List<TvEntity> = DataDummy.generateTvs()
}