---
title: Grouped Notifications
layout: default
parent: API Coverage
nav_order: 38
---

# Grouped Notifications

Receive grouped notifications for activity on your account or statuses.

<a href="https://docs.joinmastodon.org/methods/grouped_notifications/" target="_blank">https://docs.joinmastodon.org/methods/grouped_notifications/</a>

| Method                                          | Description                                               | Status                          | Comments          | 
|-------------------------------------------------|-----------------------------------------------------------|---------------------------------|-------------------|
| `GET /api/v2/notifications`                     | Get all grouped notifications                             | <img src="/assets/green16.png"> | Fully supported   |
| `GET /api/v2/notifications/:group_key`          | Get a single notification group                           | <img src="/assets/green16.png"> | Fully supported   |
| `POST /api/v2/notifications/:group_key/dismiss` | Dismiss a single notification group                       | <img src="/assets/green16.png"> | Fully supported   |
| `GET /api/v2/notifications/:group_key/accounts` | Get accounts of all notifications in a notification group | <img src="/assets/green16.png"> | Fully supported   |
| `GET /api/v2/notifications/unread_count`        | Get the number of unread notifications                    | <img src="/assets/red16.png">   | Not yet supported |
