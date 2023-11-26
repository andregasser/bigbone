package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigBoneClientInstantiationException;
import social.bigbone.api.exception.BigBoneRequestException;

public class GetBookmarks {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];
        final String accessToken = args[1];

        // Instantiate client
        final MastodonClient client;
        try {
            client = new MastodonClient.Builder(instance)
                    .accessToken(accessToken)
                    .build();
        } catch (BigBoneClientInstantiationException e) {
            throw new RuntimeException(e);
        }

        // Get bookmarks
        final Pageable<Status> bookmarks = client.bookmarks().getBookmarks().execute();
        bookmarks.getPart().forEach(bookmark -> {
            String statusText = bookmark.getContent() + "\n";
            System.out.print(statusText);
        });
    }
}
