package social.bigbone.rx

import org.junit.jupiter.api.Test
import social.bigbone.api.Pageable
import social.bigbone.api.Range
import social.bigbone.api.entity.Account
import social.bigbone.rx.testtool.MockClient

class RxEndorsementMethodsTest {

    @Test
    fun `Given a client returning success, when getting endorsements, then emit list of endorsements`() {
        val client = MockClient.mock(
            jsonName = "endorsements_view_success.json"
        )
        val endorsements = RxEndorsementMethods(client)

        with(endorsements.getEndorsements().test()) {
            assertNoErrors()
            assertComplete()

            assertValueCount(1)
            assertValue { endorsements: Pageable<Account> -> endorsements.part.size == 2 }

            dispose()
        }
    }

    @Test
    fun `Given a client returning success, when getting endorsements with limit of 90, then emit error`() {
        val client = MockClient.mock(
            jsonName = "endorsements_view_success.json"
        )
        val endorsements = RxEndorsementMethods(client)

        with(endorsements.getEndorsements(range = Range(limit = 90)).test()) {
            assertNoValues()
            assertNotComplete()

            assertError { error: Throwable ->
                error is IllegalArgumentException &&
                    error.message == "limit defined in Range must not be higher than 80 but was 90"
            }

            dispose()
        }
    }
}
