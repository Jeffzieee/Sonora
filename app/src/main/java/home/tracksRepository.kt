/* SONORA - tracks Repository
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import java.util.*

class tracksRepository {
    private lateinit var dataList: MutableLiveData<List<track>>
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("tracks").child("common")

    @Volatile
    private var INSTANCE: tracksRepository? = null

    fun getInstance(): tracksRepository {
        return INSTANCE ?: synchronized(this) {

            val instance = tracksRepository()
            INSTANCE = instance
            instance
        }
    }

    fun loadUsers(trackList: MutableLiveData<List<track>>) {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                try {

                    val _trackList: List<track> = snapshot.children.map { dataSnapshot ->

                        dataSnapshot.getValue(track::class.java)!!

                    }

                    val recommended_tracks = engageRecommendationEngine(_trackList)
                    trackList.postValue(recommended_tracks)
                    dataList.postValue(recommended_tracks)

                } catch (e: Exception) {


                }
//

            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            fun engageRecommendationEngine(list : List<track>): List<track> {
                return list.shuffled()
            }
        })
    }

}