package com.example.leaguecraft

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView

//NOTE: Android recyclerview docs
//
class ItemListAdapter(private val data: ArrayList<Item>) : RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName : TextView = view.findViewById(R.id.itemName)
        val itemPic: ImageView = view.findViewById(R.id.itemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_selection_row, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemName.text = data[position].name
        holder.itemPic.setImageDrawable(
            getImage(data[position].image.full, holder.itemPic.context)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    // NOTE: Using image file name to get drawable
    // https://stackoverflow.com/questions/44897639/how-to-get-image-name-from-drawable-object
    private fun getImage(fileName: String, context: Context) : Drawable? {
        val removeExtension = fileName.split('.')[0]
        val fName = "_" + removeExtension
        val r = context.resources
        val imageInt = r.getIdentifier(fName, "drawable", context.packageName)
        return AppCompatResources.getDrawable(context, imageInt)
    }
}