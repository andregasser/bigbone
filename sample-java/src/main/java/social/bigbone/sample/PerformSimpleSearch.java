package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Results;
import social.bigbone.api.exception.BigBoneRequestException;
import social.bigbone.api.method.Public;

@SuppressWarnings("PMD.SystemPrintln")
public class PerformSimpleSearch {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];
        final String accessToken = args[1];
        final String searchTerm = args[2];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build();

        // Perform search and print results
        final Public publicMethod = new Public(client);
        final Results searchResult = publicMethod.search(searchTerm).execute();
        searchResult.getAccounts().forEach(account -> System.out.println(account.getDisplayName()));
        searchResult.getStatuses().forEach(status -> System.out.println(status.getContent()));
        searchResult.getHashtags().forEach(hashtag -> System.out.println(hashtag.getName()));
    }
}
