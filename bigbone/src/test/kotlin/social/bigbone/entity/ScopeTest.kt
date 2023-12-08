package social.bigbone.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import social.bigbone.api.Scope

class ScopeTest {
    @Test
    fun toStringTest() {
        Scope(Scope.Name.READ).toString() shouldBeEqualTo "read"
        Scope(Scope.Name.READ, Scope.Name.WRITE).toString() shouldBeEqualTo "read write"
        Scope().toString() shouldBeEqualTo ""
    }

    @Test
    fun toStringTestWithDuplicates() {
        Scope(Scope.Name.READ, Scope.Name.READ).toString() shouldBeEqualTo "read"
        Scope(Scope.Name.READ, Scope.Name.WRITE, Scope.Name.PUSH, Scope.Name.WRITE).toString() shouldBeEqualTo "read write push"
    }
}
