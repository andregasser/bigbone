package social.bigbone.api.method

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient

class BlockMethodsTest {
    @Test
    fun getBlocks() {
        val client = MockClient.mock("blocks.json")

        val blockMethods = BlockMethods(client)
        val pageable = blockMethods.getBlocks().execute()
        val block = pageable.part.first()
        block.acct shouldBeEqualTo "test@test.com"
        block.displayName shouldBeEqualTo "test"
        block.username shouldBeEqualTo "test"
    }

    @Test
    fun getBlocksException() {
        Assertions.assertThrows(BigBoneRequestException::class.java) {
            val client = MockClient.ioException()
            val blockMethods = BlockMethods(client)
            blockMethods.getBlocks().execute()
        }
    }
}
