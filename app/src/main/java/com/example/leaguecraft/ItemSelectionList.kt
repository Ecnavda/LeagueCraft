package com.example.leaguecraft

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemSelectionList.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemSelectionList : Fragment() {
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
        return inflater.inflate(R.layout.fragment_item_selection_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepData(this.context)
        // NOTE: findViewByID in fragments
        // https://stackoverflow.com/questions/46419171/how-to-fetch-resource-id-in-fragment-using-kotlin-in-android
        val recycleView = view.findViewById<RecyclerView>(R.id.itemSelectionRecycler)
        val adapter = ItemListAdapter()

        recycleView.adapter = adapter
    }
}

private fun prepData(c: Context?) {
    if (c != null) {
        val assMan: AssetManager = c.assets
        val f = assMan.open("item.json")
        // NOTE: Why buffered I/O streams
        // https://medium.com/@isaacjumba/why-use-bufferedreader-and-bufferedwriter-classses-in-java-39074ee1a966
        val jsonData = f.bufferedReader()
        // readLine() prints the whole file (item.json)
        val jsonString = jsonData.readLine()

        // NOTE: Using built-in JSONobject for parsing
        // https://johncodeos.com/how-to-parse-json-in-android-using-kotlin/
        val jObj = JSONObject(jsonString)
        println(jObj.get("type"))
    }

}