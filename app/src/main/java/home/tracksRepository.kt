/* SONORA - tracks Repository
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class tracksRepository {
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

                    trackList.postValue(_trackList)

                } catch (e: Exception) {


                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}