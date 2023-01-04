package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Results;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Public;

import java.io.IOException;

@SuppressWarnings({"PMD.SystemPrintln", "PMD.AvoidPrintStackTrace"})
public class Search {
    public static void main(final String[] args) {
        try {
            final String instanceName = args[0];
            final String credentialFilePath = args[1];
            final MastodonClient client = Authenticator.appRegistrationIfNeeded(instanceName, credentialFilePath, false);
            final Public publicMethod = new Public(client);
            final Results searchResult = publicMethod.getSearch("bigbone").execute();
            searchResult.getAccounts().forEach(account -> {
                System.out.println(account.getDisplayName());
            });
            searchResult.getStatuses().forEach(status -> {
                System.out.println(status.getContent());
            });
            searchResult.getHashtags().forEach(hashtag -> {
                System.out.println(hashtag.getName());
            });
        } catch (IOException | BigboneRequestException e) {
            e.printStackTrace();
        }
    }
}
