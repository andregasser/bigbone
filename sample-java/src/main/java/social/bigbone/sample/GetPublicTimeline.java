package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigBoneRequestException;

import static social.bigbone.api.method.TimelineMethods.StatusOrigin.LOCAL_AND_REMOTE;

public class GetPublicTimeline {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
            .build();

        // Get statuses from public timeline
        final Pageable<Status> statuses = client.timelines().getPublicTimeline(LOCAL_AND_REMOTE).execute();
        statuses.getPart().forEach(status -> System.out.println(status.getContent()));
    }
}
