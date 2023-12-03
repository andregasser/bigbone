---
title: Timelines
layout: default
parent: API Coverage
nav_order: 23
---

# Timelines

Read and view timelines of statuses.

<a href="https://docs.joinmastodon.org/methods/timelines/" target="_blank">https://docs.joinmastodon.org/methods/timelines/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/timelines/public</code><br>View public timelines</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/timelines/tag/:hashtag</code><br>View hashtag timeline</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/timelines/home</code><br>View home timeline</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/timelines/list/:list_id</code><br>View list timeline</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/timelines/direct</code><br>(DEPRECATED) View direct timeline</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Won’t be implemented.</td>
  </tr>
</table>
