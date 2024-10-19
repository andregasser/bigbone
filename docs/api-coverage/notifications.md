---
title: Notifications
layout: default
parent: API Coverage
nav_order: 28
---

# Notifications

Receive notifications for activity on your account or statuses.

<a href="https://docs.joinmastodon.org/methods/notifications/" target="_blank">https://docs.joinmastodon.org/methods/notifications/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications</code><br>Get all notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications/:id</code><br>Get a single notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/clear</code><br>Dismiss all notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/:id/dismiss</code><br>Dismiss a single notification</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/dismiss</code><br>(REMOVED) Dismiss a single notification</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;"><b>Will not be implemented</b>.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications/unread_count</code><br>Get the number of unread notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v2/notifications/policy</code><br>Get the filtering policy for notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>PATCH /api/v2/notifications/policy</code><br>Update the filtering policy for notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications/requests</code><br>Get all notification requests</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications/requests/:id</code><br>Get a single notification requests</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/requests/:id/accept</code><br>Accept a single notification request</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/requests/:id/dismiss</code><br>Dismiss a single notification request</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/requests/accept</code><br>Accept multiple notification requests</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/requests/dismiss</code><br>Dismiss multiple notification requests</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications/requests/merged</code><br>Check if accepted notification requests have been merged</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
</table>
