package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Instance;
import social.bigbone.api.exception.BigBoneClientInstantiationException;
import social.bigbone.api.exception.BigBoneRequestException;

@SuppressWarnings("PMD.SystemPrintln")
public class GetInstanceInfo {

    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];

        // Instantiate client
        final MastodonClient client;
        try {
            client = new MastodonClient.Builder(instance).build();
        } catch (BigBoneClientInstantiationException e) {
            throw new RuntimeException(e);
        }

        // Get instance info and dump it to the console as JSON
        final Instance instanceInfo = client.instances().getInstance().execute();

        System.out.println(instanceInfo.toString());
    }
}
