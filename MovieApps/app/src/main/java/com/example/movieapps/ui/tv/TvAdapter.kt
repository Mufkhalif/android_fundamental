package com.example.movieapps.ui.tv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movieapps.R
import com.example.movieapps.api.Constants
import com.example.movieapps.api.model.Tv
import com.example.movieapps.databinding.ItemTvBinding
import com.example.movieapps.ui.detail.tv.DetailTvActivity

class TvAdapter : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    private val listTv = ArrayList<Tv>()

    fun setListTv(tv: List<Tv>) {
        this.listTv.clear()
        this.listTv.addAll(tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.TvViewHolder {
        val binding = ItemTvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvAdapter.TvViewHolder, position: Int) {
        val tv = listTv[position]
        holder.bind(tv)
    }

    override fun getItemCount(): Int = listTv.size

    class TvViewHolder(private val binding: ItemTvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: Tv) {
            with(binding) {
                tvItemTitle.text = tv.name
                tvPopularity.text = tv.popularity.toString()
            }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailTvActivity::class.java)
                intent.putExtra(DetailTvActivity.EXTRA_TV, tv.id.toString())
                itemView.context.startActivity(intent)
            }

            Glide.with(itemView.context)
                .load("${Constants.baseImageUrl}${tv.poster_path}")
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(binding.imgPoster)

        }
    }
}