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
import com.example.movieapps.R
import com.example.movieapps.databinding.FragmentTvBinding
import com.example.movieapps.viewmodel.ViewModelFactory
import com.example.movieapps.vo.Status

class TvFragment : Fragment() {
    private var _fragmentTvBinding: FragmentTvBinding? = null
    private val binding get() = _fragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTvBinding = FragmentTvBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvAdapter = TvAdapter()

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvViewModel::class.java]

            viewModel.getTv().observe(viewLifecycleOwner, { tv ->
                if (tv != null) {
                    when (tv.status) {
                        Status.LOADING -> binding?.progressBar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding?.progressBar?.visibility = View.GONE
                            tv.data?.let { tvAdapter.submitList(it) }
                            tvAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            binding?.progressBar?.visibility = View.GONE
                            Toast.makeText(
                                context,
                                getString(R.string.network_error),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

            binding?.rvTv?.layoutManager = LinearLayoutManager(context)
            binding?.rvTv?.setHasFixedSize(true)
            binding?.rvTv?.adapter = tvAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvBinding = null
    }
}