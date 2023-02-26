/* SONORA - recycler Adapter
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codered.sonora.R
import home.track

class recyclerAdapter(private val context : Context) : RecyclerView.Adapter<recyclerAdapter.MyViewHolder>(){

    private val trackList = ArrayList<track>()

    public class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imgurl : ImageView = itemView.findViewById(R.id.imgae)
        val title : TextView = itemView.findViewById(R.id.title)
        val artist : TextView = itemView.findViewById(R.id.artist)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: recyclerAdapter.MyViewHolder, position: Int) {

        val tracks = trackList[position]
        holder.artist.text = tracks.artist
        holder.title.text = tracks.title

        Glide.with(context).load(tracks.imgurl).into(holder.imgurl)


    }
    override fun getItemCount(): Int {
    return trackList.size
    }

    fun updateTrackList(trackList : List<track>){

        this.trackList.clear()
        this.trackList.addAll(trackList)
        notifyDataSetChanged()

    }


}