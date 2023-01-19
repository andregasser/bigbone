import com.github.dockerjava.api.model.HealthCheck
import org.junit.jupiter.api.Test
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.Arrays


@Testcontainers
class InstanceMethodsIntegrationTest {

//    @Container
//    var environment: DockerComposeContainer<*> = DockerComposeContainer(File("src/integrationTest/resources/compose-compose.yml"))
//        .withExposedService("mastodon_web", 3000)
//        .withExposedService("streaming", 4000)
//        .waitingFor("mastodon_web", Wait.defaultWaitStrategy().withStartupTimeout(Duration.ofSeconds(60)))

    @Container
    val postgresContainer = GenericContainer(DockerImageName.parse("postgres:alpine"))
        .withEnv("POSTGRES_DB", "mastodon")
        .withEnv("POSTGRES_USER", "mastodon-user")
        .withEnv("POSTGRES_PASSWORD", "MySuperSecretPassword")
        .withCreateContainerCmdModifier {
            it.withHealthcheck(HealthCheck()
                .withTest(listOf("CMD", "pg_isready", "-U", "mastodon-user", "-d", "mastodon"))
                .withInterval(5 * 1000000000)
                .withTimeout(5 * 1000000000)
            )
        }

    @Container
    val redisContainer = GenericContainer(DockerImageName.parse("redis:alpine"))
        .withCommand("redis-server --save 60 1 --loglevel warning")
        .withCreateContainerCmdModifier {
            it.withHealthcheck(HealthCheck()
                .withTest(listOf("CMD", "redis-cli", "ping"))
                .withInterval(5 * 1000000000)
                .withTimeout(5 * 1000000000)
            )
        }

    @Container
    val mastodonWebContaier = GenericContainer(DockerImageName.parse("tootsuite/mastodon"))
        .withCommand("bash -c \"/provision.sh; rm -f /mastodon/tmp/pids/server.pid; bundle exec rails s -p 3000\"")
        .withEnv("MASTODON_ADMIN_USERNAME", "foobar")
        .withEnv("MASTODON_ADMIN_EMAIL", "foo@bar.ch")
        // Add more envs here
        .withExposedPorts(3000)
        .withClasspathResourceMapping("provision.sh", "/provision.sh", BindMode.READ_WRITE)
        .withClasspathResourceMapping("production.rb", "/opt/mastodon/config/environments/production.rb", BindMode.READ_WRITE)
        .withCreateContainerCmdModifier {
            it.withHealthcheck(HealthCheck()
                .withTest(listOf("CMD-SHELL", "wget", "-q", "--spider", "--proxy=off", "localhost:3000/health", "||", "exit 1"))
                .withInterval(5 * 1000000000)
                .withTimeout(5 * 1000000000)
                .withRetries(12)
            )
        }
        .dependsOn(postgresContainer)
        .dependsOn(redisContainer)

    @Test
    fun getInstance() {
        //val web = environment.getServiceHost("mastodon_web", 3000) + ":" + environment.getServicePort("mastodon_web", 3000)
        //println(web)
        postgresContainer.isRunning();
        println(postgresContainer.getHost())
        //println(postgresContainer.getFirstMappedPort())
    }
}
