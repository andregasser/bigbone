package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Search;
import social.bigbone.api.exception.BigBoneClientInstantiationException;
import social.bigbone.api.exception.BigBoneRequestException;

public class PerformSimpleSearch {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];
        final String accessToken = args[1];
        final String searchTerm = args[2];

        // Instantiate client
        final MastodonClient client;
        try {
            client = new MastodonClient.Builder(instance)
                    .accessToken(accessToken)
                    .build();
        } catch (BigBoneClientInstantiationException e) {
            throw new RuntimeException(e);
        }

        // Perform search and print results
        final Search searchResult = client.search().searchContent(searchTerm).execute();
        searchResult.getAccounts().forEach(account -> System.out.println(account.getDisplayName()));
        searchResult.getStatuses().forEach(status -> System.out.println(status.getContent()));
        searchResult.getHashtags().forEach(hashtag -> System.out.println(hashtag.getName()));
    }
}
