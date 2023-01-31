package com.example.myapplication.ui.main.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.NasaItemBinding
import com.example.myapplication.ui.main.data.NasaResult

class PagingAdapter : PagingDataAdapter<NasaResult, MainViewHolder>(DiffUtilCallBack()){

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            date.text = item?.earthDate.toString()
            roverName.text = item?.rover?.name.toString()
            camName.text = item?.camera?.name.toString()
            item?.let {
                Glide
                    .with(marsPhoto.context)
                    .load(it.image)
                    .into(marsPhoto)
            }
            holder.itemView.setOnClickListener { v ->
                val activity = v?.context as AppCompatActivity
                val secondFragment = SecondFragment(item ?: return@setOnClickListener)
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, secondFragment).addToBackStack(R.id.main_fragment.toString()).commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = NasaItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding)
    }
}

class DiffUtilCallBack : DiffUtil.ItemCallback<NasaResult>() {
    override fun areItemsTheSame(oldItem: NasaResult, newItem: NasaResult): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: NasaResult, newItem: NasaResult): Boolean =
        oldItem == newItem



}