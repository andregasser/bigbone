# Implementation Progress

**Mastodon4j** currently implements the checkmarked methods of the Mastodon API documented here: https://docs.joinmastodon.org/methods/

Methods listed without checkmark, or those missing from the list, are not yet implemented.
An optional version number preceding a list, or listed after an individual endpoint, identifies the first Mastodon4j version to implement this method.

## Methods

### Accounts

- [x] GET `/api/v1/accounts/:id`
- [x] GET `/api/v1/accounts/verify_credentials`
- [x] PATCH `/api/v1/accounts/update_credentials`
- [x] GET `/api/v1/accounts/:id/followers`
- [x] GET `/api/v1/accounts/:id/following`
- [x] GET `/api/v1/accounts/:id/statuses`
- [x] POST `/api/v1/accounts/:id/follow`
- [x] POST `/api/v1/accounts/:id/unfollow`
- [x] POST `/api/v1/accounts/:id/block`
- [x] POST `/api/v1/accounts/:id/unblock`
- [x] POST `/api/v1/accounts/:id/mute`
- [x] POST `/api/v1/accounts/:id/unmute`
- [x] GET `/api/v1/accounts/relationships`
- [x] GET `/api/v1/accounts/search`

### Apps

- [x] POST `/api/v1/apps`

### Blocks

- [x] GET `/api/v1/blocks`

### Favourites

- [x] GET `/api/v1/favourites`

### Follow Requests

- [x] GET `/api/v1/follow_requests`
- [x] POST `/api/v1/follow_requests/:id/authorize`
- [x] POST `/api/v1/follow_requests/:id/reject`

### Follows

- [x] POST `/api/v1/follows`

### Instance

- [x] GET `/api/v1/instance`

### Media

- [x] POST `/api/v1/media`

### Mutes

- [x] GET `/api/v1/mutes`

### Notifications

- [x] GET `/api/v1/notifications`
- [x] GET `/api/v1/notifications/:id`
- [x] POST `/api/v1/notifications/clear`

### OAuth

- [x] Generate Url for OAuth `/oauth/authorize`
- [x] POST password authorize `/oauth/token` (v0.0.2)
- [x] POST `/oauth/token`

### Reports

- [x] GET `/api/v1/reports`
- [x] POST `/api/v1/reports`

### Search

- [x] GET `/api/v1/search`

### Statuses

- [x] GET `/api/v1/statuses/:id`
- [x] GET `/api/v1/statuses/:id/context`
- [x] GET `/api/v1/statuses/:id/card`
- [x] GET `/api/v1/statuses/:id/reblogged_by`
- [x] GET `/api/v1/statuses/:id/favourited_by`
- [x] POST `/api/v1/statuses`
- [x] DELETE `/api/v1/statuses/:id`
- [x] POST `/api/v1/statuses/:id/reblog`
- [x] POST `/api/v1/statuses/:id/unreblog`
- [x] POST `/api/v1/statuses/:id/favourite`
- [x] POST `/api/v1/statuses/:id/unfavourite`

### Streaming

(since v1.0.0)

- [x] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`

### Timelines

- [x] GET `/api/v1/timelines/home`
- [x] GET `/api/v1/timelines/public`
- [x] GET `/api/v1/timelines/tag/:hashtag`


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