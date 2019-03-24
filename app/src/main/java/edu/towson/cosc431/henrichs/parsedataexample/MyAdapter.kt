package edu.towson.cosc431.henrichs.parsedataexample

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.listitem.view.*
import java.time.LocalDate

class MyAdapter(val listFeed: List<Task>) : RecyclerView.Adapter<CustomViewHolder>() {

    //numberOfItem
    override fun getItemCount(): Int = listFeed.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        // how to create a view
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.listitem, parent, false)
        return CustomViewHolder(view)
    }

    @TargetApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val task = listFeed[position]

        holder.itemView?.title_tv?.text = task.title
        holder.itemView?.contents_tv?.text = task.contents
        holder.itemView?.completed_cb?.isChecked = task.completed
        holder.itemView?.dateCreate_tv?.text = LocalDate.now().toString()

        val thumbNailImgView = holder.itemView?.avatar_iv
        Picasso.get().load(task.image_url).into(thumbNailImgView)
    }
}

class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)