package social.bigbone.v410

import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

@Suite
@SuiteDisplayName("Mastodon 4.1.0 Integration Test Suite")
@SelectClasses(
    V410InstanceMethodsIntegrationTest::class,
    V410StatusMethodsIntegrationTest::class,
    V410MediaMethodsIntegrationTest::class
)
class V410TestSuite
