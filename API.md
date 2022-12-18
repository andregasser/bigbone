# Implementation Progress

**Mastodon4j** currently implements the checkmarked methods of the Mastodon API documented here: https://docs.joinmastodon.org/methods/

Methods listed without checkmark, or those missing from the list, are not yet implemented.
An optional version number preceding a list, or listed after an individual endpoint, identifies the first Mastodon4j version to implement this method.

## Methods

### Apps

https://docs.joinmastodon.org/methods/apps/

- [x] POST `/api/v1/apps` (create an application)
- [ ] GET `/api/v1/apps/verify_credentials` (Verify your app works)

### OAuth

https://docs.joinmastodon.org/methods/oauth/

- [x] GET `/oauth/authorize` (Authorize a user)
- [x] POST `/oauth/token` (Obtain a token)
- [ ] POST `/oauth/revoke` (Revoke a token)
- [x] POST password authorize `/oauth/token` (v0.0.2) (to be checked)

### Emails

https://docs.joinmastodon.org/methods/emails/

- [ ] POST `/api/v1/emails/confirmation` (Resend confirmation email)

### Accounts

https://docs.joinmastodon.org/methods/accounts/

- [ ] POST `/api/v1/accounts` (Register an account)
- [x] GET `/api/v1/accounts/verify_credentials` (Verify account credentials)
- [x] PATCH `/api/v1/accounts/update_credentials` (Update account credentials)
- [x] GET `/api/v1/accounts/:id` (Get account)
- [x] GET `/api/v1/accounts/:id/statuses` (Get account's statuses)
- [x] GET `/api/v1/accounts/:id/followers` (Get account's followers)
- [x] GET `/api/v1/accounts/:id/following` (Get account's following)
- [ ] GET `/api/v1/accounts/:id/featured_tags` (Get account's featured tags)
- [ ] GET `/api/v1/accounts/:id/lists` (Get lists containing this account)
- [x] POST `/api/v1/accounts/:id/follow` (Follow account)
- [x] POST `/api/v1/accounts/:id/unfollow` (Unfollow account)
- [ ] POST `/api/v1/accounts/:id/remove_from_followers` (Remove account from followers)
- [x] POST `/api/v1/accounts/:id/block` (Block account)
- [x] POST `/api/v1/accounts/:id/unblock` (Unblock account)
- [x] POST `/api/v1/accounts/:id/mute` (Mute account)
- [x] POST `/api/v1/accounts/:id/unmute` (Unmute account)
- [ ] POST `/api/v1/accounts/:id/pin` (Feature account on your profile)
- [ ] POST `/api/v1/accounts/:id/unpin` (Unfeature account from profile)
- [ ] POST `/api/v1/accounts/:id/note` (Set private note on profile)
- [x] GET `/api/v1/accounts/relationships` (Check relationships to other accounts)
- [ ] GET `/api/v1/accounts/familiar_followers` (Find familiar followers)
- [x] GET `/api/v1/accounts/search` (Search for matching accounts)
- [ ] GET `/api/v1/accounts/lookup` (Lookup account ID from Webfinger address)

### Bookmarks

https://docs.joinmastodon.org/methods/bookmarks/

- [ ] GET `/api/v1/bookmarks` (View bookmarked statuses)

### Favourites

https://docs.joinmastodon.org/methods/favourites/

- [x] GET `/api/v1/favourites`

### Mutes

https://docs.joinmastodon.org/methods/mutes/

- [x] GET `/api/v1/mutes` (View muted accounts)

### Blocks

https://docs.joinmastodon.org/methods/blocks/

- [x] GET `/api/v1/blocks` (View blocked users)

### Domain Blocks

https://docs.joinmastodon.org/methods/domain_blocks/

- [ ] GET `/api/v1/domain_blocks` (Get domain blocks)
- [ ] POST `/api/v1/domain_blocks` (Block a domain)
- [ ] DELETE `/api/v1/domain_blocks` (Unblock a domain)

### Filters

https://docs.joinmastodon.org/methods/filters/

__Server-side (V2) methods__
- [ ] GET `/api/v2/filters` (View all filters)
- [ ] GET `/api/v2/filters/:id` (View a specific filter)
- [ ] POST `/api/v2/filters` (Create a filter)
- [ ] PUT `/api/v2/filters/:id` (Update a filter)
- [ ] DELETE `/api/v2/filters/:id` (Delete a filter)
- [ ] GET `/api/v2/filters/:filter_id/keywords` (View keywords added to a filter)
- [ ] POST `/api/v2/filters/:filter_id/keywords` (Add a keyword to a filter)
- [ ] GET `/api/v2/filters/keywords/:id` (View a single keyword)
- [ ] PUT `/api/v2/filters/keywords/:id` (Edit a keyword within a filter)
- [ ] DELETE `/api/v2/filters/keywords/:id` (Remove keywords from a filter)
- [ ] GET `/api/v2/filters/:filter_id/statuses` (View all status filters)
- [ ] POST `/api/v2/filters/:filter_id/statuses` (Add a status to a filter group)
- [ ] GET `/api/v2/filters/statuses/:id` (View a single status filter)
- [ ] DELETE `/api/v2/filters/statuses/:id` (Remove a status from a filter group)

__Client-side (v1) methods__
- [ ] GET `/api/v1/filters` (View your filters)
- [ ] GET `/api/v1/filters/:id` (View a single filter)
- [ ] POST `/api/v1/filters` (Create a filter)
- [ ] PUT `/api/v1/filters/:id` (Update a filter)
- [ ] DELETE `/api/v1/filters/:id` (Remove a filter)

### Reports

https://docs.joinmastodon.org/methods/reports/

- [x] POST `/api/v1/reports` (File a report)

### Follow Requests

https://docs.joinmastodon.org/methods/follow_requests/

- [x] GET `/api/v1/follow_requests` (View pending follow requests)
- [x] POST `/api/v1/follow_requests/:account_id/authorize` (Accept follow request)
- [x] POST `/api/v1/follow_requests/:account_id/reject` (Reject follow request)

### Endorsements

https://docs.joinmastodon.org/methods/endorsements/

- [ ] GET `/api/v1/endorsements` (View currently features profiles)

### Featured Tags

https://docs.joinmastodon.org/methods/featured_tags/

- [ ] GET `/api/v1/featured_tags` (View your featured tags)
- [ ] POST `/api/v1/featured_tags` (Feature a tag)
- [ ] DELETE `/api/v1/featured_tags/:id` (Unfeature a tag)
- [ ] GET `/api/v1/featured_tags/suggestions` (View suggested tags to feature)

### Preferences

https://docs.joinmastodon.org/methods/preferences/

- [ ] GET /api/v1/preferences (View user preferences)

### Followed Tags

https://docs.joinmastodon.org/methods/followed_tags/

- [ ] GET `/api/v1/followed_tags` (View all followed tags)

### Suggestions

https://docs.joinmastodon.org/methods/suggestions/

- [ ] GET `/api/v2/suggestions` (View follow suggestions (v2))
- [ ] DELETE `/api/v1/suggestions/:account_id` (Remove a suggestion)

### Tags

https://docs.joinmastodon.org/methods/tags/

- [ ] GET `/api/v1/tags/:id` (View information about a single tag)
- [ ] POST `/api/v1/tags/:id/follow` (Follow a hashtag)
- [ ] POST `/api/v1/tags/:id/unfollow` (Unfollow a hashtag)

### Statuses

https://docs.joinmastodon.org/methods/statuses/

- [x] POST `/api/v1/statuses` (Post a new status)
- [x] GET `/api/v1/statuses/:id` (View a single status)
- [x] DELETE `/api/v1/statuses/:id` (Delete a single status)
- [x] GET `/api/v1/statuses/:id/context` (Get parent and child statuses in context)
- [x] GET `/api/v1/statuses/:id/reblogged_by` (See who boosted a status)
- [x] GET `/api/v1/statuses/:id/favourited_by` (See who favourited a status)
- [x] POST `/api/v1/statuses/:id/favourite` (Favourite a status)
- [x] POST `/api/v1/statuses/:id/unfavourite` (Unfavourite a status)
- [x] POST `/api/v1/statuses/:id/reblog` (Boost a status)
- [x] POST `/api/v1/statuses/:id/unreblog` (Undo boost of a status)
- [ ] POST `/api/v1/statuses/:id/bookmark` (Bookmark a status)
- [ ] POST `/api/v1/statuses/:id/unbookmark` (Undo bookmark of a status)
- [ ] POST `/api/v1/statuses/:id/mute` (Mute a conversation)
- [ ] POST `/api/v1/statuses/:id/unmute` (Unmute a conversation)
- [ ] POST `/api/v1/statuses/:id/pin` (Pin status to profile)
- [ ] POST `/api/v1/statuses/:id/unpin` (Unpin status from profile)
- [ ] PUT `/api/v1/statuses/:id` (Edit a status)
- [ ] GET `/api/v1/statuses/:id/history` (View edit history of a status)
- [ ] GET `/api/v1/statuses/:id/source` (View status source)
- [x] GET `/api/v1/statuses/:id/card` ((DEPRECATED) Fetch preview card)
 
### Media

https://docs.joinmastodon.org/methods/media/

- [ ] POST `/api/v2/media` (Upload media as an attachment (async))
- [ ] GET `/api/v1/media/:id` (Get media attachmnet)
- [ ] PUT `/api/v1/media/:id` (Update media attachment)
- [x] POST `/api/v1/media` ((DEPRECATED) Upload media as an attachment)

### Polls

https://docs.joinmastodon.org/methods/polls/

- [ ] GET `/api/v1/polls/:id` (View a poll)
- [ ] POST `/api/v1/polls/:id/votes` (Vote on a poll)

### Scheduled Statuses

https://docs.joinmastodon.org/methods/scheduled_statuses/

- [ ] GET `/api/v1/scheduled_statuses` (View scheduled statuses)
- [ ] GET `/api/v1/scheduled_statuses/:id` (View a single scheduled status)
- [ ] PUT `/api/v1/scheduled_statuses/:id` (Update a scheduled status's publishing date)
- [ ] DELETE `/api/v1/scheduled_statuses/:id` (Cancel a scheduled status)

### Timelines

https://docs.joinmastodon.org/methods/timelines/

- [x] GET `/api/v1/timelines/public` (View public timelines)
- [x] GET `/api/v1/timelines/tag/:hashtag` (View hashtag timeline)
- [x] GET `/api/v1/timelines/home` (View home timeline)
- [ ] GET `/api/v1/timelines/list/:list_id` (View list timeline)
- [ ] GET `/api/v1/timelines/direct` ((DEPRECATED) View direct timeline)

### Conversations

https://docs.joinmastodon.org/methods/conversations/

- [ ] GET `/api/v1/conversations` (View all conversations)
- [ ] DELETE `/api/v1/conversations/:id` (Remove a conversation)
- [ ] POST `/api/v1/conversations/:id/read` (Mark a conversation as read)

### Lists

https://docs.joinmastodon.org/methods/lists/

- [ ] GET `/api/v1/lists` (View your lists)
- [ ] GET `/api/v1/lists` (Show a single list)
- [ ] POST `/api/v1/lists` (Create a list)
- [ ] PUT `/api/v1/lists/:id` (Update a list)
- [ ] DELETE `/api/v1/lists/:id` (Delete a list)
- [ ] GET `/api/v1/lists/:id/accounts` (View accounts in a list)
- [ ] POST `/api/v1/lists/:id/accounts` (Add accounts to a list)
- [ ] DELETE `/api/v1/lists/:id/accounts` (Remove accounts from list)

### Markers

https://docs.joinmastodon.org/methods/markers/

- [ ] GET `/api/v1/markers` (Get saved timeline positions)
- [ ] POST `/api/v1/markers` (Save your position in a timeline)

### Notifications

https://docs.joinmastodon.org/methods/notifications/

- [x] GET `/api/v1/notifications` (Get all notifications)
- [x] GET `/api/v1/notifications/:id` (Get a single notifications)
- [x] POST `/api/v1/notifications/clear` (Dismiss all notifications)
- [ ] POST `/api/v1/notifications/:id/dismiss` (Dismiss a single notification)
- [ ] POST `/api/v1/notifications/dismiss` ((REMOVED) Dismiss a single notification)

### Push

https://docs.joinmastodon.org/methods/push/

- [ ] POST `/api/v1/push/subscription` (Subscribe to push notifications)
- [ ] GET `/api/v1/push/subscription` (Get current subscription)
- [ ] PUT `/api/v1/push/subscription` (Change type of notifications)
- [ ] DELETE `/api/v1/push/subscription` (Remove current subscription)

### Search

https://docs.joinmastodon.org/methods/search/

- [ ] GET `/api/v2/search` (Perform a search)
- [x] GET `/api/v1/search` ((REMOVED) Search results (v1))

### Instance

https://docs.joinmastodon.org/methods/instance/

- [ ] GET `/api/v2/instance` (View server information)
- [ ] GET `/api/v1/instance/peers` (List of connected domains)
- [ ] GET `/api/v1/instance/activity` (Weekly activity)
- [ ] GET `/api/v1/instance/rules` (List of rules)
- [ ] GET `/api/v1/instance/domain_blocks` (View moderated servers)
- [ ] GET `/api/v1/example` (View extended description)
- [x] GET `/api/v1/instance` ((DEPRECATED) View server information (V1))

### Trends

https://docs.joinmastodon.org/methods/trends/

- [ ] GET `/api/v1/trends/tags` (View trending tags)
- [ ] GET `/api/v1/trends/statuses` (View trending statuses)
- [ ] GET `/api/v1/trends/links` (View trending links)

### Directory

https://docs.joinmastodon.org/methods/directory/

- [ ] GET `/api/v1/directory` (View profile directory)

### Custom Emojis

https://docs.joinmastodon.org/methods/custom_emojis/

- [ ] GET `/api/v1/custom_emojis` (View all custom emoji)

### Announcements

https://docs.joinmastodon.org/methods/announcements/

- [ ] GET `/api/v1/announcements` (View all announcements)
- [ ] POST `/api/v1/announcements/:id/dismiss` (Dismiss an announcement)
- [ ] PUT `/api/v1/announcements/:id/reactions/:name` (Add a reaction to an announcement)
- [ ] DELETE `/api/v1/announcements/:id/reactions/:name` (Remove a reaction from an announcement)

### Proofs

https://docs.joinmastodon.org/methods/proofs/

...

### OEmbed

https://docs.joinmastodon.org/methods/oembed/

- [ ] GET `/api/oembed` (Get OEmbed info as JSON)

### Admin (Accounts)

https://docs.joinmastodon.org/methods/admin/accounts/

...

### Admin (Domain Blocks)

https://docs.joinmastodon.org/methods/admin/domain_blocks/

...

### Admin (Reports)

https://docs.joinmastodon.org/methods/admin/reports/

...

### Admin (Trends)

https://docs.joinmastodon.org/methods/admin/trends/

...

### Admin (Canonical Email Blocks)

https://docs.joinmastodon.org/methods/admin/canonical_email_blocks/

...

### Admin (Dimensions)

https://docs.joinmastodon.org/methods/admin/dimensions/

...

### Admin (Domain Allows)

https://docs.joinmastodon.org/methods/admin/domain_allows/

...

### Admin (Email Domain Blocks)

https://docs.joinmastodon.org/methods/admin/email_domain_blocks/

...

### Admin (IP Blocks)

https://docs.joinmastodon.org/methods/admin/ip_blocks/

...

### Admin (Measures)

https://docs.joinmastodon.org/methods/admin/measures/

...

### Admin (Retention)

https://docs.joinmastodon.org/methods/admin/retention/

...


===========================


### Follows

- [x] POST `/api/v1/follows`


### Reports

- [x] GET `/api/v1/reports`





### Streaming

https://docs.joinmastodon.org/methods/streaming/

(since v1.0.0)

- [x] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`


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

### Rx Streaming

(since v1.0.0)

- [ ] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`
