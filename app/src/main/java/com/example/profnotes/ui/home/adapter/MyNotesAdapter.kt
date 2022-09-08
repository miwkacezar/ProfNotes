package com.example.profnotes.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.profnotes.data.model.MyNote
import com.example.profnotes.databinding.ItemNoteBinding

class MyNotesAdapter : RecyclerView.Adapter<MyNotesAdapter.ViewHolder>() {

    private var items = emptyList<MyNote>()

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyNote) {
            with(binding) {
                tvNoteTitle.text = item.title
                tvNoteDescription.text = item.description
                tvNoteStatus.text = item.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNoteBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newItems: List<MyNote>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}