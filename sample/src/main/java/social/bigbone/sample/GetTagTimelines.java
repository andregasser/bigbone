package social.bigbone.sample;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import social.bigbone.MastodonClient;
import social.bigbone.api.Pageable;
import social.bigbone.api.Range;
import social.bigbone.api.entity.Status;
import social.bigbone.api.method.Public;
import social.bigbone.api.exception.BigboneRequestException;

public class GetTagTimelines {
    public static void main(String[] args) {
        MastodonClient client = new MastodonClient.Builder("mstdn.jp", new OkHttpClient.Builder(), new Gson()).build();
        Public publicMethod = new Public(client);

        try {
            Pageable<Status> statuses = publicMethod.getFederatedTag("mastodon", new Range()).execute();
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
