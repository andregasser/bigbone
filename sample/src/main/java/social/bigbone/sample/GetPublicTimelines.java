package social.bigbone.sample;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.Range;
import social.bigbone.api.entity.Status;
import social.bigbone.api.exception.BigboneRequestException;
import social.bigbone.api.method.Public;

@SuppressWarnings({"PMD.AvoidPrintStackTrace", "PMD.SystemPrintln"})
public class GetPublicTimelines {
    public static void main(final String[] args) {
        final MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
        final Public publicMethod = new Public(client);

        try {
            final Pageable<Status> statuses = publicMethod.getLocalPublic(new Range())
                    .doOnJson(System.out::println)
                    .execute();
            statuses.getPart().forEach(status -> {
                System.out.println("=============");
                System.out.println(status.getAccount().getDisplayName());
                System.out.println(status.getContent());
                System.out.println(status.isReblogged());
            });
        } catch (BigboneRequestException e) {
            e.printStackTrace();
        }
    }
}
