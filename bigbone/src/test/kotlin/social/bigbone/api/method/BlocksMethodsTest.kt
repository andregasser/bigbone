package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class BlocksMethodsTest {
    @Test
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blocksMethods = BlocksMethods(client)
        val pageable = blocksMethods.getBlocks().execute()
        val block = pageable.part.first()
        block.acct shouldBeEqualTo "test@test.com"
        block.displayName shouldBeEqualTo "test"
        block.userName shouldBeEqualTo "test"
    }

    @Test
    fun getBlocksException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val blocksMethods = BlocksMethods(client)
            blocksMethods.getBlocks().execute()
        }
    }
}
