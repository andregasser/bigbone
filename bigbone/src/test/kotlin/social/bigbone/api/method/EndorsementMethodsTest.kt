package social.bigbone.api.method

import org.amshove.kluent.invoking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import social.bigbone.PrecisionDateTime.ExactTime
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.api.exception.BigBoneRequestException
import social.bigbone.testtool.MockClient
import java.time.Instant

class EndorsementMethodsTest {

    @Test
    fun `Given a client returning success, when getting endorsements, then expect values of response`() {
        val client = MockClient.mock("endorsements_view_success.json")

        val endorsementMethods = EndorsementMethods(client)
        val endorsements: Pageable<Account> = endorsementMethods.getEndorsements().execute()

        endorsements.part.size shouldBeEqualTo 2
        val firstEndorsement = endorsements.part[0]
        firstEndorsement.id shouldBeEqualTo "952529"
        firstEndorsement.isLocked shouldBeEqualTo true
        firstEndorsement.isBot shouldBeEqualTo false
        firstEndorsement.statusesCount shouldBeEqualTo 955
        firstEndorsement.lastStatusAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T07:05:50.682Z"))
        firstEndorsement.emojis.isEmpty() shouldBeEqualTo true

        val secondEndorsement = endorsements.part[1]
        secondEndorsement.id shouldBeEqualTo "832844"
        secondEndorsement.isLocked shouldBeEqualTo true
        secondEndorsement.isBot shouldBeEqualTo false
        secondEndorsement.statusesCount shouldBeEqualTo 5906
        secondEndorsement.lastStatusAt shouldBeEqualTo ExactTime(Instant.parse("2019-11-23T05:23:47.911Z"))
        secondEndorsement.emojis.isEmpty() shouldBeEqualTo false
    }

    @Test
    fun `Given a client returning success, when getting endorsements with a limit of 90, then throw IllegalArgumentException`() {
        val client = MockClient.mock("endorsements_view_success.json")

        invoking {
            EndorsementMethods(client).getEndorsements(
                range = Range(limit = 90)
            ).execute()
        } shouldThrow IllegalArgumentException::class withMessage "limit defined in Range must not be higher than 80 but was 90"
    }

    @Test
    fun `Given a client returning unauthorized, when getting featured_tags, then propagate error`() {
        val client = MockClient.failWithResponse(
            responseJsonAssetPath = "error_401_unauthorized.json",
            responseCode = 401,
            message = "Unauthorized"
        )

        invoking {
            EndorsementMethods(client).getEndorsements().execute()
        } shouldThrow BigBoneRequestException::class withMessage "Unauthorized"
    }
}
