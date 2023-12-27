---
title: Media
layout: default
parent: API Coverage
nav_order: 20
---

# Media

Attach media to authored statuses.

<a href="https://docs.joinmastodon.org/methods/media/" target="_blank">https://docs.joinmastodon.org/methods/media/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v2/media</code><br>Upload media as an attachment (async)</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not yet implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/media/:id</code><br>Get media attachment</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>PUT /api/v1/media/:id</code><br>Update media attachment</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/media</code><br>(DEPRECATED) Upload media as an attachment</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
</table>
