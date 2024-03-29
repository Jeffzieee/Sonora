/* SONORA - HomeFragment
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chill.chillActivity
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import com.codered.sonora.R
import com.google.android.material.internal.ContextUtils
import drive.driveActivity
import english.engActivity
import hindi.hindiActivity
import malayalam.malayalamActivity
import mediaplayer.mediaplayerActivity
import org.json.JSONException
import party.partyActivity
import pop.popActivity
import tamil.tamilActivity
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private lateinit var viewModel : trackViewModel
private lateinit var userRecyclerView: RecyclerView
private lateinit var adapter: recyclerAdapter
private lateinit var testBtn : Button

class homeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var alanButton: AlanButton? = null

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

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment homeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            homeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //ALAN implementation Here
        val config = AlanConfig.builder().setProjectId("518fe85d398d74f635ae1a9d3483bb2e2e956eca572e1d8b807a3e2338fdd0dc/stage").build()
        alanButton = view.findViewById(R.id.alan_button)
        alanButton?.initWithConfig(config)

        val alanCallback: AlanCallback = object : AlanCallback() {
            /// Handle commands from Alan Studio
            override fun onCommand(eventCommand: EventCommand) {
                try {
                    val command = eventCommand.data
                    val commandName = command.getJSONObject("data").getString("command")
                    when(commandName){
                        "openLibrary" -> {
                            val fragment2 : Fragment = libraryFragment()
                            val fragmentManager = parentFragmentManager
                            val transaction = fragmentManager.beginTransaction()
                            transaction.replace(R.id.fragment_Container, fragment2)
                            transaction.commit()
                        }

                        "openSearch" -> {
                            val fragment2 : Fragment = searchFragment()
                            val fragmentManager = parentFragmentManager
                            val transaction = fragmentManager.beginTransaction()
                            transaction.replace(R.id.fragment_Container, fragment2)
                            transaction.commit()
                        }

                        "openAbout" -> {
                            val fragment2 : Fragment = upgradeFragment()
                            val fragmentManager = parentFragmentManager
                            val transaction = fragmentManager.beginTransaction()
                            transaction.replace(R.id.fragment_Container, fragment2)
                            transaction.commit()
                        }

                        "openEnglish" -> {
                            val intent = Intent (getActivity(), engActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openHindi" -> {
                            val intent = Intent (getActivity(), hindiActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openMalayalam" -> {
                            val intent = Intent (getActivity(), malayalamActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openTamil" -> {
                            val intent = Intent (getActivity(), tamilActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openPop" -> {
                            val intent = Intent (getActivity(), popActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openDrive" -> {
                            val intent = Intent (getActivity(), driveActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openParty" -> {
                            val intent = Intent (getActivity(), partyActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "openChill" -> {
                            val intent = Intent (getActivity(), chillActivity::class.java)
                            getActivity()?.startActivity(intent)
                        }

                        "playEnglish" -> {

                            val titleList = listOf("As It Was", "Still Don't Know My Name", "Don't Wanna Know", "Bones", "No Lie")
                            val artistList = listOf("Harry Styles", "Labrinth", "Maroon 5", "Imagine Dragons", "Sean Paul")
                            val idList = listOf("1","2","3","4","5")
                            val imgList = listOf("https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/imgAsitwas.jpg?alt=media&token=cfa69d0f-6722-49ff-b9ac-30c8698f4f21", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/imgStill.jpg?alt=media&token=dd9a63ab-e752-485f-8d60-63b56d9dbd17", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/imgDont.jpg?alt=media&token=302f98e4-96f7-48bd-af95-2f4da7c7db70", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/imgBones.jpg?alt=media&token=9af22da1-c69d-4a69-87ef-81dadd352f26", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/imgNolie.jpg?alt=media&token=e0aa8bdb-70a3-47d1-b5f5-7d03fb644e7b")
                            val mp3List = listOf("https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/mp3Asitwas.mp3?alt=media&token=7831f711-057d-4e2d-b19d-82f5da7a3b7e", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/labrithEuphoria.mp3?alt=media&token=bd4104c6-a512-4df1-acae-84f59499ea66", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/mp3Dont.mp3?alt=media&token=c7580862-e5c2-45fd-9a3e-6bff039633f1", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/mp3Bones.mp3?alt=media&token=70446f72-6e19-4937-a6a1-cb777c10e5b4", "https://firebasestorage.googleapis.com/v0/b/sonora-b64bc.appspot.com/o/mp3NoLie.mp3?alt=media&token=d0fb52b7-98d8-41be-9f03-0dc5d7423ef1")
                            val index = Random.nextInt(idList.size)

                            val intent = Intent(getActivity(), mediaplayerActivity::class.java)
                            intent.putExtra("imgUrl",imgList[index])
                            intent.putExtra("title",titleList[index])
                            intent.putExtra("mp3url",mp3List[index])
                            intent.putExtra("id",idList[index])
                            intent.putExtra("artist",artistList[index])
                            getActivity()?.startActivity(intent)
                        }
                    }
                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }
            }
        };

/// Register callbacks
        alanButton?.registerCallback(alanCallback);


        testBtn = view.findViewById(R.id.switchBtn)
        testBtn.setOnClickListener{
            val fragment2 : Fragment = libraryFragment()
            val fragmentManager = parentFragmentManager

            // Begin a new transaction.
            val transaction = fragmentManager.beginTransaction()

            // Replace the current fragment with the new one.
            transaction.replace(R.id.fragment_Container, fragment2)

            // Add the transaction to the back stack, so the user can navigate back to the previous fragment.

            // Commit the transaction.
            transaction.commit()
        }

        //RecyclerView Implementation
        userRecyclerView = view.findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(context)
        userRecyclerView.setHasFixedSize(true)
        adapter = getContext()?.let { recyclerAdapter(it) }!!
        userRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(trackViewModel::class.java)

        viewModel.allTracks.observe(viewLifecycleOwner, Observer {

            adapter.updateTrackList(it)

        })
    }
}
