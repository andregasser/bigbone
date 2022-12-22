package social.bigbone.entity

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import social.bigbone.api.Scope

class ScopeTest {
    @Test
    fun toStringTest() {
        Scope(Scope.Name.READ).toString() shouldBeEqualTo "read"
        Scope(Scope.Name.READ, Scope.Name.WRITE).toString() shouldBeEqualTo "read write"
        Scope(Scope.Name.ALL).toString() shouldBeEqualTo "read write follow"
        Scope().toString() shouldBeEqualTo "read write follow"
    }

    @Test
    fun validateSuccess() {
        Scope().validate()
        Scope(Scope.Name.READ).validate()
        Scope(Scope.Name.READ, Scope.Name.WRITE).validate()
        Scope(Scope.Name.ALL).validate()
    }

    @Test
    fun validateDuplication() {
        Assertions.assertThrows(java.lang.IllegalArgumentException::class.java) {
            Scope(Scope.Name.READ, Scope.Name.READ).validate()
        }
    }
}
