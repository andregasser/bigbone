package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Public;

import java.io.IOException;

public class GetRawJson {
    public static void main(String[] args) {
        try {
            String instanceName = args[0];
            String credentialFilePath = args[1];
            MastodonClient client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath, false);
            Public publicMethod = new Public(client);
            publicMethod.getLocalPublic().doOnJson(System.out::println).execute();
        } catch (IOException | BigboneRequestException e) {
            e.printStackTrace();
        }
    }
}
