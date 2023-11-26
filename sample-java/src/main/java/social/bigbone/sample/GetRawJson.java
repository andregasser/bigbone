package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.exception.BigBoneClientInstantiationException;
import social.bigbone.api.exception.BigBoneRequestException;

import static social.bigbone.api.method.TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE;

public class GetRawJson {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];

        // Instantiate client
        final MastodonClient client;
        try {
            client = new MastodonClient.Builder(instance).build();
        } catch (BigBoneClientInstantiationException e) {
            throw new RuntimeException(e);
        }

        // Print timeline statuses
        client.timelines().getPublicTimeline(LOCAL_AND_REMOTE).doOnJson(
            System.out::println
        ).execute();
    }
}
