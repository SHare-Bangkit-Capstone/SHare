package com.rechit.share.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rechit.share.R
import com.rechit.share.databinding.ItemRowPsikologBinding
import com.rechit.share.data.Psikolog

class ListPsikologAdapter(private val listPsikolog: ArrayList<Psikolog>): RecyclerView.Adapter<ListPsikologAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowPsikologBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_psikolog, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, photo) = listPsikolog[position]
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.binding.tvItemName.text = name
    }

    override fun getItemCount(): Int = listPsikolog.size
}