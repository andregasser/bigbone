package social.bigbone

import com.github.dockerjava.api.model.HealthCheck
import org.slf4j.LoggerFactory
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.containers.output.Slf4jLogConsumer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import org.testcontainers.utility.MountableFile

@Testcontainers
open class V402BaseIntegrationTest {
    companion object {
        private const val POSTGRES_HOSTNAME = "postgres"
        private const val POSTGRES_IMAGE_VERSION = "postgres:14-alpine"
        private const val REDIS_HOSTNAME = "redis"
        private const val REDIS_IMAGE_VERSION = "redis:7-alpine"
        private const val ELASTIC_HOSTNAME = "elastic"
        private const val ELASTIC_IMAGE_VERSION = "docker.elastic.co/elasticsearch/elasticsearch:7.17.7"
        private const val MASTODON_WEB_HOSTNAME = "mastodon-web"
        private const val MASTODON_SIDEKIQ_HOSTNAME = "mastodon-sidekiq"
        private const val MASTODON_STREAMING_HOSTNAME = "mastodon-streaming"
        private const val MASTODON_IMAGE_VERSION = "tootsuite/mastodon:v4.0.2"
    }

    private val mastodonEnvMap = mapOf(
        "RAILS_ENV" to "production",
        "LOCAL_DOMAIN" to "mastodon.local",
        "REDIS_HOST" to REDIS_HOSTNAME,
        "MASTODON_ADMIN_USERNAME" to "foobar",
        "MASTODON_ADMIN_EMAIL" to "foo@bar.ch",
        "DB_HOST" to POSTGRES_HOSTNAME,
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
        "ES_HOST" to ELASTIC_HOSTNAME,
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

    private val postgresEnvMap = mapOf(
        "POSTGRES_DB" to "mastodon",
        "POSTGRES_USER" to "mastodon-user",
        "POSTGRES_PASSWORD" to "MySuperSecretPassword"
    )

    private val elasticEnvMap = mapOf(
        "ES_JAVA_OPTS" to "-Xms512m -Xmx512m",
        "xpack.license.self_generated.type" to "basic",
        "xpack.security.enabled" to "false",
        "xpack.watcher.enabled" to "false",
        "xpack.graph.enabled" to "false",
        "xpack.ml.enabled" to "false",
        "bootstrap.memory_lock" to "true",
        "cluster.name" to "mastodon-es",
        "discovery.type" to "single-node",
        "thread_pool.write.queue_size" to "1000",
        "ingest.geoip.downloader.enabled" to "false"
    )

    private var network: Network = Network.newNetwork()

    @Container
    val postgresContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse(POSTGRES_IMAGE_VERSION))
        .withNetwork(network)
        .withNetworkAliases(POSTGRES_HOSTNAME)
        .withEnv(postgresEnvMap)
        .withLogConsumer(Slf4jLogConsumer(LoggerFactory.getLogger(V402InstanceMethodsIntegrationTest::class.java)))
        .withCreateContainerCmdModifier {
            it.withHealthcheck(
                HealthCheck()
                    .withTest(listOf("CMD", "pg_isready", "-U", "mastodon-user", "-d", "mastodon"))
                    .withInterval(5 * 1000000000L)
                    .withTimeout(5 * 1000000000L)
            )
        }

    @Container
    val redisContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse(REDIS_IMAGE_VERSION))
        .withNetwork(network)
        .withNetworkAliases(REDIS_HOSTNAME)
        .withCommand("redis-server --save 60 1 --loglevel warning")
        .withLogConsumer(Slf4jLogConsumer(LoggerFactory.getLogger(V402InstanceMethodsIntegrationTest::class.java)))
        .withCreateContainerCmdModifier {
            it.withHealthcheck(
                HealthCheck()
                    .withTest(listOf("CMD", "redis-cli", "ping"))
                    .withInterval(5 * 1000000000L)
                    .withTimeout(5 * 1000000000L)
            )
        }

    @Container
    val elasticContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse(ELASTIC_IMAGE_VERSION))
        .withNetwork(network)
        .withNetworkAliases(ELASTIC_HOSTNAME)
        .withEnv(elasticEnvMap)
        .withLogConsumer(Slf4jLogConsumer(LoggerFactory.getLogger(V402InstanceMethodsIntegrationTest::class.java)))
        .withCreateContainerCmdModifier {
            it.withHealthcheck(
                HealthCheck()
                    .withTest(listOf("CMD-SHELL", "curl --silent --fail localhost:9200/_cluster/health || exit 1"))
                    .withInterval(5 * 1000000000L)
                    .withTimeout(5 * 1000000000L)
                    .withRetries(6)
            )
        }

    @Container
    val mastodonWebContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse(MASTODON_IMAGE_VERSION))
        .withNetwork(network)
        .withNetworkAliases(MASTODON_WEB_HOSTNAME)
        .withCopyFileToContainer(MountableFile.forClasspathResource("testcontainers/mastodon-4.0.2/mastodon-web/startup.sh"), "/startup.sh")
        .withCopyFileToContainer(MountableFile.forClasspathResource("testcontainers/mastodon-4.0.2/mastodon-web/provision.sh"), "/provision.sh")
        .withCopyFileToContainer(MountableFile.forClasspathResource("testcontainers/mastodon-4.0.2/mastodon-web/production.rb"), "/opt/mastodon/config/environments/production.rb")
        .withCommand("bash /startup.sh")
        .withEnv(mastodonEnvMap)
        .withExposedPorts(3000)
        .withLogConsumer(Slf4jLogConsumer(LoggerFactory.getLogger(V402InstanceMethodsIntegrationTest::class.java)))
        .withCreateContainerCmdModifier {
            it.withHealthcheck(
                HealthCheck()
                    .withTest(listOf("CMD-SHELL", "wget -q --spider --proxy=off localhost:3000/health || exit 1"))
                    .withInterval(5 * 1000000000L)
                    .withTimeout(5 * 1000000000L)
                    .withRetries(12)
            )
        }
        .dependsOn(postgresContainer)
        .dependsOn(redisContainer)
        .dependsOn(elasticContainer)

    @Container
    val mastodonSidekiqContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse(MASTODON_IMAGE_VERSION))
        .withNetwork(network)
        .withNetworkAliases(MASTODON_SIDEKIQ_HOSTNAME)
        .withCommand("bundle exec sidekiq")
        .withEnv(mastodonEnvMap)
        .withLogConsumer(Slf4jLogConsumer(LoggerFactory.getLogger(V402InstanceMethodsIntegrationTest::class.java)))
        .withCreateContainerCmdModifier {
            it.withHealthcheck(
                HealthCheck()
                    .withTest(listOf("CMD-SHELL", "\"ps aux | grep '[s]idekiq\\ 6' || false\""))
            )
        }
        .dependsOn(postgresContainer)
        .dependsOn(redisContainer)

    @Container
    val mastodonStreamingContainer: GenericContainer<*> = GenericContainer(DockerImageName.parse(MASTODON_IMAGE_VERSION))
        .withNetwork(network)
        .withNetworkAliases(MASTODON_STREAMING_HOSTNAME)
        .withCommand("node ./streaming")
        .withEnv(mastodonEnvMap)
        .withExposedPorts(4000)
        .withLogConsumer(Slf4jLogConsumer(LoggerFactory.getLogger(V402InstanceMethodsIntegrationTest::class.java)))
        .withCreateContainerCmdModifier {
            it.withHealthcheck(
                HealthCheck()
                    .withTest(listOf("CMD-SHELL", "wget -q --spider --proxy=off localhost:4000/api/v1/streaming/health || exit 1"))
            )
        }
        .dependsOn(postgresContainer)
        .dependsOn(redisContainer)
}
