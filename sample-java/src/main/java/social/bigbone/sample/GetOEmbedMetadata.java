package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.OEmbedMetadata;
import social.bigbone.api.exception.BigBoneClientInstantiationException;
import social.bigbone.api.exception.BigBoneRequestException;

public class GetOEmbedMetadata {

    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];
        final String statusUrl = args[1];

        // Instantiate client
        final MastodonClient client;
        try {
            client = new MastodonClient.Builder(instance).build();
        } catch (BigBoneClientInstantiationException e) {
            throw new RuntimeException(e);
        }

        // Get oEmbed metadata for [statusUrl]
        final OEmbedMetadata oEmbedMetadata = client.oembed().getOEmbedInfoAsJson(statusUrl).execute();
        System.out.println(oEmbedMetadata);
    }
}
