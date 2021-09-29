package com.example.leaguecraft

import android.app.Application
import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemBuildActivity : AppCompatActivity() {
    // NOTE: What "by" keyword means
    // https://stackoverflow.com/questions/38250022/what-does-by-keyword-do-in-kotlin
    val itemViewModel by viewModels<ItemJSONViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_build)

        // NOTE: Coroutine Tutorial
        // https://www.raywenderlich.com/1423941-kotlin-couroutines-tutorial-for-android-getting-started
        val cs = CoroutineScope(Dispatchers.IO)
        cs.launch {
            itemViewModel.data = loadItemJSON()
        }
        Toast.makeText(this, "Finished Loading File", Toast.LENGTH_SHORT).show()
    }

    private fun loadItemJSON() : ItemJSON {
        val assMan: AssetManager = this.assets
        val fileHandle = assMan.open("item.json")
        val unparsedJSON = fileHandle.bufferedReader().readLine()
        val itemFile = ItemJSON(unparsedJSON)
        fileHandle.close()
        return itemFile
    }
}

// NOTE: Passing data between activities/fragments
// https://stackoverflow.com/questions/58913190/how-to-pass-data-from-activity-to-fragment-using-onfragmentinteractionlistener
class ItemJSONViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var data : ItemJSON
}