---
title: Polls
layout: default
parent: API Coverage
nav_order: 21
---

# Polls

View and vote on polls attached to statuses. To discover poll ID, you will need to GET a Status first and then check for a `poll` property.

<a href="https://docs.joinmastodon.org/methods/polls/" target="_blank">https://docs.joinmastodon.org/methods/polls/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/polls/:id</code><br>View a poll</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/polls/:id/votes</code><br>Vote on a poll</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
</table>
