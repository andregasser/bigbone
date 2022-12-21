package social.bigbone.sample;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import social.bigbone.MastodonClient;
import social.bigbone.api.Scope;
import social.bigbone.api.entity.auth.AccessToken;
import social.bigbone.api.entity.auth.AppRegistration;
import social.bigbone.api.exception.Mastodon4jRequestException;
import social.bigbone.api.method.Apps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

final class Authenticator {
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String ACCESS_TOKEN = "access_token";

    private Authenticator() {}

    static MastodonClient appRegistrationIfNeeded(String instanceName, String credentialFilePath, boolean useStreaming) throws IOException, Mastodon4jRequestException {
        File file = new File(credentialFilePath);
        if (!file.exists()) {
            System.out.println("create $credentialFilePath.");
            file.createNewFile();
        }
        Properties properties = new Properties();
        System.out.println("load $credentialFilePath.");
        properties.load(Files.newInputStream(file.toPath()));
        if (properties.get(CLIENT_ID) == null) {
            System.out.println("try app registration...");
            AppRegistration appRegistration = appRegistration(instanceName);
            properties.put(CLIENT_ID, appRegistration.getClientId());
            properties.put(CLIENT_SECRET, appRegistration.getClientSecret());
            properties.store(Files.newOutputStream(file.toPath()), "app registration");
        } else {
            System.out.println("app registration found...");
        }
        String clientId = properties.get(CLIENT_ID).toString(); //?.toString() ?: error("client id not found")
        String clientSecret = properties.get(CLIENT_SECRET).toString(); //]?.toString() ?: error("client secret not found")
        if (properties.get(ACCESS_TOKEN) == null) {
            System.out.println("get access token for $instanceName...");
            System.out.println("please input your email...");
            String email = System.console().readLine();
            System.out.println("please input your password...");
            String pass = System.console().readLine();
            AccessToken accessToken = getAccessToken(instanceName, clientId, clientSecret, email, pass);
            properties.put(ACCESS_TOKEN, accessToken.getAccessToken());
            properties.store(Files.newOutputStream(file.toPath()), "app registration");
        } else {
            System.out.println("access token found...");
        }
        MastodonClient.Builder builder = new MastodonClient.Builder(instanceName, new OkHttpClient.Builder(), new Gson())
            .accessToken(properties.get(ACCESS_TOKEN).toString());
        if (useStreaming) {
            builder.useStreamingApi();
        }
        return builder.build();
    }

    private static AccessToken getAccessToken(String instanceName, String clientId, String clientSecret, String email, String password) throws Mastodon4jRequestException {
        MastodonClient client = new MastodonClient.Builder(instanceName, new OkHttpClient.Builder(), new Gson()).build();
        Apps apps = new Apps(client);
        return apps.postUserNameAndPassword(clientId, clientSecret, new Scope(), email, password).execute();
    }

    private static AppRegistration appRegistration(String instanceName) throws Mastodon4jRequestException {
        MastodonClient client = new MastodonClient.Builder(instanceName, new OkHttpClient.Builder(), new Gson()).build();
        Apps apps = new Apps(client);
        return apps.createApp("kotlindon", "urn:ietf:wg:oauth:2.0:oob", new Scope(), null).execute();
    }
}
