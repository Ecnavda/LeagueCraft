package com.example.leaguecraft

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemSelectionFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // NOTE: Difference between onCreate and onCreateview
    // https://stackoverflow.com/questions/28929637/difference-and-uses-of-oncreate-oncreateview-and-onactivitycreated-in-fra
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
        return inflater.inflate(R.layout.fragment_item_selection, container, false)
    }

    // NOTE: Why setting onClick listeners here
    // https://stackoverflow.com/questions/60681658/kotlin-setonclicklistener-on-fragment-crashes-app
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var viewArr = ArrayList<View>()
        viewArr.add(view.findViewById(R.id.itemBuildItem1))
        viewArr.add(view.findViewById(R.id.itemBuildItem2))
        viewArr.add(view.findViewById(R.id.itemBuildItem3))
        viewArr.add(view.findViewById(R.id.itemBuildItem4))
        viewArr.add(view.findViewById(R.id.itemBuildItem5))
        viewArr.add(view.findViewById(R.id.itemBuildItem6))

        makeClickable(viewArr, this)
    }
}

private fun makeClickable(viewArray: ArrayList<View>, frag: Fragment) {
    viewArray.map {
        it.setOnClickListener {
            val nav = NavHostFragment.findNavController(frag)
            nav.navigate(R.id.action_itemSelectionFragment_to_itemSelectionList)
        }
    }
}