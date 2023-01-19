package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Scope;
import social.bigbone.api.entity.auth.AppRegistration;
import social.bigbone.api.exception.BigBoneRequestException;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidPrintStackTrace"})
public class GetAppRegistration {
    public static void main(final String[] args) {
        final MastodonClient client = new MastodonClient.Builder("mstdn.jp").build();
        try {
            final AppRegistration registration = client.apps().createApp(
                    "bigbone-sample-app",
                    "urn:ietf:wg:oauth:2.0:oob",
                    new Scope(Scope.Name.ALL),
                    ""
            ).execute();
            System.out.println("instance=" + registration.getInstanceName());
            System.out.println("client_id=" + registration.getClientId());
            System.out.println("client_secret=" + registration.getClientSecret());
        } catch (BigBoneRequestException e) {
            e.printStackTrace();
        }
    }
}
