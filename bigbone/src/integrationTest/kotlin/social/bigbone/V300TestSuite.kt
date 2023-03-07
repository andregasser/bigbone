package social.bigbone

import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

@Suite
@SuiteDisplayName("Mastodon 3.0.0 Integration Test Suite")
@SelectClasses(
    V300InstanceMethodsIntegrationTest::class,
    V300StatusMethodsIntegrationTest::class
)
class V300TestSuite {
}
