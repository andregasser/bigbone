# Implementation Progress

Bigbone does not yet implement all the endpoints that are offered by the [Mastodon API](https://docs.joinmastodon.org/methods/). We use this page to
provide an overview of which endpoints have been implemented and to what extent. This information applies to the latest version of Bigbone. 

We use the __Status__ column to provide information about the current implementation status of this endpoint. Values are as follows:

| Status | Description                                                                                                                                | 
|--------|--------------------------------------------------------------------------------------------------------------------------------------------|
| 🟢     | The endpoint is fully implemented according to the Mastodon API documentation.                                                             |
| 🟠     | The endpoint is partially implemented, meaning that some of the features / fields mentioned in the Mastodon API documentation are missing. |
| 🔴     | The endpoint is not supported by the library at the moment.                                                                                |

In the __Comments__ column, we provide more information what exactly is missing in order to get to a fully implemented state.
We use the __Version__ column to indicate from which version on this endpoint started to become at least partially implemented.

## Methods

### Apps

https://docs.joinmastodon.org/methods/apps/   https://img.shields.io/badge/version-2.0.0-green

| Method                                | Description           | Status | Comments                                      | 
|---------------------------------------|-----------------------|--------|-----------------------------------------------|
| `POST /api/v1/apps`                   | Create an application | 🟠     | `AppRegistration` entity needs to be updated. |
| `GET /api/v1/apps/verify_credentials` | Verify your app works | 🔴     |                                               |

#### OAuth

https://docs.joinmastodon.org/methods/oauth/

| Method                 | Description      | Status | Comments                                                                           | 
|------------------------|------------------|--------|------------------------------------------------------------------------------------|
| `GET /oauth/authorize` | Authorize a user | 🟠     | `force_login`, `lang` query parameters are missing. Method is missing in RxApps.kt |
| `POST /oauth/token`    | Obtain a token   | 🟢     |                                                                                    |
| `POST /oauth/revoke`   | Revoke a token   | 🔴     |                                                                                    |

#### Emails

https://docs.joinmastodon.org/methods/emails/

| Method                             | Description               | Status | Comments | 
|------------------------------------|---------------------------|--------|----------|
| `POST /api/v1/emails/confirmation` | Resend confirmation email | 🔴     |          |

### Accounts

https://docs.joinmastodon.org/methods/accounts/

| Method                                            | Description                              |  Status | Comments                                                                                                  | 
|---------------------------------------------------|------------------------------------------|---------|-----------------------------------------------------------------------------------------------------------|
| `POST /api/v1/accounts`                           | Register an account                      |  🔴     |                                                                                                           |
| `GET /api/v1/accounts/verify_credentials`         | Verify account credentials               |  🟠     | Should return `CredentialAccount`instead of `Account`.                                                    |
| `PATCH /api/v1/accounts/update_credentials`       | Update account credentials               |  🟠     | `locked`, `bot`, `discoverable`, `fields_attributes`, `source` query parameters are missing.              |
| `GET /api/v1/accounts/:id`                        | Get account                              |  🟠     | `Account`entity needs to be updated.                                                                      |
| `GET /api/v1/accounts/:id/statuses`               | Get account's statuses                   |  🟠     | `max_id`, `since_id`, `min_id`, `limit`, `exclude_reblogs`, `tagged` query parameters are missing.        |
| `GET /api/v1/accounts/:id/followers`              | Get account's followers                  |  🟠     | `min_id` query parameter missing. `Account` entity needs to be updated.                                   |
| `GET /api/v1/accounts/:id/following`              | Get account's following                  |  🟠     | `min_id` query parameter missing. `Account` entity needs to be updated.                                   |
| `GET /api/v1/accounts/:id/featured_tags`          | Get account's featured tags              |  🔴     |                                                                                                           |
| `GET /api/v1/accounts/:id/lists`                  | Get lists containing this account        |  🔴     |                                                                                                           |
| `GET /api/v1/accounts/:id/follow`                 | Follow account                           |  🟠     | `reblogs`, `notify`, `languages` query parameters are missing. `Relationship` entity needs to be updated. |
| `POST /api/v1/accounts/:id/unfollow`              | Unfollow account                         |  🟠     | `Relationship` entity needs to be updated.                                                                |
| `POST /api/v1/accounts/:id/remove_from_followers` | Remove account from followers            |  🔴     |                                                                                                           |
| `POST /api/v1/accounts/:id/block`                 | Block account                            |  🟠     | `Relationship` entity needs to be updated.                                                                |
| `POST /api/v1/accounts/:id/unblock`               | Unblock account                          |  🟠     | `Relationship` entity needs to be updated.                                                                |
| `POST /api/v1/accounts/:id/mute`                  | Mute account                             |  🟠     | `notifications`, `duration` form data parameters are missing. `Relationship` entity needs to be updated.  |
| `POST /api/v1/accounts/:id/unmute`                | Unmute account                           |  🟠     | `Relationship` entity needs to be updated.                                                                |
| `POST /api/v1/accounts/:id/pin`                   | Feature account on your profile          |  🔴     |                                                                                                           |
| `POST /api/v1/accounts/:id/unpin`                 | Unfeature account from profile           |  🔴     |                                                                                                           |
| `POST /api/v1/accounts/:id/note`                  | Set private note on profile              |  🔴     |                                                                                                           |
| `GET /api/v1/accounts/relationships`              | Check relationships to other accounts    |  🟠     | `Relationship` entity needs to be updated.                                                                |
| `GET /api/v1/accounts/familiar_followers`         | Find familiar followers                  |  🔴     |                                                                                                           |
| `GET /api/v1/accounts/search`                     | Search for matching accounts             |  🟠     | `offset`, `resolve`, `following` query parameters are missing. `Account` entity needs to be updated.      |
| `GET /api/v1/accounts/lookup`                     | Lookup account ID from Webfinger address |  🔴     |                                                                                                           |

#### Bookmarks

https://docs.joinmastodon.org/methods/bookmarks/

| Method                  | Description              | Status | Comments | 
|-------------------------|--------------------------|--------|----------|
| `GET /api/v1/bookmarks` | View bookmarked statuses | 🔴     |          |

#### Favourites

https://docs.joinmastodon.org/methods/favourites/

| Method                   | Description            | Status | Comments                                                               | 
|--------------------------|------------------------|--------|------------------------------------------------------------------------|
| `GET /api/v1/favourites` | View favourited status | 🟠     | `min_id` query parameter missing. `Status` entity needs to be updated. |

#### Mutes

https://docs.joinmastodon.org/methods/mutes/

| Method              | Description         | Status | Comments                              | 
|---------------------|---------------------|--------|---------------------------------------|
| `GET /api/v1/mutes` | View muted accounts | 🟠     | `Account` entity needs to be updated. |

#### Blocks

https://docs.joinmastodon.org/methods/blocks/

| Method               | Description        | Status | Comments                                                                | 
|----------------------|--------------------|--------|-------------------------------------------------------------------------|
| `GET /api/v1/blocks` | View blocked users | 🟠     | `min_id` query parameter missing. `Account` entity needs to be updated. |


#### Domain Blocks

https://docs.joinmastodon.org/methods/domain_blocks/

| Method                         | Description       | Status | Comments | 
|--------------------------------|-------------------|--------|----------|
| `GET /api/v1/domain_blocks`    | Get domain blocks | 🔴     |          |
| `POST /api/v1/domain_blocks`   | Block a domain    | 🔴     |          |
| `DELETE /api/v1/domain_blocks` | Unblock a domain  | 🔴     |          |

#### Filters

https://docs.joinmastodon.org/methods/filters/

__Server-side (V2) methods__

| Method                                     | Description                         | Status | Comments | 
|--------------------------------------------|-------------------------------------|--------|----------|
| `GET /api/v2/filters`                      | View all filters                    | 🔴     |          |
| `GET /api/v2/filters/:id`                  | View a specific filter              | 🔴     |          |
| `POST /api/v2/filters`                     | Create a filter                     | 🔴     |          |
| `PUT /api/v2/filters/:id`                  | Update a filter                     | 🔴     |          |
| `DELETE /api/v2/filters/:id`               | Delete a filter                     | 🔴     |          |
| `GET /api/v2/filters/:filter_id/keywords`  | View keywords added to a filter     | 🔴     |          |
| `POST /api/v2/filters/:filter_id/keywords` | Add a keyword to a filter           | 🔴     |          |
| `GET /api/v2/filters/keywords/:id`         | View a single keyword               | 🔴     |          |
| `PUT /api/v2/filters/keywords/:id`         | Edit a keyword within a filter      | 🔴     |          |
| `DELETE /api/v2/filters/keywords/:id`      | Remove keywords from a filter       | 🔴     |          |
| `GET /api/v2/filters/:filter_id/statuses`  | View all status filters             | 🔴     |          |
| `POST /api/v2/filters/:filter_id/statuses` | Add a status to a filter group      | 🔴     |          |
| `GET /api/v2/filters/statuses/:id`         | View a single status filter         | 🔴     |          |
| `DELETE /api/v2/filters/statuses/:id`      | Remove a status from a filter group | 🔴     |          |


__Client-side (v1) methods__

| Method                       | Description          | Status | Comments | 
|------------------------------|----------------------|--------|----------|
| `GET /api/v1/filters`        | View your filters    | 🔴     |          |
| `GET /api/v1/filters/:id`    | View a single filter | 🔴     |          |
| `POST /api/v1/filters`       | Create a filter      | 🔴     |          |
| `PUT /api/v1/filters/:id`    | Update a filter      | 🔴     |          |
| `DELETE /api/v1/filters/:id` | Remove a filter      | 🔴     |          |

#### Reports

https://docs.joinmastodon.org/methods/reports/

| Method                 | Description   | Status | Comments                                                                                                               | 
|------------------------|---------------|--------|------------------------------------------------------------------------------------------------------------------------|
| `POST /api/v1/reports` | File a report | 🟠     | `status_ids`, `forward`, `category`, `rule_ids` form data parameters are missing. `Report` entity needs to be updated. |

#### Follow Requests

https://docs.joinmastodon.org/methods/follow_requests/

| Method                                               | Description                  | Status | Comments                                   | 
|------------------------------------------------------|------------------------------|--------|--------------------------------------------|
| `GET /api/v1/follow_requests`                        | View pending follow requests | 🟠     | `Account` entity needs to be updated.      |
| `POST /api/v1/follow_requests/:account_id/authorize` | Accept follow request        | 🟠     | `Relationship` entity needs to be updated. |
| `POST /api/v1/follow_requests/:account_id/reject`    | Reject follow request        | 🟠     | `Relationship` entity needs to be updated. |

#### Endorsements

https://docs.joinmastodon.org/methods/endorsements/

| Method                     | Description                          | Status | Comments | 
|----------------------------|--------------------------------------|--------|----------|
| `GET /api/v1/endorsements` | View currently features profiles  🔴 |        |

#### Featured Tags

https://docs.joinmastodon.org/methods/featured_tags/

| Method                                  | Description                    | Status | Comments | 
|-----------------------------------------|--------------------------------|--------|----------|
| `GET /api/v1/featured_tags`             | View your featured tags        | 🔴     |          |
| `POST /api/v1/featured_tags`            | Feature a tag                  | 🔴     |          |
| `DELETE /api/v1/featured_tags/:id`      | Unfeature a tag                | 🔴     |          |
| `GET /api/v1/featured_tags/suggestions` | View suggested tags to feature | 🔴     |          |

#### Preferences

https://docs.joinmastodon.org/methods/preferences/

| Method                    | Description           | Status | Comments | 
|---------------------------|-----------------------|--------|----------|
| `GET /api/v1/preferences` | View user preferences | 🔴     |          |

#### Followed Tags

https://docs.joinmastodon.org/methods/followed_tags/

| Method                      | Description            | Status | Comments | 
|-----------------------------|------------------------|--------|----------|
| `GET /api/v1/followed_tags` | View all followed tags | 🔴     |          |

#### Suggestions

https://docs.joinmastodon.org/methods/suggestions/

| Method                                   | Description                  | Status | Comments | 
|------------------------------------------|------------------------------|--------|----------|
| `GET /api/v2/suggestions`                | View follow suggestions (v2) | 🔴     |          |
| `DELETE /api/v1/suggestions/:account_id` | Remove a suggestion          | 🔴     |          |

#### Tags

https://docs.joinmastodon.org/methods/tags/

| Method                           | Description                         | Status | Comments | 
|----------------------------------|-------------------------------------|--------|----------|
| `GET /api/v1/tags/:id`           | View information about a single tag | 🔴     |          |
| `POST /api/v1/tags/:id/follow`   | Follow a hashtag                    | 🔴     |          |
| `POST /api/v1/tags/:id/unfollow` | Unfollow a hashtag                  | 🔴     |          |

### Statuses

https://docs.joinmastodon.org/methods/statuses/

| Method                                   | Description                              | Status | Comments                                                                                           | 
|------------------------------------------|------------------------------------------|--------|----------------------------------------------------------------------------------------------------|
| `POST /api/v1/statuses`                  | Post a new status                        | 🟠     | `poll`, `language`, `scheduled_at` request parameter missing. `Status` entity needs to be updated. |
| `GET /api/v1/statuses/:id`               | View a single status                     | 🟠     | `Status` entity needs to be updated.                                                               |
| `DELETE /api/v1/statuses/:id`            | Delete a single status                   | 🟠     | `Status` entity needs to be updated.                                                               |
| `GET /api/v1/statuses/:id/context`       | Get parent and child statuses in context | 🟠     | `Status` entity needs to be updated.                                                               |
| `GET /api/v1/statuses/:id/reblogged_by`  | See who boosted a status                 | 🟠     | `min_id` request parameter is missing. `Status` entity needs to be updated.                        |
| `GET /api/v1/statuses/:id/favourited_by` | See who favourited a status              | 🟠     | `min_id` request parameter is missing. `Status` entity needs to be updated.                        |
| `POST /api/v1/statuses/:id/favourite`    | Favourite a status                       | 🟠     | `Status` entity needs to be updated.                                                               |
| `POST /api/v1/statuses/:id/unfavourite`  | Unfavourite a status                     | 🟠     | `Status` entity needs to be updated.                                                               |
| `POST /api/v1/statuses/:id/reblog`       | Boost a status                           | 🟠     | `Status` entity needs to be updated.                                                               |
| `POST /api/v1/statuses/:id/unreblog`     | Undo boost of a status                   | 🟠     | `Status` entity needs to be updated.                                                               |
| `POST /api/v1/statuses/:id/bookmark`     | Bookmark a status                        | 🔴     |                                                                                                    |
| `POST /api/v1/statuses/:id/unbookmark`   | Undo bookmark of a status                | 🔴     |                                                                                                    |
| `POST /api/v1/statuses/:id/mute`         | Mute a conversation                      | 🔴     |                                                                                                    |
| `POST /api/v1/statuses/:id/unmute`       | Unmute a conversation                    | 🔴     |                                                                                                    |
| `POST /api/v1/statuses/:id/pin`          | Pin status to profile                    | 🔴     |                                                                                                    |
| `POST /api/v1/statuses/:id/unpin`        | Unpin status from profile                | 🔴     |                                                                                                    |
| `PUT /api/v1/statuses/:id`               | Edit a status                            | 🔴     |                                                                                                    |
| `GET /api/v1/statuses/:id/history`       | View edit history of a status            | 🔴     |                                                                                                    |
| `GET /api/v1/statuses/:id/source`        | View status source                       | 🔴     |                                                                                                    |
| `GET /api/v1/statuses/:id/card`          | (DEPRECATED) Fetch preview card          | 🟠     | `Card` entity needs to be updated.                                                                 |

#### Media

https://docs.joinmastodon.org/methods/media/

| Method                  | Description                                | Status | Comments                                                                                                     | 
|-------------------------|--------------------------------------------|--------|--------------------------------------------------------------------------------------------------------------|
| `POST /api/v2/media`    | Upload media as an attachment (async)      | 🔴     |                                                                                                              |
| `GET /api/v1/media/:id` | Get media attachment                       | 🔴     |                                                                                                              |
| `PUT /api/v1/media/:id` | Update media attachment                    | 🔴     |                                                                                                              |
| `POST /api/v1/media`    | (DEPRECATED) Upload media as an attachment | 🟠     | `thumbnail`, `description`, `focus` request parameters are missing. `Attachment` entity needs to be updated. |

#### Polls

https://docs.joinmastodon.org/methods/polls/

| Method                         | Description    | Status | Comments | 
|--------------------------------|----------------|--------|----------|
| `GET /api/v1/polls/:id`        | View a poll    | 🔴     |          |
| `POST /api/v1/polls/:id/votes` | Vote on a poll | 🔴     |          |

#### Scheduled Statuses

https://docs.joinmastodon.org/methods/scheduled_statuses/

| Method                                  | Description                                 | Status | Comments | 
|-----------------------------------------|---------------------------------------------|--------|----------|
| `GET /api/v1/scheduled_statuses`        | View scheduled statuses                     | 🔴     |          |
| `GET /api/v1/scheduled_statuses/:id`    | View a single scheduled status              | 🔴     |          |
| `PUT /api/v1/scheduled_statuses/:id`    | Update a scheduled status's publishing date | 🔴     |          |
| `DELETE /api/v1/scheduled_statuses/:id` | Cancel a scheduled status                   | 🔴     |          |

### Timelines

https://docs.joinmastodon.org/methods/timelines/

| Method                                | Description                       | Status | Comments                                                                                                              | 
|---------------------------------------|-----------------------------------|--------|-----------------------------------------------------------------------------------------------------------------------|
| `GET /api/v1/timelines/public`        | View public timelines             | 🟠     | `remote`, `only_media`, `min_id` query parameters missing. `Status` entity needs to be updated.                       |
| `GET /api/v1/timelines/tag/:hashtag`  | View hashtag timeline             | 🟠     | `any`, `all`, `none`, `remote`, `only_media`, `min_id` query parameters missing. `Status` entity needs to be updated. |
| `GET /api/v1/timelines/home`          | View home timeline                | 🟠     | `min_id` query parameter missing. `Status` entity needs to be updated.                                                |
| `GET /api/v1/timelines/list/:list_id` | View list timeline                | 🔴     |                                                                                                                       |
| `GET /api/v1/timelines/direct`        | (DEPRECATED) View direct timeline | 🔴     |                                                                                                                       |

#### Conversations

https://docs.joinmastodon.org/methods/conversations/

| Method                                | Description                 | Status | Comments | 
|---------------------------------------|-----------------------------|--------|----------|
| `GET /api/v1/conversations`           | View all conversations      | 🔴     |          |
| `DELETE /api/v1/conversations/:id`    | Remove a conversation       | 🔴     |          |
| `POST /api/v1/conversations/:id/read` | Mark a conversation as read | 🔴     |          |

#### Lists

https://docs.joinmastodon.org/methods/lists/

| Method                              | Description               | Status | Comments                                   | 
|-------------------------------------|---------------------------|--------|--------------------------------------------|
| `GET /api/v1/lists`                 | View your lists           | 🟠     | `MastodonList` entity needs to be updated. |
| `GET /api/v1/lists`                 | Show a single list        | 🔴     |                                            |
| `POST /api/v1/lists`                | Create a list             | 🔴     |                                            |
| `PUT /api/v1/lists/:id`             | Update a list             | 🔴     |                                            |
| `DELETE /api/v1/lists/:id`          | Delete a list             | 🔴     |                                            |
| `GET /api/v1/lists/:id/accounts`    | View accounts in a list   | 🔴     |                                            |
| `POST /api/v1/lists/:id/accounts`   | Add accounts to a list    | 🔴     |                                            |
| `DELETE /api/v1/lists/:id/accounts` | Remove accounts from list | 🔴     |                                            |

#### Markers

https://docs.joinmastodon.org/methods/markers/

| Method                 | Description                      | Status | Comments | 
|------------------------|----------------------------------|--------|----------|
| `GET /api/v1/markers`  | Get saved timeline positions     | 🔴     |          |
| `POST /api/v1/markers` | Save your position in a timeline | 🔴     |          |

#### Streaming

https://docs.joinmastodon.org/methods/streaming/

| Method                                    | Description                                | Status | Comments                                                                                                                      | 
|-------------------------------------------|--------------------------------------------|--------|-------------------------------------------------------------------------------------------------------------------------------|
| `GET /api/v1/streaming/health`            | Check if the server is alive               | 🔴     |                                                                                                                               |
| `GET /api/v1/streaming/user`              | Watch your home timeline and notifications | 🟠     | `filters_changed`, `announcement`, `announcement.reaction`, `announcement.delete`, `status.update` event types not supported. |
| `GET /api/v1/streaming/user/notification` | Watch your notifications                   | 🔴     |                                                                                                                               |
| `GET /api/v1/streaming/public`            | Watch the federated timeline               | 🟠     | `only_media` query parameter not supported. `delete`, `status.update` event types not supported.                              |
| `GET /api/v1/streaming/public/local`      | Watch the local timeline                   | 🟠     | `only_media` query parameter not supported. `delete`, `status.update` event types not supported.                              |
| `GET /api/v1/streaming/public/remote`     | Watch for remote statuses                  | 🔴     |                                                                                                                               |
| `GET /api/v1/streaming/hashtag`           | Watch the public timeline for a hashtag    | 🟠     | `delete`, `status.update` event types not supported.                                                                          |
| `GET /api/v1/streaming/hashtag/local`     | Watch the local timeline for a hashtag     | 🟠     | `delete`, `status.update` event types not supported.                                                                          |
| `GET /api/v1/streaming/list`              | Watch for list updates                     | 🔴     |                                                                                                                               |
| `GET /api/v1/streaming/direct`            | Watch for direct messages                  | 🔴     |                                                                                                                               |

### Notifications

https://docs.joinmastodon.org/methods/notifications/

| Method                                   | Description                             | Status | Comments                                                                                                                                | 
|------------------------------------------|-----------------------------------------|--------|-----------------------------------------------------------------------------------------------------------------------------------------|
| `GET /api/v1/notifications`              | Get all notifications                   | 🟠     | `max_id`, `since_id`, `min_id`, `limit`, `types`, `account_id` query parameters are missing. `Notification` entity needs to be updated. |
| `GET /api/v1/notifications/:id`          | Get a single notifications              | 🟠     | `Notification` entity needs to be updated.                                                                                              |
| `POST /api/v1/notifications/clear`       | Dismiss all notifications               | 🟢     |                                                                                                                                         |
| `POST /api/v1/notifications/:id/dismiss` | Dismiss a single notification           | 🔴     |                                                                                                                                         |
| `POST /api/v1/notifications/dismiss`     | (REMOVED) Dismiss a single notification | 🔴     |                                                                                                                                         |

#### Push

https://docs.joinmastodon.org/methods/push/

| Method                             | Description                     | Status | Comments | 
|------------------------------------|---------------------------------|--------|----------|
| `POST /api/v1/push/subscription`   | Subscribe to push notifications | 🔴     |          |
| `GET /api/v1/push/subscription`    | Get current subscription        | 🔴     |          |
| `PUT /api/v1/push/subscription`    | Change type of notifications    | 🔴     |          |
| `DELETE /api/v1/push/subscription` | Remove current subscription     | 🔴     |          |

### Search

https://docs.joinmastodon.org/methods/search/

| Method               | Description      | Status | Comments | 
|----------------------|------------------|--------|----------|
| `GET /api/v2/search` | Perform a search | 🔴     |          |

### Instance

https://docs.joinmastodon.org/methods/instance/

| Method                               | Description                               | Status | Comments                               | 
|--------------------------------------|-------------------------------------------|--------|----------------------------------------|
| `GET /api/v2/instance`               | View server information                   | 🔴     |                                        |
| `GET /api/v1/instance/peers`         | List of connected domains                 | 🔴     |                                        |
| `GET /api/v1/instance/activity`      | Weekly activity                           | 🔴     |                                        |
| `GET /api/v1/instance/rules`         | List of rules                             | 🔴     |                                        |
| `GET /api/v1/instance/domain_blocks` | View moderated servers                    | 🔴     |                                        |
| `GET /api/v1/example`                | View extended description                 | 🔴     |                                        |
| `GET /api/v1/instance`               | (DEPRECATED) View server information (V1) | 🟠     | `Instance` entity needs to be updated. |

#### Trends

https://docs.joinmastodon.org/methods/trends/

| Method                        | Description            | Status | Comments | 
|-------------------------------|------------------------|--------|----------|
| `GET /api/v1/trends/tags`     | View trending tags     | 🔴     |          |
| `GET /api/v1/trends/statuses` | View trending statuses | 🔴     |          |
| `GET /api/v1/trends/links`    | View trending links    | 🔴     |          |

#### Directory

https://docs.joinmastodon.org/methods/directory/

| Method                  | Description            | Status | Comments | 
|-------------------------|------------------------|--------|----------|
| `GET /api/v1/directory` | View profile directory | 🔴     |          |

#### Custom Emojis

https://docs.joinmastodon.org/methods/custom_emojis/

| Method                      | Description           | Status | Comments | 
|-----------------------------|-----------------------|--------|----------|
| `GET /api/v1/custom_emojis` | View all custom emoji | 🔴     |          |

#### Announcements

https://docs.joinmastodon.org/methods/announcements/

| Method                                             | Description                            | Status | Comments | 
|----------------------------------------------------|----------------------------------------|--------|----------|
| `GET /api/v1/announcements`                        | View all announcements                 | 🔴     |          |
| `POST /api/v1/announcements/:id/dismiss`           | Dismiss an announcement                | 🔴     |          |
| `PUT /api/v1/announcements/:id/reactions/:name`    | Add a reaction to an announcement      | 🔴     |          |
| `DELETE /api/v1/announcements/:id/reactions/:name` | Remove a reaction from an announcement | 🔴     |          |

### Admin

#### Admin (Accounts)

https://docs.joinmastodon.org/methods/admin/accounts/

| Method                                        | Description                          | Status | Comments | 
|-----------------------------------------------|--------------------------------------|--------|----------|
| `GET /api/v1/admin/accounts`                  | View accounts (v1)                   | 🔴     |          |
| `GET /api/v2/admin/accounts`                  | View accounts (v2)                   | 🔴     |          |
| `GET /api/v1/admin/accounts/:id`              | View a specific account              | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/approve`     | Approve a pending account            | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/reject`      | Reject a pending account             | 🔴     |          |
| `DELETE /api/v1/admin/accounts/:id`           | Delete an account                    | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/action`      | Perform an action against an account | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/enable`      | Enable a currently disabled account  | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/unsilence`   | Unsilence an account                 | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/unsuspend`   | Unsuspend an account                 | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/unsensitive` | Unmark an account as sensitive       | 🔴     |          |

#### Admin (Domain Blocks)

https://docs.joinmastodon.org/methods/admin/domain_blocks/

| Method                                   | Description                    | Status | Comments | 
|------------------------------------------|--------------------------------|--------|----------|
| `GET /api/v1/admin/domain_blocks`        | List all blocked domains       | 🔴     |          |
| `GET /api/v1/admin/domain_blocks/:id`    | Get a single blocked domain    | 🔴     |          |
| `POST /api/v1/admin/domain_blocks`       | Block a domain from federating | 🔴     |          |
| `PUT /api/v1/admin/domain_blocks/:id`    | Update a domain block          | 🔴     |          |
| `DELETE /api/v1/admin/domain_blocks/:id` | Remove a domain block          | 🔴     |          |

#### Admin (Reports)

https://docs.joinmastodon.org/methods/admin/reports/

| Method                                          | Description             | Status | Comments | 
|-------------------------------------------------|-------------------------|--------|----------|
| `GET /api/v1/admin/reports`                     | View all reports        | 🔴     |          |
| `GET /api/v1/admin/reports/:id`                 | View a single report    | 🔴     |          |
| `PUT /api/v1/admin/reports/:id`                 | Update a report         | 🔴     |          |
| `POST /api/v1/admin/reports/:id/assign_to_self` | Assign report to self   | 🔴     |          |
| `POST /api/v1/admin/reports/:id/unassign`       | Unassign report         | 🔴     |          |
| `POST /api/v1/admin/reports/:id/resolve`        | Mark report as resolved | 🔴     |          |
| `POST /api/v1/admin/reports/:id/reopen`         | Reopen a closed report  | 🔴     |          |

#### Admin (Trends)

https://docs.joinmastodon.org/methods/admin/trends/

| Method                              | Description            | Status | Comments | 
|-------------------------------------|------------------------|--------|----------|
| `GET /api/v1/admin/trends/links`    | View trending links    | 🔴     |          |
| `GET /api/v1/admin/trends/statuses` | View trending statuses | 🔴     |          |
| `GET /api/v1/admin/trends/tags`     | View trending tags     | 🔴     |          |

#### Admin (Canonical Email Blocks)

https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/

| Method                                            | Description                         | Status | Comments | 
|---------------------------------------------------|-------------------------------------|--------|----------|
| `GET /api/v1/admin/canonical_email_blocks`        | List all canonical email blocks     | 🔴     |          |
| `GET /api/v1/admin/canonical_email_blocks/:id`    | Show a single canonical email block | 🔴     |          |
| `POST /api/v1/admin/canonical_email_blocks/test`  | Test                                | 🔴     |          |
| `POST /api/v1/admin/canonical_email_blocks`       | Block a canonical email             | 🔴     |          |
| `DELETE /api/v1/admin/canonical_email_blocks/:id` | Delete a canonical email block      | 🔴     |          |

#### Admin (Dimensions)

https://docs.joinmastodon.org/methods/admin/dimensions/

| Method                          | Description          | Status | Comments | 
|---------------------------------|----------------------|--------|----------|
| `POST /api/v1/admin/dimensions` | Get dimensional data | 🔴     |          |

#### Admin (Domain Allows)

https://docs.joinmastodon.org/methods/admin/domain_allows/

| Method                                   | Description                 | Status | Comments | 
|------------------------------------------|-----------------------------|--------|----------|
| `GET /api/v1/admin/domain_allows`        | List all allowed domains    | 🔴     |          |
| `GET /api/v1/admin/domain_allows/:id`    | Get a single allowed domain | 🔴     |          |
| `POST /api/v1/admin/domain_allows`       | Allow a domain to federate  | 🔴     |          |
| `DELETE /api/v1/admin/domain_allows/:id` | Delete an allowed domain    | 🔴     |          |

#### Admin (Email Domain Blocks)

https://docs.joinmastodon.org/methods/admin/email_domain_blocks/

| Method                                         | Description                        | Status | Comments | 
|------------------------------------------------|------------------------------------|--------|----------|
| `GET /api/v1/admin/email_domain_blocks`        | List all blocked email domains     | 🔴     |          |
| `GET /api/v1/admin/email_domain_blocks/:id`    | Get a single blocked email domain  | 🔴     |          |
| `POST /api/v1/admin/email_domain_blocks`       | Block an email domain from signups | 🔴     |          |
| `DELETE /api/v1/admin/email_domain_blocks/:id` | Delete an email domain block       | 🔴     |          |

#### Admin (IP Blocks)

https://docs.joinmastodon.org/methods/admin/ip_blocks/

| Method                               | Description                               | Status | Comments | 
|--------------------------------------|-------------------------------------------|--------|----------|
| `GET /api/v1/admin/ip_blocks`        | List all IP blocks                        | 🔴     |          |
| `GET /api/v1/admin/ip_blocks/:id`    | Get a single IP block                     | 🔴     |          |
| `POST /api/v1/admin/ip_blocks`       | Block an IP address range from signing up | 🔴     |          |
| `PUT /api/v1/admin/ip_blocks/:id`    | Update a domain block                     | 🔴     |          |
| `DELETE /api/v1/admin/ip_blocks/:id` | Delete an IP block                        | 🔴     |          |

#### Admin (Measures)

https://docs.joinmastodon.org/methods/admin/measures/

| Method                        | Description         | Status | Comments | 
|-------------------------------|---------------------|--------|----------|
| `POST /api/v1/admin/measures` | Get measurable data | 🔴     |          |

#### Admin (Retention)

https://docs.joinmastodon.org/methods/admin/retention/

| Method                         | Description              | Status | Comments | 
|--------------------------------|--------------------------|--------|----------|
| `POST /api/v1/admin/retention` | Calculate retention data | 🔴     |          |

### OEmbed

https://docs.joinmastodon.org/methods/oembed/

| Method            | Description             | Status | Comments | 
|-------------------|-------------------------|--------|----------|
| `GET /api/oembed` | Get OEmbed info as JSON | 🔴     |          |

## Reactive Implementation
Bigbone offers reactive endpoints based on RxJava as well. We have not listed the detailed implementation status here, but we are aware that not all endpoints
are offered as rx endpoints yet. It might also happen that they are not complete in terms of parameters or response objects. We will try to clean this up as 
we go along and implement missing functionality. If you think, a specific rx endpoint is critical to have, please open an issue in the
[Issues](https://github.com/andregasser/bigbone/issues) section or even better, provide a pull request.


