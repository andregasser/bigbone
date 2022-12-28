package social.bigbone.rx

import org.junit.jupiter.api.Test
import social.bigbone.rx.testtool.MockClient

class RxTimelinesTest {

    @Test
    fun getPublic() {
        val client = MockClient.mock("public_timeline.json", "5", "40")
        val publicMethod = RxPublic(client)
        val subscriber = publicMethod.getLocalPublic().test()
        subscriber.assertNoErrors()
    }
}
