import org.junit.ClassRule
import org.junit.jupiter.api.Test
import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.io.File
import java.time.Duration


@Testcontainers
class InstanceMethodsIntegrationTest {

    @Container
    var environment: DockerComposeContainer<*> = DockerComposeContainer(File("src/integrationTest/resources/compose-compose.yml"))
        .withExposedService("mastodon_web", 3000)
        .withExposedService("streaming", 4000)
        .waitingFor("mastodon_web", Wait.defaultWaitStrategy().withStartupTimeout(Duration.ofSeconds(60)))



    @Test
    fun getInstance() {
        val web = environment.getServiceHost("mastodon_web", 3000) + ":" + environment.getServicePort("mastodon_web", 3000)
        println(web)
    }
}
