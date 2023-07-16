package social.bigbone.sample;

import social.bigbone.MastodonClient;
import social.bigbone.api.entity.Account;
import social.bigbone.api.exception.BigBoneRequestException;
import social.bigbone.api.method.DirectoryMethods;

import java.util.List;

public class GetDirectoryAccounts {
    public static void main(final String[] args) throws BigBoneRequestException {
        final String instance = args[0];
        final String accessToken = args[1];

        // instantiate client
        final MastodonClient client = new MastodonClient.Builder(instance)
                .accessToken(accessToken)
                .build();

        // get 40 local accounts that were recently active, skipping the first ten
        final List<Account> accounts = client.directories().listAccounts(
                true,
                DirectoryMethods.AccountOrder.ACTIVE,
                10,
                40
        ).execute();

        // do something with the result; here, we output how many times each account has posted since it was created
        accounts.forEach(account -> {
            System.out.println("@" + account.getAcct() + "@" + instance + " has posted "
                    + account.getStatusesCount() + " times since " + account.getCreatedAt());
        });
    }
}
