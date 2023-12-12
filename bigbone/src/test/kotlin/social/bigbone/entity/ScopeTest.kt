package social.bigbone.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.api.Scope

class ScopeTest {
    @Test
    fun toStringTest() {
        Scope(Scope.READ.ALL).toString() shouldBeEqualTo "read"
        Scope(Scope.READ.ALL, Scope.WRITE.ALL).toString() shouldBeEqualTo "read write"
        Scope().toString() shouldBeEqualTo ""
    }

    @Test
    fun toStringTestWithDuplicates() {
        Scope(Scope.READ.ALL, Scope.READ.ALL).toString() shouldBeEqualTo "read"
        Scope(Scope.READ.ALL, Scope.WRITE.ALL, Scope.PUSH.ALL, Scope.WRITE.ALL).toString() shouldBeEqualTo "read write push"
    }
}
