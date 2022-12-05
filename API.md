# Implementation Progress

## Methods

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
- [x] POST `/api/v1/apps`
- [x] GET `/api/v1/blocks`
- [x] GET `/api/v1/favourites`
- [x] GET `/api/v1/follow_requests`
- [x] POST `/api/v1/follow_requests/:id/authorize`
- [x] POST `/api/v1/follow_requests/:id/reject`
- [x] POST `/api/v1/follows`
- [x] GET `/api/v1/instance`
- [x] POST `/api/v1/media`
- [x] GET `/api/v1/mutes`
- [x] GET `/api/v1/notifications`
- [x] GET `/api/v1/notifications/:id`
- [x] POST `/api/v1/notifications/clear`
- [x] GET `/api/v1/reports`
- [x] POST `/api/v1/reports`
- [x] GET `/api/v1/search`
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
- [x] GET `/api/v1/timelines/home`
- [x] GET `/api/v1/timelines/public`
- [x] GET `/api/v1/timelines/tag/:hashtag`

## Streaming

v1.0.0 or later

- [x] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`

## Auth

- [x] Generate Url for OAuth `/oauth/authorize`
- [x] POST password authorize `/oauth/token` v0.0.2 or later
- [x] POST `/oauth/token`

## Rx

v0.0.2 or later

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

## Rx Streaming

v1.0.0 or later

- [ ] `GET /api/v1/streaming/user`
- [x] `GET /api/v1/streaming/public`
- [x] `GET /api/v1/streaming/public/local`
- [x] `GET /api/v1/streaming/hashtag`
- [x] `GET /api/v1/streaming/hashtag/local`