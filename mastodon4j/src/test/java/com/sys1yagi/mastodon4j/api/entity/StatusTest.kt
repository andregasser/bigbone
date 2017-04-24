package com.sys1yagi.mastodon4j.api.entity

import com.google.gson.Gson
import com.sys1yagi.mastodon4j.testtool.AssetsUtil
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class StatusTest {

    @Test
    fun deserialize() {
        val json = AssetsUtil.readFromAssets("status.json")
        val status: Status = Gson().fromJson(json, Status::class.java)
        status.id shouldEqualTo 172429L
        status.visibility shouldEqualTo Status.Visibility.Public.value
        status.content shouldEqualTo "Test Status"
        val account = status.account
        requireNotNull(account)
        account?.let {
            it.id shouldEqualTo 14476L
        }
        status.isReblogged shouldEqualTo false
    }
}
