package com.example.movieapps.ui.tv

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.databinding.FragmentTvBinding
import com.example.movieapps.viewmodel.ViewModelFactory

class TvFragment : Fragment() {
    private lateinit var fragmentTvBinding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return fragmentTvBinding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvAdapter = TvAdapter()

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            fragmentTvBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTv().observe(viewLifecycleOwner, { tv ->
                if (tv != null) {
                    tvAdapter.setListTv(tv.results)
                    tvAdapter.notifyDataSetChanged()
                }
                fragmentTvBinding.progressBar.visibility = View.GONE
            })

            with(fragmentTvBinding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }

    }

}