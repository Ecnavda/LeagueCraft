package com.example.leaguecraft

import android.content.res.AssetManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch

class ItemBuildActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_build)

        // NOTE: Coroutine Tutorial
        // https://www.raywenderlich.com/1423941-kotlin-couroutines-tutorial-for-android-getting-started
        val cs = CoroutineScope(Dispatchers.IO)
        cs.launch {
            loadItemJSON()
        }
        Toast.makeText(this, "Finished Loading File", Toast.LENGTH_SHORT).show()
    }

    private suspend fun loadItemJSON() {
        val assMan: AssetManager = this.assets
        assMan.open("item.json").use {
            val unparsedJSON = it.bufferedReader().readLine()
            val itemfile = ItemJSON(unparsedJSON)
            println(itemfile.version)

        }
    }
}