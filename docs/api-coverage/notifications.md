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
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>max_id</code>, <code>since_id</code>, <code>min_id</code>, <code>limit</code>code>, <code>types</code>, <code>account_id</code> query parameters are missing. <code>Notification</code> entity needs to be updated.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/notifications/:id</code><br>Get a single notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>Notification</code> entity needs to be updated.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/clear</code><br>Dismiss all notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/:id/dismiss</code><br>Dismiss a single notification</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/notifications/dismiss</code><br>(REMOVED) Dismiss a single notification</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
</table>
