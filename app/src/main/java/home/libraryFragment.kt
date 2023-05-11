/* SONORA - LibraryFragment
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
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chill.chillActivity
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import com.codered.sonora.R
import drive.driveActivity
import english.engActivity
import hindi.hindiActivity
import login.signupActivity
import malayalam.malayalamActivity
import org.json.JSONException
import party.partyActivity
import pop.popActivity
import tamil.tamilActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [libraryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var english : Button
private lateinit var hindi : Button
private lateinit var malayalam : Button
private lateinit var tamil : Button
private lateinit var pop : Button
private lateinit var drive : Button
private lateinit var party : Button
private lateinit var chill : Button
class libraryFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment libraryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            libraryFragment().apply {
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
                        "openHome" -> {
                            val fragment2 : Fragment = homeFragment()
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
                    }
                } catch (e: JSONException) {
                    e.message?.let { Log.e("AlanButton", it) }
                }
            }
        };

/// Register callbacks
        alanButton?.registerCallback(alanCallback);

        //RecyclerView Implementation
         english = view.findViewById(R.id.btn_English)
        english.setOnClickListener{
            val intent = Intent (getActivity(), engActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        hindi = view.findViewById(R.id.btn_Hindi)
        hindi.setOnClickListener {
            val intent = Intent (getActivity(), hindiActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        malayalam = view.findViewById(R.id.btn_Malayalam)
        malayalam.setOnClickListener {
            val intent = Intent (getActivity(), malayalamActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        tamil = view.findViewById(R.id.btn_Tamil)
        tamil.setOnClickListener {
            val intent = Intent (getActivity(), tamilActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        pop = view.findViewById(R.id.btn_Pop)
        pop.setOnClickListener {
            val intent = Intent (getActivity(), popActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        drive = view.findViewById(R.id.btn_Drive)
        drive.setOnClickListener {
            val intent = Intent (getActivity(), driveActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        party = view.findViewById(R.id.btn_Party)
        party.setOnClickListener {
            val intent = Intent (getActivity(), partyActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        chill = view.findViewById(R.id.btn_Chill)
        chill.setOnClickListener {
            val intent = Intent (getActivity(), chillActivity::class.java)
            getActivity()?.startActivity(intent)
        }

        }

    }
