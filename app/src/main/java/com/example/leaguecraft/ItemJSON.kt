package com.example.leaguecraft

import org.json.JSONArray
import org.json.JSONObject

class ItemJSON(JSONStringFile: String) {
    // root -> type, version, basic, data, groups, tree
    // Ignore basic, groups, and tree for the time being
    // data holds all the items and info
    lateinit var type: String
    lateinit var version: String
    val items = ArrayList<Item>()

    init {

        val initialParsing = JSONObject(JSONStringFile)
        type = initialParsing.getString("type")
        version = initialParsing.getString("version")
        val data = initialParsing.getJSONObject("data")
        val itemNumbers = data.names()

        for (i in 0 until itemNumbers.length()) {
            val tempJSON = data.getJSONObject(itemNumbers.getString(i))
            items.add(
                Item(
                    itemNumbers.getInt(i),
                    tempJSON.getString("name"),
                    tempJSON.getString("description"),
                    tempJSON.getString("colloq"),
                    tempJSON.getString("plaintext"),
                    processItemFrom(tempJSON),
                    processItemInto(tempJSON),
                    processItemImage(tempJSON.getJSONObject("image")),
                    processItemGold(tempJSON.getJSONObject("gold")),
                    processItemTags(tempJSON.getJSONArray("tags")),
                    processItemMaps(tempJSON.getJSONObject("maps")),

                )
            )
        }

    }

    private fun processItemFrom(fromString: JSONObject) : ArrayList<Int> {
        val fromArr = ArrayList<Int>()
        if (fromString.has("from")) {
            val jsonArr = fromString.getJSONArray("from")
            for (i in 0 until jsonArr.length()) {
                fromArr.add(jsonArr.get(i).toString().toInt())
            }
        }
        return fromArr
    }

    private fun processItemInto(intoString: JSONObject) : ArrayList<Int> {
        val intoArr = ArrayList<Int>()
        if (intoString.has("into")) {
            val jsonArr = intoString.getJSONArray("into")
            for (i in 0 until jsonArr.length()) {
                intoArr.add(jsonArr.get(i).toString().toInt())
            }
        }
        return intoArr
    }

    private fun processItemImage(imageString: JSONObject) : ItemImage {
        val itemImg = ItemImage(
            imageString.getString("full"),
            imageString.getString("sprite"),
            imageString.getString("group"),
            imageString.getInt("x"),
            imageString.getInt("y"),
            imageString.getInt("w"),
            imageString.getInt("h"),
        )
        return itemImg
    }

    private fun processItemGold(goldString: JSONObject) : ItemGold {
        val itemGold = ItemGold(
            goldString.getInt("base"),
            goldString.getBoolean("purchasable"),
            goldString.getInt("total"),
            goldString.getInt("sell")
        )
        return itemGold
    }

    private fun processItemTags(tagString: JSONArray) : ArrayList<String> {
        val tagArr = ArrayList<String>()
        for (i in 0 until tagString.length()) {
            tagArr.add(tagString.getString(i))
        }
        return tagArr
    }

    private fun processItemMaps(mapString: JSONObject) : ItemMaps {
        val itemMaps = ItemMaps(
            mapString.getBoolean("11"),
            mapString.getBoolean("12"),
            mapString.getBoolean("21"),
            mapString.getBoolean("22")
        )
        return itemMaps
    }
}

class Item (
    var num: Int,
    var name: String,
    var description: String,
    var colloq: String,
    var plaintext: String,
    var from: ArrayList<Int>,
    var into: ArrayList<Int>,
    var image: ItemImage,
    var gold: ItemGold,
    var tags: ArrayList<String>,
    var maps: ItemMaps,
    // var stats: ItemStats
    )  {}

class ItemImage (
    var full: String,
    var sprite: String,
    var group: String,
    var x: Int,
    var y: Int,
    var w: Int,
    var h: Int
    ) {}

class ItemGold (
    var base: Int,
    var purchaseable: Boolean,
    var total: Int,
    var sell: Int
    ) {}

class ItemMaps (
    var map11: Boolean,
    var map12: Boolean,
    var map21: Boolean,
    var map22: Boolean
    ) {}

class ItemStats  {
    lateinit var stats: ArrayList<Stat>

    class Stat(val statName: String, val statValue: Double) {}

    public fun processStats(statString: String) {
        // TODO: Parse string
        stats.add(Stat("Test", 1.0))
    }
}