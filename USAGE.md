# Usage

## Get Public Timeline

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
        
val timelines = Timelines(client)
val statuses: List<Status> = timelines.getPublic().execute()
```

__java__

```java
MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
Timelines timelines = new Timelines(client);

try {
  List<Status> statuses = timelines.getPublic(new Range()).execute();
  statuses.forEach(status->{
    System.out.println("=============");
    System.out.println(status.getAccount().getDisplayName());
    System.out.println(status.getContent());
  });
} catch (Mastodon4jRequestException e) {
  e.printStackTrace();
}
```

## Register App

If you want to access the auth required API, you need create client credential and get access token. see more [docs](https://docs.joinmastodon.org/methods/apps/)

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
val apps = Apps(client)
val appRegistration = apps.createApp(
	clientName = "client name",
	redirectUris = "urn:ietf:wg:oauth:2.0:oob",
	scope = Scope(Scope.Name.ALL),
	website = "https://sample.com"
).execute()
save(appRegistration) // appRegistration needs to be saved.
```

AppRegistration has client id and client secret.

__java__

```java
MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
Apps apps = new Apps(client);
try {
	AppRegistration registration = apps.createApp(
	    "mastodon4j-sample-app",
	    "urn:ietf:wg:oauth:2.0:oob",
	    new Scope(Scope.Name.ALL),
        "https://sample.com"
    ).execute();
    System.out.println("instance=" + registration.getInstanceName());
    System.out.println("client_id=" + registration.getClientId());
    System.out.println("client_secret=" + registration.getClientSecret());
} catch (Mastodon4jRequestException e) {
	int statusCode = e.getResponse().code();
	// error handling.
}
```

## OAuth login and get Access Token

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson()).build()
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
// 	accessToken needs to be saved.
```

## Get Home Timeline

__kotlin__

```kotlin
// Need parameter of accessToken
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson())
  .accessToken(accessToken)
  .build()

val statuses: List<Status> = timelines.getHome().execute()
```

## Get raw json

v0.0.7 or later

__kotlin__

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

__kotlin__

```kotlin
val client: MastodonClient = MastodonClient.Builder("mstdn.jp", OkHttpClient.Builder(), Gson())
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

__java__

```java
MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson())
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