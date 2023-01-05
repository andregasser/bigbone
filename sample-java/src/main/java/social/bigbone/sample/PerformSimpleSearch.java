package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Results;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Public;

@SuppressWarnings("PMD.SystemPrintln")
public class PerformSimpleSearch {
    public static void main(final String[] args) throws BigboneRequestException {
        final String instance = "<YOUR INSTANCE>";
        final String accessToken = "<YOUR ACCESS TOKEN>";
        final String searchTerm = "<YOUR SEARCH TERM>";

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
            .accessToken(accessToken)
            .build();

        // Perform search and print results
        final Public publicMethod = new Public(client);
        final Results searchResult = publicMethod.getSearch(searchTerm).execute();
        searchResult.getAccounts().forEach(account -> System.out.println(account.getDisplayName()));
        searchResult.getStatuses().forEach(status -> System.out.println(status.getContent()));
        searchResult.getHashtags().forEach(hashtag -> System.out.println(hashtag.getName()));
    }
}
