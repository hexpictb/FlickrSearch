package com.aezhkov.flickrsearch.data.storage

interface PreferenceStorage {

    fun saveList(key: String, list: List<String>)

    fun getList(key: String): List<String>
}