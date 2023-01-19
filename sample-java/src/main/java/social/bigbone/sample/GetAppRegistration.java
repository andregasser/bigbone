package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Scope;
import social.bigbone.api.entity.auth.AppRegistration;
import social.bigbone.api.exception.BigBoneRequestException;
import social.bigbone.api.method.AppsMethods;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidPrintStackTrace"})
public class GetAppRegistration {
    public static void main(final String[] args) {
        final MastodonClient client = new MastodonClient.Builder("mstdn.jp").build();
        final AppsMethods appsMethods = new AppsMethods(client);
        try {
            final AppRegistration registration = appsMethods.createApp(
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
