package com.brandon.hotelbookingapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brandon.hotelbookingapp.R
import com.brandon.hotelbookingapp.db.model.ApplicationViewModel
import com.brandon.hotelbookingapp.db.model.HotelFavourite
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.utils.AppUtils.calculateHotelReviewFace
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HotelListingAdapter(
    applicationViewModel: ApplicationViewModel,
    context: Context,
    private val hotelListings: MutableList<HotelListing>
) : RecyclerView.Adapter<HotelListingAdapter.HotelListingViewHolder>() {

    private var mContext: Context? = null
    private var mApplicationViewModel: ApplicationViewModel?

    init {
        mContext = context
        mApplicationViewModel = applicationViewModel
    }

    inner class HotelListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hotelImage: ImageView = view.findViewById(R.id.hotel_iv)
        val hotelName: TextView = view.findViewById(R.id.hotel_name_tv)
        val hotelRating: TextView = view.findViewById(R.id.hotel_rating_tv)
        val hotelRatingFaceReview: ImageView = view.findViewById(R.id.hotel_face_review_iv)
        val favouriteStarImage: ImageView = view.findViewById(R.id.favourite_star_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListingViewHolder {
        return HotelListingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.hotel_listing_view, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HotelListingViewHolder, position: Int) {
        var isFavourite = hotelListings[position].isFavourite

        try {
            Glide.with(mContext!!)
                .asBitmap()
                .load(hotelListings[position].hotelImageUrl)
                .placeholder(R.drawable.loading_image_default_placeholder)
                .error(R.drawable.location_placeholder_24)
                .into(holder.hotelImage)
        } catch (ex: Exception) {
            Log.e(
                TAG, mContext!!
                    .getString(R.string.loading_hotel_listing_images_error) + ex.printStackTrace()
            )
        }

        val hotelRatingText = "${hotelListings[position].hotelRating}/100"

        holder.hotelName.text = hotelListings[position].hotelName

        holder.hotelRating.text = hotelRatingText

        holder.hotelRatingFaceReview
            .setImageResource(calculateHotelReviewFace(hotelListings[position].hotelRating))

        if (isFavourite) {
            holder.favouriteStarImage.setImageResource(R.drawable.favourite_star_selected)
        } else {
            holder.favouriteStarImage.setImageResource(R.drawable.favourite_star_not_selected)
        }

        holder.favouriteStarImage.setOnClickListener {
            val hotelFavourite = HotelFavourite(
                id = hotelListings[position].id,
                hotelImageUrl = hotelListings[position].hotelImageUrl,
                hotelName = hotelListings[position].hotelName,
                hotelRating = hotelListings[position].hotelRating
            )
            if (isFavourite) {
                holder.favouriteStarImage.setImageResource(R.drawable.favourite_star_not_selected)
                GlobalScope.launch(Dispatchers.IO) {
                    isFavourite = false
                    mApplicationViewModel!!.updateHotelListing(isFavourite, hotelListings[position].id)
                    mApplicationViewModel!!.deleteHotelFavourite(hotelFavourite)
                }
            } else {
                isFavourite = true
                // User has selected favourite on a hotel
                holder.favouriteStarImage.setImageResource(R.drawable.favourite_star_selected)
                GlobalScope.launch(Dispatchers.IO) {
                    mApplicationViewModel!!.updateHotelListing(isFavourite, hotelListings[position].id)
                    mApplicationViewModel!!.addHotelFavourite(hotelFavourite)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return hotelListings.size
    }

    fun updateHotelListings(hotelListings: List<HotelListing>) {
        this.hotelListings.clear()
        this.hotelListings.addAll(hotelListings)
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "HotelListingAdapter"
    }
}
