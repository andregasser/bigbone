package social.bigbone

import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

@Suite
@SuiteDisplayName("Mastodon 4.0.2 Integration Test Suite")
@SelectClasses(
    V402InstanceMethodsIntegrationTest::class,
    V402StatusMethodsIntegrationTest::class
)
class V402TestSuite {
}
