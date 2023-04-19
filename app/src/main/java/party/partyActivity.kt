package party

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codered.sonora.R
import com.google.firebase.database.*
import party.searchAdapter
import party.track

class partyActivity : AppCompatActivity() {
    private lateinit var searchView : SearchView
    private lateinit var databaseReference: DatabaseReference
    private var trackList: MutableList<track> = mutableListOf()
    private lateinit var SearchAdapter: searchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_party)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        SearchAdapter = searchAdapter(this, trackList)
        recyclerView.adapter = SearchAdapter

        databaseReference = FirebaseDatabase.getInstance().getReference("tracks").child("party")
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                trackList.clear()
                for (postSnapshot in snapshot.children) {
                    val track = postSnapshot.getValue(track::class.java)
                    if (track != null) {
                        trackList.add(track)
                    }
                }
                SearchAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}