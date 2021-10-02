package com.example.movieapps.ui.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.databinding.FragmentTvBinding

class TvFragment : Fragment() {

    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvViewModel::class.java]

            val tvAdapter = TvAdapter()
            tvAdapter.setListTv(viewModel.getTv())

            with(fragmentTvBinding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }

    }

}