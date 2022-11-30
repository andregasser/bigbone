package com.sys1yagi.mastodon4j.entity

import com.sys1yagi.mastodon4j.api.Scope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

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

    @Test(expected = IllegalArgumentException::class)
    fun validateDuplication() {
        Scope(Scope.Name.READ, Scope.Name.READ).validate()
    }
}
