package com.example.homework14_leacture17.View.activitys.recycler_adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework14_leacture17.model.data.Mushroom
import com.example.homework14_leacture17.databinding.RecyclerDangerousMushroomItemBinding
import com.example.homework14_leacture17.databinding.RecyclerSafeMushroomItemBinding

// recyclers adapters fall in View layer in MVVM architecture
class MyRecyclerAdapter(private val listener: ItemListener) : ListAdapter<Mushroom, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {
    companion object {

        const val SAFE = 1
        const val DANGEROUS = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Mushroom>() {
            override fun areItemsTheSame(oldItem: Mushroom, newItem: Mushroom): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Mushroom, newItem: Mushroom): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class SafeMushroomViewHolder(private val binding: RecyclerSafeMushroomItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(mushroom: Mushroom){
            setInfo(mushroom)
        }

        private fun setInfo(mushroom: Mushroom){
            binding.apply {
                idNum.text = mushroom.id.toString()
                tvName.text = mushroom.name
                tvHabitat.text = mushroom.habitats.toString()
                removeBtn.setOnClickListener{
                    listener.removeItem(mushroom)
                }
                editBtn.setOnClickListener{
                    listener.editItem(mushroom)
                }
            }
        }
    }

    inner class DangerousMushroomViewHolder(private val binding: RecyclerDangerousMushroomItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(mushroom: Mushroom){
            setInfo(mushroom)
        }

        private fun setInfo(mushroom: Mushroom){
            binding.apply {
                idNum.text = mushroom.id.toString()
                tvName.text = mushroom.name
                removeBtn.setOnClickListener{
                    listener.removeItem(mushroom)
                }
                editBtn.setOnClickListener{
                    listener.editItem(mushroom)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item.ifEdible.id) {
            SAFE -> SAFE  //item1
            else -> DANGEROUS  //item2
        } // for better visualization. I could do same without when...
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == SAFE){
            return SafeMushroomViewHolder(
                RecyclerSafeMushroomItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        }else{
            return DangerousMushroomViewHolder(
                RecyclerDangerousMushroomItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mushroom = getItem(position)
        if(holder is SafeMushroomViewHolder)holder.bind(mushroom)
        else if(holder is DangerousMushroomViewHolder)holder.bind(mushroom)
    }
}

interface ItemListener {
    fun removeItem(field: Mushroom)
    fun editItem(field: Mushroom)
}