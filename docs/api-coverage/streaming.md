---
title: Streaming
layout: default
parent: API Coverage
nav_order: 27
---

# Streaming

Subscribe to server-sent events for real-time updates via a long-lived HTTP connection or via WebSocket.

<a href="https://docs.joinmastodon.org/methods/streaming/" target="_blank">https://docs.joinmastodon.org/methods/streaming/</a>

<b>All methods currently lack proper documentation via kDoc comments.</b>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/health</code><br>Check if the server is alive</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/user</code><br>Watch your home timeline and notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>filters_changed</code>, <code>announcement</code>, <code>announcement.reaction</code>, <code>announcement.delete</code>, <code>status.update</code> event types not supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/user/notification</code><br>Watch your notifications</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/public</code><br>Watch the federated timeline</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>only_media</code> query parameter not supported. <code>delete</code>code>, <code>status.update</code> event types not supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/public/local</code><br>Watch the local timeline</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>only_media</code> query parameter not supported. <code>delete</code>, <code>status.update</code> event types not supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/public/remote</code><br>Watch for remote statuses</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/hashtag</code><br>Watch the public timeline for a hashtag</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>delete</code>, <code>status.update</code> event types not supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/hashtag/local</code><br>Watch the local timeline for a hashtag</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>delete</code>, <code>status.update</code> event types not supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/list</code><br>Watch for list updates</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>status.update</code> event types not supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/streaming/direct</code><br>Watch for direct messages</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
</table>
