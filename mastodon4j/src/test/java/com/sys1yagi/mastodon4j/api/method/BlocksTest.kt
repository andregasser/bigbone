package com.sys1yagi.mastodon4j.api.method

import com.sys1yagi.mastodon4j.api.exception.Mastodon4jRequestException
import com.sys1yagi.mastodon4j.testtool.MockClient
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class BlocksTest {
    @Test
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blocks = Blocks(client)
        val pageable = blocks.getBlocks().execute()
        val block = pageable.part.first()
        block.acct shouldBeEqualTo "test@test.com"
        block.displayName shouldBeEqualTo "test"
        block.userName shouldBeEqualTo "test"
    }

    @Test
    fun getBlocksException() {
        Assertions.assertThrows(Mastodon4jRequestException::class.java) {
            val client = MockClient.ioException()
            val blocks = Blocks(client)
            blocks.getBlocks().execute()
        }
    }
}
