package social.bigbone

import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

@Suite
@SuiteDisplayName("Mastodon 4.1.0 Integration Test Suite")
@SelectClasses(
    V410InstanceMethodsIntegrationTest::class,
    V410StatusMethodsIntegrationTest::class
)
class V410TestSuite {
}
