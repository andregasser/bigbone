# Implementation Progress

Mastodon4j does not yet implement all the endpoints that are offered by the [Mastodon API](https://docs.joinmastodon.org/methods/). We use this page to
summarize which endpoints have been implemented and to what extent. 

We use the __Status__ column to provide information about the current implementation status of this endpoint. Values are as follows:

| Status   | Description                                                                                                                                | 
|----------|--------------------------------------------------------------------------------------------------------------------------------------------|
| Complete | The endpoint is fully implemented according to the Mastodon API documentation.                                                             |
| Partial  | The endpoint is partially implemented, meaning that some of the features / fields mentioned in the Mastodon API documentation are missing. |
| Missing  | The endpoint is not supported by the library at the moment.                                                                                |

In the __Comments__ column, we provide more information what exactly is missing in order to get to a fully implemented state.
We use the __Version__ column to indicate from which version on this endpoint started to become at least partially implemented.

## Methods

### Apps

https://docs.joinmastodon.org/methods/apps/   https://img.shields.io/badge/version-2.0.0-green

| Method                                | Description           | Version | Status  | Comments      | 
|---------------------------------------|-----------------------|---------|---------|---------------|
| `POST /api/v1/apps`                   | Create an application | 2.0.0   | Partial | To be checked |
| `GET /api/v1/apps/verify_credentials` | Verify your app works |         | Missing |               |

#### OAuth

https://docs.joinmastodon.org/methods/oauth/

| Method                                   | Description      | Version | Status  | Comments      | 
|------------------------------------------|------------------|---------|---------|---------------|
| `GET /oauth/authorize`                   | Authorize a user | 2.0.0   | Partial | To be checked |
| `POST /oauth/token`                      | Obtain a token   | 2.0.0   | Partial | To be checked |
| `POST /oauth/revoke`                     | Revoke a token   |         | Missing |               |
| `POST /oauth/token` (password authorize) |                  |         | Partial | To be checked |

#### Emails

https://docs.joinmastodon.org/methods/emails/

| Method                             | Description               | Version | Status  | Comments | 
|------------------------------------|---------------------------|---------|---------|----------|
| `POST /api/v1/emails/confirmation` | Resend confirmation email |         | Missing |          |

### Accounts

https://docs.joinmastodon.org/methods/accounts/

| Method                                            | Description                              | Version | Status  | Comments      | 
|---------------------------------------------------|------------------------------------------|---------|---------|---------------|
| `POST /api/v1/accounts`                           | Register an account                      |         | Missing |               |
| `GET /api/v1/accounts/verify_credentials`         | Verify account credentials               | 2.0.0   | Partial | To be checked |
| `PATCH /api/v1/accounts/update_credentials`       | Update account credentials               | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/:id`                        | Get account                              | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/:id/statuses`               | Get account's statuses                   | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/:id/followers`              | Get account's followers                  | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/:id/following`              | Get account's following                  | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/:id/featured_tags`          | Get account's featured tags              |         | Missing |               |
| `GET /api/v1/accounts/:id/lists`                  | Get lists containing this account        |         | Missing |               |
| `GET /api/v1/accounts/:id/follow`                 | Follow account                           | 2.0.0   | Partial | To be checked |
| `POST /api/v1/accounts/:id/unfollow`              | Unfollow account                         | 2.0.0   | Partial | To be checked |
| `POST /api/v1/accounts/:id/remove_from_followers` | Remove account from followers            |         | Missing |               |
| `POST /api/v1/accounts/:id/block`                 | Block account                            | 2.0.0   | Partial | To be checked |
| `POST /api/v1/accounts/:id/unblock`               | Unblock account                          | 2.0.0   | Partial | To be checked |
| `POST /api/v1/accounts/:id/mute`                  | Mute account                             | 2.0.0   | Partial | To be checked |
| `POST /api/v1/accounts/:id/unmute`                | Unmute account                           | 2.0.0   | Partial | To be checked |
| `POST /api/v1/accounts/:id/pin`                   | Feature account on your profile          |         | Missing |               |
| `POST /api/v1/accounts/:id/unpin`                 | Unfeature account from profile           |         | Missing |               |
| `POST /api/v1/accounts/:id/note`                  | Set private note on profile              |         | Missing |               |
| `GET /api/v1/accounts/relationships`              | Check relationships to other accounts    | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/familiar_followers`         | Find familiar followers                  |         | Missing |               |
| `GET /api/v1/accounts/search`                     | Search for matching accounts             | 2.0.0   | Partial | To be checked |
| `GET /api/v1/accounts/lookup`                     | Lookup account ID from Webfinger address |         | Missing |               |

#### Bookmarks

https://docs.joinmastodon.org/methods/bookmarks/

| Method                  | Description              | Version | Status  | Comments | 
|-------------------------|--------------------------|---------|---------|----------|
| `GET /api/v1/bookmarks` | View bookmarked statuses |         | Missing |          |

#### Favourites

https://docs.joinmastodon.org/methods/favourites/

| Method                   | Description            | Version | Status  | Comments      | 
|--------------------------|------------------------|---------|---------|---------------|
| `GET /api/v1/favourites` | View favourited status | 2.0.0   | Partial | To be checked |

#### Mutes

https://docs.joinmastodon.org/methods/mutes/

| Method              | Description         | Version | Status  | Comments      | 
|---------------------|---------------------|---------|---------|---------------|
| `GET /api/v1/mutes` | View muted accounts | 2.0.0   | Partial | To be checked |

#### Blocks

https://docs.joinmastodon.org/methods/blocks/

| Method               | Description        | Version | Status  | Comments      | 
|----------------------|--------------------|---------|---------|---------------|
| `GET /api/v1/blocks` | View blocked users | 2.0.0   | Partial | To be checked |


#### Domain Blocks

https://docs.joinmastodon.org/methods/domain_blocks/

| Method                         | Description       | Version | Status  | Comments | 
|--------------------------------|-------------------|---------|---------|----------|
| `GET /api/v1/domain_blocks`    | Get domain blocks |         | Missing |          |
| `POST /api/v1/domain_blocks`   | Block a domain    |         | Missing |          |
| `DELETE /api/v1/domain_blocks` | Unblock a domain  |         | Missing |          |

#### Filters

https://docs.joinmastodon.org/methods/filters/

__Server-side (V2) methods__

| Method                                     | Description                         | Version | Status  | Comments | 
|--------------------------------------------|-------------------------------------|---------|---------|----------|
| `GET /api/v2/filters`                      | View all filters                    |         | Missing |          |
| `GET /api/v2/filters/:id`                  | View a specific filter              |         | Missing |          |
| `POST /api/v2/filters`                     | Create a filter                     |         | Missing |          |
| `PUT /api/v2/filters/:id`                  | Update a filter                     |         | Missing |          |
| `DELETE /api/v2/filters/:id`               | Delete a filter                     |         | Missing |          |
| `GET /api/v2/filters/:filter_id/keywords`  | View keywords added to a filter     |         | Missing |          |
| `POST /api/v2/filters/:filter_id/keywords` | Add a keyword to a filter           |         | Missing |          |
| `GET /api/v2/filters/keywords/:id`         | View a single keyword               |         | Missing |          |
| `PUT /api/v2/filters/keywords/:id`         | Edit a keyword within a filter      |         | Missing |          |
| `DELETE /api/v2/filters/keywords/:id`      | Remove keywords from a filter       |         | Missing |          |
| `GET /api/v2/filters/:filter_id/statuses`  | View all status filters             |         | Missing |          |
| `POST /api/v2/filters/:filter_id/statuses` | Add a status to a filter group      |         | Missing |          |
| `GET /api/v2/filters/statuses/:id`         | View a single status filter         |         | Missing |          |
| `DELETE /api/v2/filters/statuses/:id`      | Remove a status from a filter group |         | Missing |          |


__Client-side (v1) methods__

| Method                       | Description          | Version | Status  | Comments | 
|------------------------------|----------------------|---------|---------|----------|
| `GET /api/v1/filters`        | View your filters    |         | Missing |          |
| `GET /api/v1/filters/:id`    | View a single filter |         | Missing |          |
| `POST /api/v1/filters`       | Create a filter      |         | Missing |          |
| `PUT /api/v1/filters/:id`    | Update a filter      |         | Missing |          |
| `DELETE /api/v1/filters/:id` | Remove a filter      |         | Missing |          |

#### Reports

https://docs.joinmastodon.org/methods/reports/

| Method                 | Description   | Version | Status  | Comments      | 
|------------------------|---------------|---------|---------|---------------|
| `POST /api/v1/reports` | File a report | 2.0.0   | Partial | To be checked |

#### Follow Requests

https://docs.joinmastodon.org/methods/follow_requests/

| Method                                               | Description                  | Version | Status  | Comments      | 
|------------------------------------------------------|------------------------------|---------|---------|---------------|
| `GET /api/v1/follow_requests`                        | View pending follow requests | 2.0.0   | Partial | To be checked |
| `POST /api/v1/follow_requests/:account_id/authorize` | Accept follow request        | 2.0.0   | Partial | To be checked |
| `POST /api/v1/follow_requests/:account_id/reject`    | Reject follow request        | 2.0.0   | Partial | To be checked |

#### Endorsements

https://docs.joinmastodon.org/methods/endorsements/

| Method                     | Description                      | Version | Status  | Comments | 
|----------------------------|----------------------------------|---------|---------|----------|
| `GET /api/v1/endorsements` | View currently features profiles |         | Missing |          |

#### Featured Tags

https://docs.joinmastodon.org/methods/featured_tags/

| Method                                  | Description                    | Version | Status  | Comments | 
|-----------------------------------------|--------------------------------|---------|---------|----------|
| `GET /api/v1/featured_tags`             | View your featured tags        |         | Missing |          |
| `POST /api/v1/featured_tags`            | Feature a tag                  |         | Missing |          |
| `DELETE /api/v1/featured_tags/:id`      | Unfeature a tag                |         | Missing |          |
| `GET /api/v1/featured_tags/suggestions` | View suggested tags to feature |         | Missing |          |

#### Preferences

https://docs.joinmastodon.org/methods/preferences/

| Method                    | Description           | Version | Status  | Comments | 
|---------------------------|-----------------------|---------|---------|----------|
| `GET /api/v1/preferences` | View user preferences |         | Missing |          |

#### Followed Tags

https://docs.joinmastodon.org/methods/followed_tags/

| Method                      | Description            | Version | Status  | Comments | 
|-----------------------------|------------------------|---------|---------|----------|
| `GET /api/v1/followed_tags` | View all followed tags |         | Missing |          |

#### Suggestions

https://docs.joinmastodon.org/methods/suggestions/

| Method                                   | Description                  | Version | Status  | Comments | 
|------------------------------------------|------------------------------|---------|---------|----------|
| `GET /api/v2/suggestions`                | View follow suggestions (v2) |         | Missing |          |
| `DELETE /api/v1/suggestions/:account_id` | Remove a suggestion          |         | Missing |          |

#### Tags

https://docs.joinmastodon.org/methods/tags/

| Method                           | Description                         | Version | Status  | Comments | 
|----------------------------------|-------------------------------------|---------|---------|----------|
| `GET /api/v1/tags/:id`           | View information about a single tag |         | Missing |          |
| `POST /api/v1/tags/:id/follow`   | Follow a hashtag                    |         | Missing |          |
| `POST /api/v1/tags/:id/unfollow` | Unfollow a hashtag                  |         | Missing |          |

### Statuses

https://docs.joinmastodon.org/methods/statuses/

| Method                                   | Description                              | Version | Status  | Comments      | 
|------------------------------------------|------------------------------------------|---------|---------|---------------|
| `POST /api/v1/statuses`                  | Post a new status                        | 2.0.0   | Partial | To be checked |
| `GET /api/v1/statuses/:id`               | View a single status                     | 2.0.0   | Partial | To be checked |
| `DELETE /api/v1/statuses/:id`            | Delete a single status                   | 2.0.0   | Partial | To be checked |
| `GET /api/v1/statuses/:id/context`       | Get parent and child statuses in context | 2.0.0   | Partial | To be checked |
| `GET /api/v1/statuses/:id/reblogged_by`  | See who boosted a status                 | 2.0.0   | Partial | To be checked |
| `GET /api/v1/statuses/:id/favourited_by` | See who favourited a status              | 2.0.0   | Partial | To be checked |
| `POST /api/v1/statuses/:id/favourite`    | Favourite a status                       | 2.0.0   | Partial | To be checked |
| `POST /api/v1/statuses/:id/unfavourite`  | Unfavourite a status                     | 2.0.0   | Partial | To be checked |
| `POST /api/v1/statuses/:id/reblog`       | Boost a status                           | 2.0.0   | Partial | To be checked |
| `POST /api/v1/statuses/:id/unreblog`     | Undo boost of a status                   | 2.0.0   | Partial | To be checked |
| `POST /api/v1/statuses/:id/bookmark`     | Bookmark a status                        |         | Missing |               |
| `POST /api/v1/statuses/:id/unbookmark`   | Undo bookmark of a status                |         | Missing |               |
| `POST /api/v1/statuses/:id/mute`         | Mute a conversation                      |         | Missing |               |
| `POST /api/v1/statuses/:id/unmute`       | Unmute a conversation                    |         | Missing |               |
| `POST /api/v1/statuses/:id/pin`          | Pin status to profile                    |         | Missing |               |
| `POST /api/v1/statuses/:id/unpin`        | Unpin status from profile                |         | Missing |               |
| `PUT /api/v1/statuses/:id`               | Edit a status                            |         | Missing |               |
| `GET /api/v1/statuses/:id/history`       | View edit history of a status            |         | Missing |               |
| `GET /api/v1/statuses/:id/source`        | View status source                       |         | Missing |               |
| `GET /api/v1/statuses/:id/card`          | (DEPRECATED) Fetch preview card          | 2.0.0   | Partial | To be checked |

#### Media

https://docs.joinmastodon.org/methods/media/

| Method                  | Description                                | Version | Status  | Comments      | 
|-------------------------|--------------------------------------------|---------|---------|---------------|
| `POST /api/v2/media`    | Upload media as an attachment (async)      |         | Missing |               |
| `GET /api/v1/media/:id` | Get media attachment                       |         | Missing |               |
| `PUT /api/v1/media/:id` | Update media attachment                    |         | Missing |               |
| `POST /api/v1/media`    | (DEPRECATED) Upload media as an attachment | 2.0.0   | Partial | To be checked |

#### Polls

https://docs.joinmastodon.org/methods/polls/

| Method                         | Description    | Version | Status  | Comments | 
|--------------------------------|----------------|---------|---------|----------|
| `GET /api/v1/polls/:id`        | View a poll    |         | Missing |          |
| `POST /api/v1/polls/:id/votes` | Vote on a poll |         | Missing |          |

#### Scheduled Statuses

https://docs.joinmastodon.org/methods/scheduled_statuses/

| Method                                  | Description                                 | Version | Status  | Comments | 
|-----------------------------------------|---------------------------------------------|---------|---------|----------|
| `GET /api/v1/scheduled_statuses`        | View scheduled statuses                     |         | Missing |          |
| `GET /api/v1/scheduled_statuses/:id`    | View a single scheduled status              |         | Missing |          |
| `PUT /api/v1/scheduled_statuses/:id`    | Update a scheduled status's publishing date |         | Missing |          |
| `DELETE /api/v1/scheduled_statuses/:id` | Cancel a scheduled status                   |         | Missing |          |

### Timelines

https://docs.joinmastodon.org/methods/timelines/

| Method                                | Description                       | Version | Status  | Comments      | 
|---------------------------------------|-----------------------------------|---------|---------|---------------|
| `GET /api/v1/timelines/public`        | View public timelines             | 2.0.0   | Partial | To be checked |
| `GET /api/v1/timelines/tag/:hashtag`  | View hashtag timeline             | 2.0.0   | Partial | To be checked |
| `GET /api/v1/timelines/home`          | View home timeline                | 2.0.0   | Partial | To be checked |
| `GET /api/v1/timelines/list/:list_id` | View list timeline                |         | Missing |               |
| `GET /api/v1/timelines/direct`        | (DEPRECATED) View direct timeline |         | Missing |               |

#### Conversations

https://docs.joinmastodon.org/methods/conversations/

| Method                                | Description                 | Version | Status  | Comments | 
|---------------------------------------|-----------------------------|---------|---------|----------|
| `GET /api/v1/conversations`           | View all conversations      |         | Missing |          |
| `DELETE /api/v1/conversations/:id`    | Remove a conversation       |         | Missing |          |
| `POST /api/v1/conversations/:id/read` | Mark a conversation as read |         | Missing |          |

#### Lists

https://docs.joinmastodon.org/methods/lists/

| Method                              | Description               | Version | Status  | Comments | 
|-------------------------------------|---------------------------|---------|---------|----------|
| `GET /api/v1/lists`                 | View your lists           |         | Missing |          |
| `GET /api/v1/lists`                 | Show a single list        |         | Missing |          |
| `POST /api/v1/lists`                | Create a list             |         | Missing |          |
| `PUT /api/v1/lists/:id`             | Update a list             |         | Missing |          |
| `DELETE /api/v1/lists/:id`          | Delete a list             |         | Missing |          |
| `GET /api/v1/lists/:id/accounts`    | View accounts in a list   |         | Missing |          |
| `POST /api/v1/lists/:id/accounts`   | Add accounts to a list    |         | Missing |          |
| `DELETE /api/v1/lists/:id/accounts` | Remove accounts from list |         | Missing |          |

#### Markers

https://docs.joinmastodon.org/methods/markers/

| Method                 | Description                      | Version | Status  | Comments | 
|------------------------|----------------------------------|---------|---------|----------|
| `GET /api/v1/markers`  | Get saved timeline positions     |         | Missing |          |
| `POST /api/v1/markers` | Save your position in a timeline |         | Missing |          |

#### Streaming

https://docs.joinmastodon.org/methods/streaming/

| Method                                    | Description                                | Version | Status  | Comments      | 
|-------------------------------------------|--------------------------------------------|---------|---------|---------------|
| `GET /api/v1/streaming/health`            | Check if the server is alive               |         | Missing |               |
| `GET /api/v1/streaming/user`              | Watch your home timeline and notifications | 2.0.0   | Partial | To be checked |
| `GET /api/v1/streaming/user/notification` | Watch your notifications                   |         | Missing |               |
| `GET /api/v1/streaming/public`            | Watch the federated timeline               | 2.0.0   | Partial | To be checked |
| `GET /api/v1/streaming/public/local`      | Watch the local timeline                   | 2.0.0   | Partial | To be checked |
| `GET /api/v1/streaming/public/remote`     | Watch for remote statuses                  |         | Missing |               |
| `GET /api/v1/streaming/hashtag`           | Watch the public timeline for a hashtag    | 2.0.0   | Partial | To be checked |
| `GET /api/v1/streaming/hashtag/local`     | Watch the local timeline for a hashtag     | 2.0.0   | Partial | To be checked |
| `GET /api/v1/streaming/list`              | Watch for list updates                     |         | Missing |               |
| `GET /api/v1/streaming/direct`            | Watch for direct messages                  |         | Missing |               |

### Notifications

https://docs.joinmastodon.org/methods/notifications/

| Method                                   | Description                             | Version | Status  | Comments      | 
|------------------------------------------|-----------------------------------------|---------|---------|---------------|
| `GET /api/v1/notifications`              | Get all notifications                   | 2.0.0   | Partial | To be checked |
| `GET /api/v1/notifications/:id`          | Get a single notifications              | 2.0.0   | Partial | To be checked |
| `POST /api/v1/notifications/clear`       | Dismiss all notifications               | 2.0.0   | Partial | To be checked |
| `POST /api/v1/notifications/:id/dismiss` | Dismiss a single notification           |         | Missing |               |
| `POST /api/v1/notifications/dismiss`     | (REMOVED) Dismiss a single notification |         | Missing |               |

#### Push

https://docs.joinmastodon.org/methods/push/

| Method                             | Description                     | Version | Status  | Comments | 
|------------------------------------|---------------------------------|---------|---------|----------|
| `POST /api/v1/push/subscription`   | Subscribe to push notifications |         | Missing |          |
| `GET /api/v1/push/subscription`    | Get current subscription        |         | Missing |          |
| `PUT /api/v1/push/subscription`    | Change type of notifications    |         | Missing |          |
| `DELETE /api/v1/push/subscription` | Remove current subscription     |         | Missing |          |

### Search

https://docs.joinmastodon.org/methods/search/

| Method               | Description                   | Version | Status  | Comments      | 
|----------------------|-------------------------------|---------|---------|---------------|
| `GET /api/v2/search` | Perform a search              |         | Missing |               |
| `GET /api/v1/search` | (REMOVED) Search results (v1) | 2.0.0   | Partial | To be checked |

### Instance

https://docs.joinmastodon.org/methods/instance/

| Method                               | Description                               | Version | Status  | Comments      | 
|--------------------------------------|-------------------------------------------|---------|---------|---------------|
| `GET /api/v2/instance`               | View server information                   |         | Missing |               |
| `GET /api/v1/instance/peers`         | List of connected domains                 |         | Missing |               |
| `GET /api/v1/instance/activity`      | Weekly activity                           |         | Missing |               |
| `GET /api/v1/instance/rules`         | List of rules                             |         | Missing |               |
| `GET /api/v1/instance/domain_blocks` | View moderated servers                    |         | Missing |               |
| `GET /api/v1/example`                | View extended description                 |         | Missing |               |
| `GET /api/v1/instance`               | (DEPRECATED) View server information (V1) | 2.0.0   | Partial | To be checked |

#### Trends

https://docs.joinmastodon.org/methods/trends/

| Method                        | Description            | Version | Status  | Comments | 
|-------------------------------|------------------------|---------|---------|----------|
| `GET /api/v1/trends/tags`     | View trending tags     |         | Missing |          |
| `GET /api/v1/trends/statuses` | View trending statuses |         | Missing |          |
| `GET /api/v1/trends/links`    | View trending links    |         | Missing |          |

#### Directory

https://docs.joinmastodon.org/methods/directory/

| Method                  | Description            | Version | Status  | Comments | 
|-------------------------|------------------------|---------|---------|----------|
| `GET /api/v1/directory` | View profile directory |         | Missing |          |

#### Custom Emojis

https://docs.joinmastodon.org/methods/custom_emojis/

| Method                      | Description           | Version | Status  | Comments | 
|-----------------------------|-----------------------|---------|---------|----------|
| `GET /api/v1/custom_emojis` | View all custom emoji |         | Missing |          |

#### Announcements

https://docs.joinmastodon.org/methods/announcements/

| Method                                             | Description                            | Version | Status  | Comments | 
|----------------------------------------------------|----------------------------------------|---------|---------|----------|
| `GET /api/v1/announcements`                        | View all announcements                 |         | Missing |          |
| `POST /api/v1/announcements/:id/dismiss`           | Dismiss an announcement                |         | Missing |          |
| `PUT /api/v1/announcements/:id/reactions/:name`    | Add a reaction to an announcement      |         | Missing |          |
| `DELETE /api/v1/announcements/:id/reactions/:name` | Remove a reaction from an announcement |         | Missing |          |

### Admin

#### Admin (Accounts)

https://docs.joinmastodon.org/methods/admin/accounts/

| Method                                        | Description                          | Version | Status  | Comments | 
|-----------------------------------------------|--------------------------------------|---------|---------|----------|
| `GET /api/v1/admin/accounts`                  | View accounts (v1)                   |         | Missing |          |
| `GET /api/v2/admin/accounts`                  | View accounts (v2)                   |         | Missing |          |
| `GET /api/v1/admin/accounts/:id`              | View a specific account              |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/approve`     | Approve a pending account            |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/reject`      | Reject a pending account             |         | Missing |          |
| `DELETE /api/v1/admin/accounts/:id`           | Delete an account                    |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/action`      | Perform an action against an account |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/enable`      | Enable a currently disabled account  |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/unsilence`   | Unsilence an account                 |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/unsuspend`   | Unsuspend an account                 |         | Missing |          |
| `POST /api/v1/admin/accounts/:id/unsensitive` | Unmark an account as sensitive       |         | Missing |          |

#### Admin (Domain Blocks)

https://docs.joinmastodon.org/methods/admin/domain_blocks/

| Method                                   | Description                    | Version | Status  | Comments | 
|------------------------------------------|--------------------------------|---------|---------|----------|
| `GET /api/v1/admin/domain_blocks`        | List all blocked domains       |         | Missing |          |
| `GET /api/v1/admin/domain_blocks/:id`    | Get a single blocked domain    |         | Missing |          |
| `POST /api/v1/admin/domain_blocks`       | Block a domain from federating |         | Missing |          |
| `PUT /api/v1/admin/domain_blocks/:id`    | Update a domain block          |         | Missing |          |
| `DELETE /api/v1/admin/domain_blocks/:id` | Remove a domain block          |         | Missing |          |

#### Admin (Reports)

https://docs.joinmastodon.org/methods/admin/reports/

| Method                                          | Description             | Version | Status  | Comments | 
|-------------------------------------------------|-------------------------|---------|---------|----------|
| `GET /api/v1/admin/reports`                     | View all reports        |         | Missing |          |
| `GET /api/v1/admin/reports/:id`                 | View a single report    |         | Missing |          |
| `PUT /api/v1/admin/reports/:id`                 | Update a report         |         | Missing |          |
| `POST /api/v1/admin/reports/:id/assign_to_self` | Assign report to self   |         | Missing |          |
| `POST /api/v1/admin/reports/:id/unassign`       | Unassign report         |         | Missing |          |
| `POST /api/v1/admin/reports/:id/resolve`        | Mark report as resolved |         | Missing |          |
| `POST /api/v1/admin/reports/:id/reopen`         | Reopen a closed report  |         | Missing |          |

#### Admin (Trends)

https://docs.joinmastodon.org/methods/admin/trends/

| Method                              | Description            | Version | Status  | Comments | 
|-------------------------------------|------------------------|---------|---------|----------|
| `GET /api/v1/admin/trends/links`    | View trending links    |         | Missing |          |
| `GET /api/v1/admin/trends/statuses` | View trending statuses |         | Missing |          |
| `GET /api/v1/admin/trends/tags`     | View trending tags     |         | Missing |          |

#### Admin (Canonical Email Blocks)

https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/

| Method                                            | Description                         | Version | Status  | Comments | 
|---------------------------------------------------|-------------------------------------|---------|---------|----------|
| `GET /api/v1/admin/canonical_email_blocks`        | List all canonical email blocks     |         | Missing |          |
| `GET /api/v1/admin/canonical_email_blocks/:id`    | Show a single canonical email block |         | Missing |          |
| `POST /api/v1/admin/canonical_email_blocks/test`  | Test                                |         | Missing |          |
| `POST /api/v1/admin/canonical_email_blocks`       | Block a canonical email             |         | Missing |          |
| `DELETE /api/v1/admin/canonical_email_blocks/:id` | Delete a canonical email block      |         | Missing |          |

#### Admin (Dimensions)

https://docs.joinmastodon.org/methods/admin/dimensions/

| Method                          | Description          | Version | Status  | Comments | 
|---------------------------------|----------------------|---------|---------|----------|
| `POST /api/v1/admin/dimensions` | Get dimensional data |         | Missing |          |

#### Admin (Domain Allows)

https://docs.joinmastodon.org/methods/admin/domain_allows/

| Method                                   | Description                 | Version | Status  | Comments | 
|------------------------------------------|-----------------------------|---------|---------|----------|
| `GET /api/v1/admin/domain_allows`        | List all allowed domains    |         | Missing |          |
| `GET /api/v1/admin/domain_allows/:id`    | Get a single allowed domain |         | Missing |          |
| `POST /api/v1/admin/domain_allows`       | Allow a domain to federate  |         | Missing |          |
| `DELETE /api/v1/admin/domain_allows/:id` | Delete an allowed domain    |         | Missing |          |

#### Admin (Email Domain Blocks)

https://docs.joinmastodon.org/methods/admin/email_domain_blocks/

| Method                                         | Description                        | Version | Status  | Comments | 
|------------------------------------------------|------------------------------------|---------|---------|----------|
| `GET /api/v1/admin/email_domain_blocks`        | List all blocked email domains     |         | Missing |          |
| `GET /api/v1/admin/email_domain_blocks/:id`    | Get a single blocked email domain  |         | Missing |          |
| `POST /api/v1/admin/email_domain_blocks`       | Block an email domain from signups |         | Missing |          |
| `DELETE /api/v1/admin/email_domain_blocks/:id` | Delete an email domain block       |         | Missing |          |

#### Admin (IP Blocks)

https://docs.joinmastodon.org/methods/admin/ip_blocks/

| Method                               | Description                               | Version | Status  | Comments | 
|--------------------------------------|-------------------------------------------|---------|---------|----------|
| `GET /api/v1/admin/ip_blocks`        | List all IP blocks                        |         | Missing |          |
| `GET /api/v1/admin/ip_blocks/:id`    | Get a single IP block                     |         | Missing |          |
| `POST /api/v1/admin/ip_blocks`       | Block an IP address range from signing up |         | Missing |          |
| `PUT /api/v1/admin/ip_blocks/:id`    | Update a domain block                     |         | Missing |          |
| `DELETE /api/v1/admin/ip_blocks/:id` | Delete an IP block                        |         | Missing |          |

#### Admin (Measures)

https://docs.joinmastodon.org/methods/admin/measures/

| Method                        | Description         | Version | Status  | Comments | 
|-------------------------------|---------------------|---------|---------|----------|
| `POST /api/v1/admin/measures` | Get measurable data |         | Missing |          |

#### Admin (Retention)

https://docs.joinmastodon.org/methods/admin/retention/

| Method                         | Description              | Version | Status  | Comments | 
|--------------------------------|--------------------------|---------|---------|----------|
| `POST /api/v1/admin/retention` | Calculate retention data |         | Missing |          |

### OEmbed

https://docs.joinmastodon.org/methods/oembed/

| Method            | Description             | Version | Status  | Comments | 
|-------------------|-------------------------|---------|---------|----------|
| `GET /api/oembed` | Get OEmbed info as JSON |         | Missing |          |

===========================


### Follows

- [x] POST `/api/v1/follows`


### Reports

- [x] GET `/api/v1/reports`


## Rx

Additionally, the following implementations using RxJava exist:

(since v0.0.2)

- [x] RxAccounts
- [x] RxApps
- [x] RxBlocks
- [x] RxFavourites
- [x] RxFollowRequests
- [x] RxFollows
- [x] RxInstances
- [x] RxMedia
- [x] RxMutes
- [x] RxNotifications
- [x] RxReports
- [x] RxSearch
- [x] RxStatuses
- [x] RxTimelines


