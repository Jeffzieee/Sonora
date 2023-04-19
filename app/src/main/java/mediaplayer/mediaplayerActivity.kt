/* SONORA - MEDIAPLAYER Activity
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package mediaplayer

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.codered.sonora.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.firebase.database.*
import dagger.hilt.android.qualifiers.ApplicationContext

class mediaplayerActivity : AppCompatActivity(), Player.Listener{

    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var progressBar: ProgressBar
    private lateinit var titleTv: TextView
    private lateinit var btnLike : Button
    private lateinit var titleText : TextView
    private lateinit var artistText : TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediaplayer)

        var imgUrl = intent.extras?.getString("imgUrl")
        var id = intent.extras?.getString("id")
        var title = intent.extras?.getString("title")
        var artist = intent.extras?.getString("artist")
        progressBar = findViewById(R.id.progressBar)
        titleText =  findViewById(R.id.titleText)
        artistText = findViewById(R.id.artistText)

        titleText.text = title
        artistText.text = artist
        btnLike = findViewById(R.id.btnLike)

        btnLike.setOnClickListener {

            btnLike.setBackgroundResource(R.drawable.filllike)

            val musicItemsRef = FirebaseDatabase.getInstance().getReference("tracks").child("common")
            if (id != null) {
                musicItemsRef.child(id).child("likes").addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val currentLikes = dataSnapshot.getValue(Int::class.java) ?: 0
                        val updatedLikes = currentLikes + 1
                        musicItemsRef.child(id).child("likes").setValue(updatedLikes)

                        Toast.makeText(applicationContext,"Liked Track",
                            Toast.LENGTH_LONG).show()
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle database error
                        Toast.makeText(applicationContext,"Database Error",
                            Toast.LENGTH_LONG).show()
                    }
                })
            }

        }

        titleTv = findViewById(R.id.title)
        Glide.with(applicationContext)
            .load(imgUrl)
            .transform(RoundedCorners(100))
            .into(findViewById(R.id.audioImg))

        setupPlayer()
        addMP3()



        // restore playstate on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(restoredMediaItem, seekTime)
                player.play()
            }
        }
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.audio_view)
        playerView.player = player
        player.addListener(this)
    }

    private fun addMP3() {
        // Build the media item.
        var mp3url = intent.extras?.getString("mp3url")
        val mediaItem = mp3url?.let { MediaItem.fromUri(it) }
        if (mediaItem != null) {
            player.setMediaItem(mediaItem)
        }
        // Set the media item to be played.
        if (mediaItem != null) {
            player.setMediaItem(mediaItem)
        }
        // Prepare the player.
        player.prepare()
    }


    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
        addMP3()

    }

    // handle loading
    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE

            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    //get Title from metadata
    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {

        titleTv.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: "no title found"

    }

    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        android.util.Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
        // current play position
        outState.putLong("SeekTime", player.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
    }

    companion object {
        private const val TAG = "mediaplayerActivity"
    }

}

