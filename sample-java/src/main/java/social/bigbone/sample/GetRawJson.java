package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Range;
import social.bigbone.api.exception.BigBoneRequestException;
import social.bigbone.api.method.Timelines;

import static social.bigbone.api.method.Timelines.StatusOrigin.LOCAL_AND_REMOTE;

@SuppressWarnings("PMD.SystemPrintln")
public class GetRawJson {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
            .build();

        // Print timeline statuses
        final Timelines timelines = new Timelines(client);
        timelines.getPublicTimeline(new Range(), LOCAL_AND_REMOTE).doOnJson(
            System.out::println
        ).execute();
    }
}
