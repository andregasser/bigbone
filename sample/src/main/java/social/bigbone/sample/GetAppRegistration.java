package social.bigbone.sample;


import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import social.bigbone.MastodonClient;
import social.bigbone.api.Scope;
import social.bigbone.api.entity.auth.AppRegistration;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Apps;

public class GetAppRegistration {
    public static void main(String[] args) {
        MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
        Apps apps = new Apps(client);
        try {
            AppRegistration registration = apps.createApp(
                    "mastodon4j-sample-app",
                    "urn:ietf:wg:oauth:2.0:oob",
                    new Scope(Scope.Name.ALL),
                    ""
            ).execute();
            System.out.println("instance=" + registration.getInstanceName());
            System.out.println("client_id=" + registration.getClientId());
            System.out.println("client_secret=" + registration.getClientSecret());
        } catch (BigboneRequestException e) {
            int statusCode = e.getResponse().code();
        }
    }
}
