package social.bigbone.sample;

import org.jetbrains.annotations.NotNull;
import social.bigbone.MastodonClient;
import social.bigbone.api.Handler;
import social.bigbone.api.Shutdownable;
import social.bigbone.api.entity.Notification;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigBoneRequestException;

@SuppressWarnings("PMD.SystemPrintln")
public class StreamFederatedPublicTimeline {
    public static void main(final String[] args) throws BigBoneRequestException, InterruptedException {
        final String instance = args[0];
        final String accessToken = args[1];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
                .accessToken(accessToken)
                .useStreamingApi()
                .build();

        // Configure status handler
        final Handler handler = new Handler() {
            @Override
            public void onStatus(@NotNull final Status status) {
                System.out.println(status.getContent());
            }

            @Override
            public void onNotification(@NotNull final Notification notification) {
                // No op
            }

            @Override
            public void onDelete(@NotNull final String id) {
                // No op
            }
        };

        // Start federated timeline streaming and stop after 20 seconds
        final Shutdownable shutdownable = client.streaming().federatedPublic(handler);
        Thread.sleep(20_000L);
        shutdownable.shutdown();
    }
}
