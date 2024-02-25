package com.raihanardila.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast

class ListMailAdapter(private val listMail: ArrayList<Mail>) : RecyclerView.Adapter<ListMailAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.home_mail_item_row, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMail.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description,subject, photo) = listMail[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvSubject.text = subject
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMail[position])
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Mail)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvSubject: TextView = itemView.findViewById(R.id.tv_item_subject)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}
