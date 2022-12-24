package social.bigbone.sample;

import org.jetbrains.annotations.NotNull;
import social.bigbone.MastodonClient;
import social.bigbone.api.Handler;
import social.bigbone.api.Shutdownable;
import social.bigbone.api.entity.Notification;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Streaming;

@SuppressWarnings({"PMD.AvoidPrintStackTrace", "PMD.SystemPrintln"})
public class StreamPublicTimeline {
    public static void main(final String[] args) {
        // require authentication even if public streaming
        final String accessToken = "PUT YOUR ACCESS TOKEN";
        final MastodonClient client = new MastodonClient.Builder("mstdn.jp")
                .accessToken(accessToken)
                .useStreamingApi()
                .build();
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
            public void onDelete(final long id) {
                // No op
            }
        };
        final Streaming streaming = new Streaming(client);
        try {
            final Shutdownable shutdownable = streaming.localPublic(handler);
            Thread.sleep(10_000L);
            shutdownable.shutdown();
        } catch (BigboneRequestException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
