package com.sajin.virginmoneyapp.presentation.people.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.sajin.virginmoneyapp.R
import com.sajin.virginmoneyapp.databinding.LayoutItemPeopleBinding
import com.sajin.virginmoneyapp.domain.model.Result

class PeopleAdapter(
    private val resultModel: List<Result>,
    private val listener: (Result) -> Unit
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    override fun getItemCount() = resultModel.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutItemPeopleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultModel[position])
    }

    inner class ViewHolder(private val binding: LayoutItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) = with(itemView) {

            Glide.with(context)
                .load(result.avatar)
                .placeholder(R.drawable.animate_loading)
                .error(R.drawable.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(binding.circleIV)
            binding.result = result
            binding.executePendingBindings()
        }
    }

}