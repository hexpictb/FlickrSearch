package com.aezhkov.flickrsearch.data.storage

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject
import com.google.gson.reflect.TypeToken

private const val LIST_STORAGE = "listStorage"

class PreferenceStorageImpl
@Inject constructor(
    context: Context
) : PreferenceStorage {

    private val preference = context.getSharedPreferences(LIST_STORAGE, Context.MODE_PRIVATE)

    override fun saveList(key: String, list: List<String>) {
        val toJson = Gson().toJson(list)
        preference.edit().putString(key, toJson).apply()
    }

    override fun getList(key: String): List<String> {
        return preference.getString(key, null)?.let {
            val listType = object : TypeToken<List<String>>() {}.type

            Gson().fromJson<List<String>>(it, listType)
        } ?: emptyList()
    }
}