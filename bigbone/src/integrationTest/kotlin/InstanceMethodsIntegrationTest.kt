import com.github.dockerjava.api.model.HealthCheck
import org.junit.jupiter.api.Test
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import social.bigbone.MastodonClient


@Testcontainers
class InstanceMethodsIntegrationTest {

    private var network: Network = Network.newNetwork()

    private fun getMastodonEnv(redisHostname: String, postgresHostname: String, esHostname: String): Map<String, String> =
        mapOf(
            "RAILS_ENV" to "production",
            "LOCAL_DOMAIN" to "mastodon.local",
            "REDIS_HOST" to redisHostname,
            "DB_HOST" to postgresHostname,
            "DB_NAME" to "mastodon",
            "DB_USER" to "mastodon-user",
            "DB_PASS" to "MySuperSecretPassword",
            "SECRET_KEY_BASE" to "RandomValue",
            "OTP_SECRET" to "AnotherRandomValue",
            "SMTP_SERVER" to "MySmtpServer",
            "SMTP_PORT" to "587",
            "SMTP_LOGIN" to "MySmtpLogin",
            "SMTP_PASSWORD" to "MySmtpPassword",
            "SMTP_FROM_ADDRESS" to "foo@bar.ch",
            "ES_ENABLED" to "true",
            "ES_HOST" to esHostname,
            "VAPID_PRIVATE_KEY" to "OdpiFjiFZmmZZvC5-GBWWrUAiTGHQ4GvHT1vxIz5vww=",
            "VAPID_PUBLIC_KEY" to "BGw1VsPbWjP6umtRSmdgoz881u-eB8MIw8tPCxJREC6yhwWsqpcWziPXoIVussPj4gcvSyQNFv8d_TEBBdGcRUQ=",
            "S3_ENABLED" to "false",
            "S3_PROTOCOL" to "https",
            "S3_ENDPOINT" to "https://url.of.s3.endpoint",
            "S3_HOSTNAME" to "url.of.s3.endpoint",
            "S3_REGION" to "us-east-1",
            "S3_BUCKET" to "mastodon",
            "AWS_ACCESS_KEY_ID" to "S3AccessKeyId",
            "AWS_SECRET_ACCESS_KEY" to "S3AccessKey",
            "S3_ALIAS_HOST" to "static.bar.ch"
        )

    @Container
    val postgresContainer = GenericContainer(DockerImageName.parse("postgres:alpine"))
        .withNetwork(network)
        .withNetworkAliases("postgres")
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
        .withNetwork(network)
        .withNetworkAliases("redis")
        .withCommand("redis-server --save 60 1 --loglevel warning")
        .withCreateContainerCmdModifier {
            it.withHealthcheck(HealthCheck()
                .withTest(listOf("CMD", "redis-cli", "ping"))
                .withInterval(5 * 1000000000)
                .withTimeout(5 * 1000000000)
            )
        }

    @Container
    val elasticContainer = GenericContainer(DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch:7.17.7"))
        .withNetwork(network)
        .withNetworkAliases("elastic")
        //.withCommand("redis-server --save 60 1 --loglevel warning")
        .withEnv("ES_JAVA_OPTS", "-Xms512m -Xmx512m")
        .withEnv("xpack.license.self_generated.type", "basic")
        .withEnv("xpack.security.enabled", "false")
        .withEnv("xpack.watcher.enabled", "false")
        .withEnv("xpack.graph.enabled", "false")
        .withEnv("xpack.ml.enabled", "false")
        .withEnv("bootstrap.memory_lock", "true")
        .withEnv("cluster.name", "mastodon-es")
        .withEnv("discovery.type", "single-node")
        .withEnv("thread_pool.write.queue_size", "1000")
        .withEnv("ingest.geoip.downloader.enabled", "false")
        .withCreateContainerCmdModifier {
            it.withHealthcheck(HealthCheck()
                .withTest(listOf("CMD-SHELL", "curl --silent --fail localhost:9200/_cluster/health || exit 1"))
                .withInterval(5 * 1000000000)
                .withTimeout(5 * 1000000000)
                .withRetries(6)
            )
        }

    @Container
    val mastodonWebContainer = GenericContainer(DockerImageName.parse("tootsuite/mastodon"))
        .withNetwork(network)
        .withNetworkAliases("mastodon-web")
        .withCommand("bash -c /provision.sh; rm -f /mastodon/tmp/pids/server.pid; bundle exec rails s -p 3000")
        .withEnv(getMastodonEnv("redis", "postgres", "elastic"))
        .withEnv("MASTODON_ADMIN_USERNAME", "foobar")
        .withEnv("MASTODON_ADMIN_EMAIL", "foo@bar.ch")
        .withExposedPorts(3000)
        .withClasspathResourceMapping("provision.sh", "/provision.sh", BindMode.READ_WRITE)
        .withClasspathResourceMapping("production.rb", "/opt/mastodon/config/environments/production.rb", BindMode.READ_WRITE)
//        .withCreateContainerCmdModifier {
//            it.withHealthcheck(HealthCheck()
//                .withTest(listOf("CMD-SHELL", "wget -q --spider --proxy=off localhost:3000/health || exit 1"))
//                .withInterval(5 * 1000000000)
//                .withTimeout(5 * 1000000000)
//                .withRetries(12)
//            )
//        }
        .dependsOn(postgresContainer)
        .dependsOn(redisContainer)
        .dependsOn(elasticContainer)

//    @Container
//    val mastodonSidekiqContainer = GenericContainer(DockerImageName.parse("tootsuite/mastodon"))
//        .withNetwork(network)
//        .withNetworkAliases("mastodon-sidekiq")
//        .withCommand("bundle exec sidekiq")
//        .withEnv(getMastodonEnv(redisContainer.host, postgresContainer.host, "mastodon-es"))
//        .withCreateContainerCmdModifier {
//            it.withHealthcheck(HealthCheck()
//                .withTest(listOf("CMD-SHELL", "\"ps aux | grep '[s]idekiq\\ 6' || false\""))
//            )
//        }
//        .dependsOn(mastodonWebContainer)

//    @Container
//    val mastodonStreamingContainer = GenericContainer(DockerImageName.parse("tootsuite/mastodon"))
//        .withNetwork(network)
//        .withNetworkAliases("mastodon-streaming")
//        .withCommand("node ./streaming")
//        .withEnv(getMastodonEnv(redisContainer.host, postgresContainer.host, "mastodon-es"))
//        .withExposedPorts(4000)
//        .withCreateContainerCmdModifier {
//            it.withHealthcheck(HealthCheck()
//                .withTest(listOf("CMD-SHELL", "wget -q --spider --proxy=off localhost:4000/api/v1/streaming/health || exit 1"))
//            )
//        }
//        .dependsOn(mastodonWebContainer)

    @Test
    fun getInstance() {
        val mastodonInstance = "mastodon-web:" + mastodonWebContainer.getMappedPort(3000)
        println("host = " + mastodonInstance)
        val client = MastodonClient.Builder(mastodonInstance).build()
        val instance = client.instances.getInstance()
        println(instance)
    }
}
