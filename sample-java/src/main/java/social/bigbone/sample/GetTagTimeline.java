package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.Range;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigBoneRequestException;
import social.bigbone.api.method.TimelinesMethods;

import static social.bigbone.api.method.TimelinesMethods.StatusOrigin.LOCAL_AND_REMOTE;

@SuppressWarnings("PMD.SystemPrintln")
public class GetTagTimeline {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];
        final String hashtag = args[1];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
            .build();

        // Get statuses from public timeline
        final TimelinesMethods timelinesMethods = new TimelinesMethods(client);
        final Pageable<Status> statuses = timelinesMethods.getTagTimeline(hashtag, new Range(), LOCAL_AND_REMOTE).execute();
        statuses.getPart().forEach(status -> System.out.println(status.getContent()));
    }
}
