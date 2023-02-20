package home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import home.tracksRepository

class trackViewModel :  ViewModel(){
    private val repository : tracksRepository
    private val _allTracks = MutableLiveData<List<track>>()
    val allTracks : LiveData<List<track>> = _allTracks


    init {

        repository = tracksRepository().getInstance()
        repository.loadUsers(_allTracks)

    }
}