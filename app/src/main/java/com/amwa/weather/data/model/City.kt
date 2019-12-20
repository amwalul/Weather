package com.amwa.weather.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

@Entity
data class City(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = ""
) {
    constructor(jsonObject: JSONObject) : this(jsonObject.getInt("id"), jsonObject.getString("name"))
}