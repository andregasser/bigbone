---
title: Tags
layout: default
parent: API Coverage
nav_order: 18
---

# Tags

View information about or follow/unfollow hashtags.

<a href="https://docs.joinmastodon.org/methods/tags/" target="_blank">https://docs.joinmastodon.org/methods/tags/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/tags/:id</code><br>View information about a single tag</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/tags/:id/follow</code><br>Follow a hashtag</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;">Idempotency was added in Mastodon 4.1.0 but is not yet handled.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/tags/:id/unfollow</code><br>Unfollow a hashtag</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
</table>
