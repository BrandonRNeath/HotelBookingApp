package com.brandon.hotelbookingapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import com.brandon.hotelbookingapp.db.model.HotelLocations
import com.brandon.hotelbookingapp.ui.fragments.HomeFragmentDirections
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import timber.log.Timber


class HotelLocationsAdapter(
    applicationViewModel: ApplicationViewModel,
    context: Context,
    private val hotelLocations: MutableList<HotelLocations>
) : RecyclerView.Adapter<HotelLocationsAdapter.HotelLocationsViewHolder>() {

    private var mContext: Context? = null
    private var mApplicationViewModel: ApplicationViewModel?

    init {
        mContext = context
        mApplicationViewModel = applicationViewModel
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

        try {
            Glide.with(mContext!!)
                .asBitmap()
                .load(hotelLocations[position].locationImageUrl)
                .placeholder(R.drawable.loading_image_default_placeholder)
                .error(R.drawable.location_placeholder_24)
                .into(holder.location)
        } catch (ex: Exception) {
            Timber.e(ex, mContext!!.getString(R.string.loading_hotel_location_images_error))
        }

        holder.locationName.text = hotelLocations[position].locationName

        holder.parentLayout.setOnClickListener {
            Timber.d("Clicked ${hotelLocations[position].locationName}")
            val action =
                HomeFragmentDirections.navigateToHotelLocation(hotelLocations[position].locationName)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return hotelLocations.size
    }

    fun updateHotelLocations(hotelLocations: List<HotelLocations>) {
        this.hotelLocations.clear()
        this.hotelLocations.addAll(hotelLocations)
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "HotelLocationsAdapter"
    }
}
