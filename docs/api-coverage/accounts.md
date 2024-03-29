---
title: Accounts
layout: default
parent: API Coverage
nav_order: 4
---

# Accounts

Methods concerning accounts and profiles.

<a href="https://docs.joinmastodon.org/methods/accounts/" target="_blank">https://docs.joinmastodon.org/methods/accounts/</a>

| Method                                            | Description                              |               Status               | Comments                                                                  | 
|:--------------------------------------------------|------------------------------------------|:----------------------------------:|---------------------------------------------------------------------------|
| `POST /api/v1/accounts`                           | Register an account                      | <img src="/assets/orange16.png" /> | Error response with details not propagated. Otherwise, fully implemented. |
| `GET /api/v1/accounts/verify_credentials`         | Verify account credentials               | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `PATCH /api/v1/accounts/update_credentials`       | Update account credentials               | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id`                        | Get account                              | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id/statuses`               | Get account’s statuses                   | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id/followers`              | Get account’s followers                  | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id/following`              | Get account’s following                  | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id/featured_tags`          | Get account’s featured tags              | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id/lists`                  | Get lists containing this account        | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/:id/follow`                 | Follow account                           | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/unfollow`              | Unfollow account                         | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/remove_from_followers` | Remove account from followers            | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/block`                 | Block account                            | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/unblock`               | Unblock account                          | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/mute`                  | Mute account                             | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/unmute`                | Unmute account                           | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/pin`                   | Feature account on your profile          | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/unpin`                 | Unfeature account from profile           | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `POST /api/v1/accounts/:id/note`                  | Set private note on profile              | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/relationships`              | Check relationships to other accounts    | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/familiar_followers`         | Find familiar followers                  | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/search`                     | Search for matching accounts             | <img src="/assets/green16.png" />  | Fully supported.                                                          |
| `GET /api/v1/accounts/lookup`                     | Lookup account ID from WebFinger address | <img src="/assets/green16.png" />  | Fully supported.                                                          |
