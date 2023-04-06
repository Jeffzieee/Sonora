/* SONORA - SearchFragment
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.events.EventCommand
import com.codered.sonora.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_search.*
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [searchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


private lateinit var searchView : SearchView
private lateinit var databaseReference: DatabaseReference
private var trackList: MutableList<track> = mutableListOf()
private lateinit var SearchAdapter: searchAdapter
class searchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_search, container, false)



        val recyclerView: RecyclerView = view.findViewById(R.id.searchrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        SearchAdapter = searchAdapter(requireContext(), trackList)
        recyclerView.adapter = SearchAdapter

        databaseReference = FirebaseDatabase.getInstance().getReference("tracks").child("common")
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

        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment searchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            searchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        searchView = view.findViewById(R.id.searchBar)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    SearchAdapter.resetList()
                } else {
                    val filteredList = trackList.filter { track ->
                        newText.toLowerCase(Locale.ROOT)
                            .let { track.title?.toLowerCase(Locale.ROOT)?.contains(it) } ?: false
                    }
                    SearchAdapter.filterList(filteredList)
                }
                return true
            }
        })
    }

    private fun searchTracks(query: String) {
        val filteredList = ArrayList<track>()
        for (track in trackList) {
            if (track.title?.toLowerCase(Locale.getDefault())?.contains(query.toLowerCase(Locale.getDefault())) == true) {
                filteredList.add(track)
            }
        }
        SearchAdapter.filterList(filteredList)
    }
}