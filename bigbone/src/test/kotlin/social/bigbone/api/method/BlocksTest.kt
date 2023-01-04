package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigboneRequestException
import social.bigbone.testtool.MockClient

class BlocksTest {
    @Test
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blocks = Blocks(client)
        val pageable = blocks.getBlocks().execute()
        val block = pageable.part.first()
        block.acct shouldBeEqualTo "bombadil@example.org"
        block.displayName shouldBeEqualTo "Tom Bombadil"
        block.userName shouldBeEqualTo "bombadil"
    }

    @Test
    fun getBlocksException() {
        Assertions.assertThrows(BigboneRequestException::class.java) {
            val client = MockClient.ioException()
            val blocks = Blocks(client)
            blocks.getBlocks().execute()
        }
    }
}
