package com.brandon.hotelbookingapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.model.HotelLocations
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class HotelLocationsAdapter(
    context: Context,
    locations: ArrayList<HotelLocations>
) : RecyclerView.Adapter<HotelLocationsAdapter.HotelLocationsViewHolder>() {

    private var mLocations: ArrayList<HotelLocations> = ArrayList()
    private var mContext: Context? = null

    companion object {
        private const val TAG = "HotelLocationsAdapter"
    }

    init {
        mContext = context
        mLocations = locations
    }

    inner class HotelLocationsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var location: CircleImageView = view.findViewById(R.id.location)
        var locationName: TextView = view.findViewById(R.id.location_name)
        var parentLayout: LinearLayout = view.findViewById(R.id.parent_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelLocationsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_locations_view,
                parent,
                false
            )
        return HotelLocationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelLocationsViewHolder, position: Int) {

        Glide.with(mContext!!)
            .asBitmap()
            .load(mLocations[position].locationUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.location)

        holder.locationName.text = mLocations[position].locationName

        holder.parentLayout.setOnClickListener {
            Log.d(TAG, "Clicked ${mLocations[position].locationName}")
        }
    }

    override fun getItemCount(): Int {
        return mLocations.size
    }
}
