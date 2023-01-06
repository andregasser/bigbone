package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.Range;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Timelines;

import static social.bigbone.api.method.Timelines.StatusOrigin.LOCAL_AND_REMOTE;

@SuppressWarnings("PMD.SystemPrintln")
public class GetPublicTimeline {
    public static void main(final String[] args) throws BigboneRequestException {
        final String instance = args[0];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
            .build();

        // Get statuses from public timeline
        final Timelines timelines = new Timelines(client);
        final Pageable<Status> statuses = timelines.getPublicTimeline(new Range(), LOCAL_AND_REMOTE).execute();
        statuses.getPart().forEach(status -> System.out.println(status.getContent()));
    }
}
