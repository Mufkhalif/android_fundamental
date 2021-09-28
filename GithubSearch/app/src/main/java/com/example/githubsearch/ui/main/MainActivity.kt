package com.example.githubsearch.ui.main

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubsearch.adapter.ListUserAdapter
import com.example.githubsearch.R
import com.example.githubsearch.api.User
import com.example.githubsearch.database.UserFavourite
import com.example.githubsearch.databinding.ActivityMainBinding
import com.example.githubsearch.ui.detail.DetailActivity
import com.example.githubsearch.ui.favourite.FavouriteActivity
import com.example.githubsearch.ui.settings.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvSearch.layoutManager = layoutManager

        mainViewModel.listSearch.observe(this, { users ->
            setSearchData(users)
        })

        mainViewModel.snackbarText.observe(this, {
            Toast.makeText(
                this,
                it,
                Toast.LENGTH_SHORT
            ).show()
        })

        mainViewModel.isLoading.observe(this, {
            showLoading(it)
        })

    }

    private fun setSearchData(users: List<User>) {
        with(binding) {
            if (users.isEmpty()) {
                rvSearch.visibility = View.GONE
                tvEmpty.visibility = View.VISIBLE
            } else {
                tvEmpty.visibility = View.GONE
                rvSearch.visibility = View.VISIBLE

                val adapter = ListUserAdapter(users)
                binding.rvSearch.adapter = adapter

                adapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: User) {
                        val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                        detailIntent.putExtra(DetailActivity.EXTRA_USERNAME, data.login)

                        val userFavourite = UserFavourite(
                            id = data.id,
                            login = data.login,
                            avatarUrl = data.avatarUrl
                        )
                        detailIntent.putExtra(DetailActivity.EXTRA_USER, userFavourite)
                        startActivity(detailIntent)
                    }
                })
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        with(searchView) {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            queryHint = resources.getString(R.string.search_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    mainViewModel.searchUser(query)
                    Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }

            })
        }

        return true
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourite_list -> {
                val favouriteIntent = Intent(this@MainActivity, FavouriteActivity::class.java)
                startActivity(favouriteIntent)
            }
            R.id.settings -> {
                val settingsIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(settingsIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}