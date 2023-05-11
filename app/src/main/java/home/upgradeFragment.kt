/* SONORA - upgradeFragment
   Language - Kotlin
   Code - jeffzieee
   Team - CodeRED. 2022
 */

package home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.alan.alansdk.AlanCallback
import com.alan.alansdk.AlanConfig
import com.alan.alansdk.button.AlanButton
import com.alan.alansdk.events.EventCommand
import com.codered.sonora.R
import org.json.JSONException
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [upgradeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class upgradeFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_upgrade, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment upgradeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            upgradeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

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

                        "openLibrary" -> {
                            val fragment2 : Fragment = libraryFragment()
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
    }
}