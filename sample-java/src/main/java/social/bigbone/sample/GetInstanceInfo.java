package social.bigbone.sample;

import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonKt;
import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Instance;
import social.bigbone.api.exception.BigBoneRequestException;

import static kotlinx.serialization.SerializersKt.serializer;

@SuppressWarnings("PMD.SystemPrintln")
public class GetInstanceInfo {

    private static final Json JSON_SERIALIZER = JsonKt.Json(Json.Default, jsonBuilder -> {
        jsonBuilder.setEncodeDefaults(true);
        jsonBuilder.setIgnoreUnknownKeys(true);
        jsonBuilder.setCoerceInputValues(true);
        return null;
    });

    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];

        // Instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
                .build();

        // Get instance info and dump it to the console as JSON
        final Instance instanceInfo = client.instances().getInstance().execute();

        System.out.println(JSON_SERIALIZER.encodeToString(serializer(Instance.class), instanceInfo));
    }
}
