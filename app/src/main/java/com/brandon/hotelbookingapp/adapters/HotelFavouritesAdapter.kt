package com.brandon.hotelbookingapp.adapters

import android.content.Context
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
import timber.log.Timber

class HotelFavouritesAdapter(
    context: Context,
    private val hotelFavourites: MutableList<HotelListing>
) : RecyclerView.Adapter<HotelFavouritesAdapter.HotelFavouritesViewHolder>() {

    private var mContext: Context? = null

    init {
        mContext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelFavouritesViewHolder {
        return HotelFavouritesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.hotel_listing_view, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HotelFavouritesViewHolder, position: Int) {

        try {
            Glide.with(mContext!!)
                .asBitmap()
                .load(hotelFavourites[position].hotelImageUrl)
                .placeholder(R.drawable.loading_image_default_placeholder)
                .error(R.drawable.location_placeholder_24)
                .into(holder.hotelImage)
        } catch (ex: Exception) {
            Timber.e(ex, mContext!!.getString(R.string.loading_hotel_listing_images_error))
        }

        val hotelRatingText = "${hotelFavourites[position].hotelRating}/100"

        holder.hotelName.text = hotelFavourites[position].hotelName

        val priceAverage = "Prices from £" + hotelFavourites[position].priceAverage.toString()

        holder.priceAverage.text = priceAverage

        holder.hotelRating.text = hotelRatingText

        holder.hotelRatingFaceReview
            .setImageResource(calculateHotelReviewFace(hotelFavourites[position].hotelRating))

        // As view is reused from hotel listing view favourite star is hidden as is redundant
        holder.favouriteStarImage.isEnabled = false
        holder.favouriteStarImage.visibility = View.INVISIBLE
    }

    fun updateHotelFavourites(hotelFavourites: List<HotelListing>) {
        this.hotelFavourites.clear()
        this.hotelFavourites.addAll(hotelFavourites)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return hotelFavourites.size
    }

    inner class HotelFavouritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val hotelImage: ImageView = view.findViewById(R.id.hotel_iv)
        val hotelName: TextView = view.findViewById(R.id.hotel_name_tv)
        val priceAverage: TextView = view.findViewById(R.id.price_average_tv)
        val hotelRating: TextView = view.findViewById(R.id.hotel_rating_tv)
        val hotelRatingFaceReview: ImageView = view.findViewById(R.id.hotel_face_review_iv)
        val favouriteStarImage: ImageView = view.findViewById(R.id.favourite_star_iv)
    }

    companion object {
        private const val TAG = "HotelFavouritesAdapter"
    }
}
