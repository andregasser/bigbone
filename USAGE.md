# First steps with Mastodon4j

Following this guide will allow you to:
* register your Mastodon app
* authenticate as a user
* read some of the most recent statuses available for that user
* post a status as that user

See the [official Mastodon docs](https://docs.joinmastodon.org/methods/apps/) for further API methods,
and this project's code for implementation details.

Code examples in this file make use of the following variables for simplicity.
Their values should generally *not* be hard-coded in an actual app.

```kotlin
val instanceHostname:String = ... // hostname of a Mastodon server, e.g. "mastodon.social"
val userMail:String = ... // mail address of an account
val userPassword:String = ... // password of an account
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

### Using available user credentials

__Kotlin__

```kotlin
// using client and appRegistration as defined above
	
val apps = Apps(client)
val accessToken = apps.postUserNameAndPassword(
	appRegistration.clientId,
	appRegistration.clientSecret,
	Scope(),
	userMail,
	userPassword
).execute()
```

### Using OAuth

__Kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder(instanceHostname, OkHttpClient.Builder(), Gson()).build()
val clientId = appRegistration.clientId
val apps = Apps(client)

val url = apps.getOAuthUrl(clientId, Scope(Scope.Name.ALL))
// url like bellow
// https://:instance_name/oauth/authorize?client_id=:client_id&redirect_uri=:redirect_uri&response_type=code&scope=read 
// open url and OAuth login and get auth code

val authCode = //...
val clientSecret = appRegistration.clientSecret
val redirectUri = appRegistration.redirectUri
val accessToken = apps.getAccessToken(
			clientId,
			clientSecret,
			redirectUri,
			authCode,
			"authorization_code"
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

## Get raw json

v0.0.7 or later

__Kotlin__

```kotlin
val client = //...
val publicMethod = Public(client)

publicMethod.getLocalPublic()
  .doOnJson { jsonString -> 
    // You can get raw json for each element.
    println(jsonString)
  }
  .execute() 
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
