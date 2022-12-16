# First steps with Mastodon4j

Following this guide will allow you to:
* register your Mastodon application
* authenticate as a user
* read some of the most recent statuses available for that user
* post a status as that user

See the [official Mastodon docs](https://docs.joinmastodon.org/methods/apps/) for further API methods,
and this project's code for implementation details.

Code examples in this guide make use of the following variable for simplicity.
Its value should generally *not* be hard-coded in an actual application.

```kotlin
// hostname of a Mastodon server, e.g. "mastodon.social"
val instanceHostname:String = ...
```

Additionally, this guide uses the following values that should be replaced in your application:

```kotlin
// This out-of-band URI will display a generated auth code for you to copy and paste.
// The actual value used instead should be a URL that will be interpreted by your application.
redirectUris = "urn:ietf:wg:oauth:2.0:oob"

// This is equal to the full range of scopes currently supported by Mastodon4j.
// Instead of this, you should request as little as possible for your application.
scope = Scope()
```

## Registering an App

To access the API of a Mastodon server, we first need to create client credentials. 

__Kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder(instanceHostname, OkHttpClient.Builder(), Gson()).build()
val apps = Apps(client)
val appRegistration = apps.createApp(
	clientName = "mastodon4j-sample-app",
	redirectUris = "urn:ietf:wg:oauth:2.0:oob",
	scope = Scope(),
	website = "https://example.org/"
).execute()
```

__Java__

```java
MastodonClient client = new MastodonClient.Builder(instanceHostname, new OkHttpClient.Builder(), new Gson()).build();
Apps apps = new Apps(client);
try {
	AppRegistration appRegistration = apps.createApp(
	    "mastodon4j-sample-app",
	    "urn:ietf:wg:oauth:2.0:oob",
	    new Scope(),
	    "https://example.org/"
	).execute();
} catch (Mastodon4jRequestException e) {
	// error handling
}
```

## Login and get Access Token

Using client ID and secret available in appRegistration, we can now get an access token to act on behalf of a user.

__Kotlin__

```kotlin
// using client and appRegistration as defined above

val apps = Apps(client)
val url = apps.getOAuthUrl(appRegistration.clientId, Scope())

// This URL will have the following format:
// https://<instance_name>/oauth/authorize?client_id=<client_id>&redirect_uri=<redirect_uri>&response_type=code&scope=<scope> 

// Opening this URL will allow the user to perform an OAuth login, after which
// the previously defined redirect_uri will be called with an auth code in the query like this:
// <redirect_uri>?code=<auth_code>

val authCode:String = ... // retrieved from redirect_uri query parameter

// we use this auth code to get an access token
val accessToken = apps.getAccessToken(
	clientId = appRegistration.clientId,
	clientSecret = appRegistration.clientSecret,
	redirectUri = appRegistration.redirectUri,
	code = authCode, 
	grantType = "authorization_code"
)
```

## Get Home Timeline

Using the received access token, we can retrieve statuses from the user's home timeline and display them.

__Kotlin__

```kotlin
// creating a new client using previously received access token
val client: MastodonClient = MastodonClient.Builder(instanceHostname, OkHttpClient.Builder(), Gson())
	.accessToken(accessToken)
	.build()

// get 5 statuses
val result = Timelines(client).getHome(Range(limit = 5)).execute()

// sort and display these statuses
result.part.sortedBy { it.createdAt }.forEach {
	val byline = "by ${it.account?.displayName?:"Unknown"} on ${it.createdAt}"
	val stats = "Favourited: ${it.favouritesCount}, Reblogged: ${it.reblogsCount}"
	val content = it.content
	
	// use these and more as necessary
}
```

## Post a status

We can also post a status as the user.

__Kotlin__

```kotlin
try {
	// using previously defined client with access token
	val status = Statuses(client).postStatus(
		status = "Hello World! #HelloWorld",
		inReplyToId = null,
		mediaIds = null,
		sensitive = false,
		spoilerText = null,
		visibility = Status.Visibility.Unlisted
	)
	status.execute()
} catch (e: Exception) {
	// error handling
}
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
            val public = Public(client)
            public.getLocalPublic().doOnJson {
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
            Public publicMethod = new Public(client);
            publicMethod.getLocalPublic().doOnJson(System.out::println).execute();
        } catch (IOException | Mastodon4jRequestException e) {
            e.printStackTrace();
        }
    }
}
```

## Streaming API

v1.0.0 or later

__Kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder(instanceHostname, OkHttpClient.Builder(), Gson())
  .accessToken(accessToken)
  .useStreamingApi()
  .build()

val handler = object : Handler {
  override fun onStatus(status: Status) {
    println(status.content)
  }
  override fun onNotification(notification: Notification) {/* no op */}
  override fun onDelete(id: Long) {/* no op */}
}

val streaming = Streaming(client)
try {
  val shutdownable = streaming.localPublic(handler)
  Thread.sleep(10000L)
  shutdownable.shutdown()
} catch(e: Mastodon4jRequestException) {
  e.printStackTrace()
}
```

__Java__

```java
MastodonClient client = new MastodonClient.Builder(instanceHostname, new OkHttpClient.Builder(), new Gson())
        .accessToken(accessToken)
        .useStreamingApi()
        .build();
Handler handler = new Handler() {
    @Override
    public void onStatus(@NotNull Status status) {
        System.out.println(status.getContent());
    }

    @Override
    public void onNotification(@NotNull Notification notification) {/* no op */}
    @Override
    public void onDelete(long id) {/* no op */}
};

Streaming streaming = new Streaming(client);
try {
    Shutdownable shutdownable = streaming.localPublic(handler);
    Thread.sleep(10000L);
    shutdownable.shutdown();
} catch (Exception e) {
    e.printStackTrace();
}
```
