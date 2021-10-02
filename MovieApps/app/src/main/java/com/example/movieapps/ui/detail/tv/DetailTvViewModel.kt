package com.example.movieapps.ui.detail.tv

import androidx.lifecycle.ViewModel
import com.example.movieapps.data.TvEntity
import com.example.movieapps.utils.DataDummy

class DetailTvViewModel : ViewModel() {
    private lateinit var id: String

    fun setCurrentId(id: String) {
        this.id = id
    }

    fun getTvDetail(): TvEntity {
        lateinit var tv: TvEntity
        val listTv = DataDummy.generateTvs()
        for (tvEntity in listTv) {
            if (tvEntity.id == id) {
                tv = tvEntity
            }
        }
        return tv
    }

}