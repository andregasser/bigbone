package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Range;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Timelines;

import java.io.IOException;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidPrintStackTrace"})
public class GetRawJson {
    public static void main(final String[] args) {
        try {
            final String instanceName = args[0];
            final String credentialFilePath = args[1];
            final MastodonClient client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath, false);
            final Timelines timelines = new Timelines(client);
            timelines.getPublicTimeline(new Range(), Timelines.StatusOrigin.LOCAL).doOnJson(System.out::println).execute();
        } catch (IOException | BigboneRequestException e) {
            e.printStackTrace();
        }
    }
}
