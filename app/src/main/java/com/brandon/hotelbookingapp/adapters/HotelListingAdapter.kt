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
import com.brandon.hotelbookingapp.db.model.HotelListing
import com.brandon.hotelbookingapp.utils.AppUtils.calculateHotelReviewFace
import com.bumptech.glide.Glide

class HotelListingAdapter(
    context: Context,
    hotelListings: ArrayList<HotelListing>
) : RecyclerView.Adapter<HotelListingAdapter.HotelListingViewHolder>() {

    private var mHotelListings: ArrayList<HotelListing> = ArrayList()
    private var mContext: Context? = null

    init {
        mContext = context
        mHotelListings = hotelListings
    }

    inner class HotelListingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hotelImage: ImageView = view.findViewById(R.id.hotel_iv)
        val hotelName: TextView = view.findViewById(R.id.hotel_name_tv)
        val hotelRating: TextView = view.findViewById(R.id.hotel_rating_tv)
        val hotelRatingFaceReview: ImageView = view.findViewById(R.id.hotel_face_review_iv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListingViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(
                R.layout.hotel_listing_view,
                parent,
                false
            )
        return HotelListingViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelListingViewHolder, position: Int) {

        try {
            Glide.with(mContext!!)
                .asBitmap()
                .load(mHotelListings[position].hotelImageUrl)
                .placeholder(R.drawable.loading_image_default_placeholder)
                .error(R.drawable.location_placeholder_24)
                .into(holder.hotelImage)
        } catch (ex: Exception) {
            Log.e(TAG, mContext!!
                .getString(R.string.loading_hotel_listing_images_error) + ex.printStackTrace())
        }

        val hotelRatingText = "${mHotelListings[position].hotelRating}/100"

        holder.hotelName.text = mHotelListings[position].hotelName

        holder.hotelRating.text = hotelRatingText

        holder.hotelRatingFaceReview
            .setImageResource(calculateHotelReviewFace(mHotelListings[position].hotelRating))

    }

    override fun getItemCount(): Int {
        return mHotelListings.size
    }

    companion object {
        private const val TAG = "HotelListingAdapter"
    }
}
