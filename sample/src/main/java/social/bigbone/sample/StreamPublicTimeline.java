package social.bigbone.sample;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import social.bigbone.MastodonClient;
import social.bigbone.api.Handler;
import social.bigbone.api.Shutdownable;
import social.bigbone.api.entity.Notification;
import social.bigbone.api.entity.Status;
import social.bigbone.api.method.Streaming;

public class StreamPublicTimeline {
    public static void main(String[] args) {
        // require authentication even if public streaming
        String accessToken = "PUT YOUR ACCESS TOKEN";
        MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson())
                .accessToken(accessToken)
                .useStreamingApi()
                .build();
        Handler handler = new Handler() {
            @Override
            public void onStatus(@NotNull Status status) {
                System.out.println(status.getContent());
            }

            @Override
            public void onNotification(@NotNull Notification notification) {

            }

            @Override
            public void onDelete(long id) {

            }
        };
        Streaming streaming = new Streaming(client);
        try {
            Shutdownable shutdownable = streaming.localPublic(handler);
            Thread.sleep(10_000L);
            shutdownable.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
