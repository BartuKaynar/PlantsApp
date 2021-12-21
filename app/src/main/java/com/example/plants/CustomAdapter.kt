package com.example.plants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CustomAdapter(
    private val mList: List<PlantData>,
    private val fm: FragmentManager
) :
    RecyclerView.Adapter<CustomAdapter.PlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return PlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val PlantData = mList[position]
        holder.plant_name.text = PlantData.plant_name
        holder.plant_family.text = PlantData.plant_family
        holder.plant_sci.text = PlantData.plant_sci
        holder.plant_climate.text = PlantData.plant_climate
        Picasso.get().load(PlantData.plant_image).centerCrop().fit().into(holder.plant_image)

        holder.mCardView.setOnClickListener {
            val ImageFragment =
                ImageFragment.newInstance(PlantData.plant_image, PlantData.plant_name)
            ImageFragment.show(fm, "Show")
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class PlantViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val mCardView: CardView = itemView.findViewById(R.id.mCardView)
        val plant_image: ImageView = itemView.findViewById(R.id.plant_img)
        val plant_name: TextView = itemView.findViewById(R.id.plant_name)
        val plant_family: TextView = itemView.findViewById(R.id.plant_family)
        val plant_sci: TextView = itemView.findViewById(R.id.plant_sci)
        val plant_climate: TextView = itemView.findViewById(R.id.plant_climate)
    }
}

