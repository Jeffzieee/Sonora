package pop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import home.homeFragment
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codered.sonora.R
import com.google.android.material.internal.ContextUtils.getActivity
import mediaplayer.mediaplayerActivity
import java.security.AccessController.getContext

class searchAdapter(private val context : Context,private var trackList: MutableList<track>) : RecyclerView.Adapter<searchAdapter.ViewHolder>(){

    private val originalTrackList: MutableList<track> = ArrayList(trackList)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)

        return ViewHolder(itemView)
    }

    @SuppressLint("RestrictedApi")
    override fun onBindViewHolder(holder: searchAdapter.ViewHolder, position: Int) {

        val tracks = trackList[position]
        holder.artist.text = tracks.artist
        holder.title.text = tracks.title
        holder.id.text = tracks.id
        holder.mp3url.text = tracks.mp3url

        Glide.with(context)
            .load(tracks.imgurl)
            .transform(RoundedCorners(25))
            .into(holder.imgurl)

        holder.itemView.setOnClickListener(){
            val intent = Intent(getActivity(context), mediaplayerActivity::class.java)
            intent.putExtra("imgUrl",tracks.imgurl)
            intent.putExtra("title",tracks.title)
            intent.putExtra("mp3url",tracks.mp3url)
            intent.putExtra("id",tracks.id)
            intent.putExtra("artist",tracks.artist)
            getActivity(context)?.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return trackList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgurl : ImageView = itemView.findViewById(R.id.imgae)
        val title : TextView = itemView.findViewById(R.id.title)
        val artist : TextView = itemView.findViewById(R.id.artist)
        val id : TextView = itemView.findViewById(R.id.id)
        val mp3url : TextView = itemView.findViewById(R.id.view_mp3url)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: List<track>) {
        trackList.clear()
        trackList.addAll(filteredList)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun resetList() {
        trackList.clear()
        trackList.addAll(originalTrackList)
        notifyDataSetChanged()
    }


}