package com.example.githubsearch.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.adapter.ListUserAdapter
import com.example.githubsearch.api.User
import com.example.githubsearch.database.UserFavourite
import com.example.githubsearch.databinding.FragmentFollowersBinding


class FollowersFragment : Fragment() {
    private lateinit var _binding: FragmentFollowersBinding
    private val detailViewModel: DetailViewModel by activityViewModels()
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvUser.layoutManager = LinearLayoutManager(activity)

        detailViewModel.followers.observe(viewLifecycleOwner, { followers ->
            setData(followers)
        })

        detailViewModel.isLoadingFollowers.observe(viewLifecycleOwner, { loading ->
            showLoading(loading)
        })


    }

    private fun setData(users: List<User>) {
        with(binding) {
            if (users.isEmpty()) {
                rvUser.visibility = View.GONE
                tvEmpty.visibility = View.VISIBLE
            } else {
                tvEmpty.visibility = View.GONE
                rvUser.visibility = View.VISIBLE

                val adapter = ListUserAdapter(users)
                rvUser.adapter = adapter

                adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: User) {
                        detailViewModel.setDetailUser(data.login)

                        val userFavourite = UserFavourite(
                            id = data.id,
                            login = data.login,
                            avatarUrl = data.avatarUrl
                        )
                        detailViewModel.setDataUserNow(userFavourite)
                        detailViewModel.checkIsFavourite(data.login)
                            .observe(viewLifecycleOwner, { listUser ->
                                detailViewModel.setIsFav(listUser.isNotEmpty())
                            })
                    }
                })
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.shimmer.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
