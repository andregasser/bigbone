# First steps with BigBone

Following this guide will allow you to:
* register your Mastodon application
* authenticate as a user
* read some of the most recent statuses available for that user
* post a status as that user

See the [official Mastodon docs](https://docs.joinmastodon.org/methods/apps/) for further API methods, and this project's code for implementation details.

Code examples in this guide make use of the following variable for simplicity. Its value should generally *not* be hard-coded in an actual application.

```kotlin
// hostname of a Mastodon server, e.g. "mastodon.social"
val instanceHostname:String = ...
```

Additionally, this guide uses the following values that should be replaced in your application:

```kotlin
// This out-of-band URI will display a generated auth code for you to copy and paste.
// The actual value used instead should be a URL that will be interpreted by your application.
redirectUris = "urn:ietf:wg:oauth:2.0:oob"

// This is equal to the full range of non-admin scopes currently supported by BigBone.
// Instead of this, you should request as little as possible for your application.
fullScope = Scope(Scope.READ.ALL, Scope.WRITE.ALL, Scope.PUSH.ALL)
```

## Registering an App

To access the API of a Mastodon server, we first need to create client credentials.


> [!IMPORTANT]
> When building an instance of the `MastodonClient`, it may throw a `BigBoneClientInstantiationException` if we could
> not
> successfully retrieve information about an instance you provide. The stacktrace of that exception should either help you
> find a solution, or give you necessary information you can provide to us, e.g. via the GitHub issues, to help you find
> one.

__Kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder(instanceHostname).build()
val appRegistration = client.apps.createApp(
	clientName = "bigbone-sample-app",
	redirectUris = "urn:ietf:wg:oauth:2.0:oob",
	scope = fullScope,
	website = "https://example.org/"
).execute()
```

__Java__

```java
try {
    MastodonClient client=new MastodonClient.Builder(instanceHostname).build();
} catch (BigBoneClientInstantiationException e){
    // error handling
}

try {
    AppRegistration appRegistration=client.apps().createApp(
        "bigbone-sample-app",
        "urn:ietf:wg:oauth:2.0:oob",
        fullScope,
        "https://example.org/"
    ).execute();
} catch (BigBoneRequestException e) {
	// error handling
}
```

## Login and get Access Token

Using client ID and secret available in appRegistration, we can now get an access token to act on behalf of a user.

__Kotlin__

```kotlin
// using client and appRegistration as defined above
val url = client.oauth.getOAuthUrl(appRegistration.clientId, Scope())

// This URL will have the following format:
// https://<instance_name>/oauth/authorize?client_id=<client_id>&redirect_uri=<redirect_uri>&response_type=code&scope=<scope> 

// Opening this URL will allow the user to perform an OAuth login, after which
// the previously defined redirect_uri will be called with an auth code in the query like this:
// <redirect_uri>?code=<auth_code>

val authCode:String = ... // retrieved from redirect_uri query parameter

// we use this auth code to get an access token
val accessToken = client.oauth.getAccessToken(
	clientId = appRegistration.clientId,
	clientSecret = appRegistration.clientSecret,
	redirectUri = appRegistration.redirectUri,
	code = authCode, 
	grantType = "authorization_code"
)
```

If your app is a command line application and is not meant for a human user to login, you can use the access token
generated from your Mastodon account. You find it under "Development > Your application > Access Token".

## Get Home Timeline

Using the received access token, we can retrieve statuses from the user's home timeline and display them.

__Kotlin__

```kotlin
// creating a new client using previously received access token
val client: MastodonClient = MastodonClient.Builder(instanceHostname)
	.accessToken(accessToken)
	.build()

// get 5 statuses
val result = client.timelines.getHome(Range(limit = 5)).execute()

// sort and display these statuses
result.part.sortedBy { it.createdAt }.forEach {
	val byline = "by ${it.account?.displayName?:"Unknown"} on ${it.createdAt}"
	val stats = "Favourited: ${it.favouritesCount}, Reblogged: ${it.reblogsCount}"
	val content = it.content
	
	// use these and more as necessary
}
```

__Java__

```java
MastodonClient client = new MastodonClient.Builder(instanceHostname).accessToken(accessToken).build();
Pageable<Status> timeline = client.timelines().getHomeTimeline(new Range(null, null, 5)).execute();

timeline.getPart().forEach(status -> {
    System.out.println(status.getContent());
});
```

## Post a status

We can also post a status as the user.

__Kotlin__

```kotlin
try {
	// using previously defined client with access token
	val status = client.statuses.postStatus(
		status = "Hello World! #HelloWorld",
		visibility = Status.Visibility.Unlisted
		// additional optional parameters exist
	)
	status.execute()
} catch (e: Exception) {
	// error handling
}
```

__Java__

```java
MastodonRequest<Status> request = client.statuses()
                .postStatus("Hello World", Status.Visibility.Unlisted);
        Status status = request.execute();
```

## Get Raw JSON
The examples in this section demonstrate, how raw JSON responses can be processed by using the `doOnJson` method. `doOnJson`is invoked for every single JSON object that is returned.

__Kotlin__

```kotlin
object GetRawJson {
    @JvmStatic
    fun main(args: Array<String>) {
        val instanceName = args[0]
        val credentialFilePath = args[1]
        val client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath)
        runBlocking {
            client.timelines.getPublicTimeline().doOnJson {
                println(it)
            }.execute()
        }
    }
}
```

__Java__

```java
public class GetRawJson {
    public static void main(String[] args) {
        try {
            String instanceName = args[0];
            String credentialFilePath = args[1];
            MastodonClient client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath, false);
            client.timelines().getPublicTimeline().doOnJson(System.out::println).execute();
        } catch (IOException | BigBoneRequestException e) {
            e.printStackTrace();
        }
    }
}
```

## Streaming API

__Kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder(instanceHostname)
  .accessToken(accessToken)
  .useStreamingApi()
  .build()

client.streaming.federatedPublic(
    onlyMedia = false,
    callback = { event: WebSocketEvent ->
        println(event)
    }
).use {
    Thread.sleep(15_000L)
}
```

__Java__

```java
final MastodonClient client = new MastodonClient.Builder(instanceHostname)
        .accessToken(accessToken)
        .useStreamingApi()
        .build();

// Start federated timeline streaming and stop after 20 seconds
try (Closeable ignored = client.streaming().federatedPublic(false, System.out::println)) {
        Thread.sleep(20_000L);
}
```
