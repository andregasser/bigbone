package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.MastodonRequest;
import social.bigbone.api.Scope;
import social.bigbone.api.entity.Token;
import social.bigbone.api.exception.BigBoneClientInstantiationException;
import social.bigbone.api.exception.BigBoneRequestException;

import java.util.Scanner;

public class OAuthGetAccessToken {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instanceName = args[0];
        final String clientId = args[1];
        final String clientSecret = args[2];
        final String redirectUri = args[3];

        final MastodonClient client;
        try {
            client = new MastodonClient.Builder(instanceName).build();
        } catch (BigBoneClientInstantiationException e) {
            throw new RuntimeException(e);
        }
        final String url = client.oauth().getOAuthUrl(clientId, new Scope(), redirectUri);
        System.out.println("Open authorization page and copy code:");
        System.out.println(url);
        System.out.println("Paste code:");
        String authCode;
        try (Scanner s = new Scanner(System.in)) {
            authCode = s.nextLine();
        }
        final MastodonRequest<Token> token = client.oauth().getUserAccessTokenWithAuthorizationCodeGrant(
                clientId,
                clientSecret,
                redirectUri,
                authCode);

        System.out.println("Access Token:");
        System.out.println(token.execute().getAccessToken());
    }
}
