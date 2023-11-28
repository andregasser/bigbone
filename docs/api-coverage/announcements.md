---
title: Announcements
layout: default
parent: API Coverage
nav_order: 35
---

# Announcements

For announcements set by administration.

<a href="https://docs.joinmastodon.org/methods/announcements/" target="_blank">https://docs.joinmastodon.org/methods/announcements/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/announcements</code><br>View all announcements</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully implemented</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/announcements/:id/dismiss</code><br>Dismiss an announcement</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully implemented</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>PUT /api/v1/announcements/:id/reactions/:name</code><br>Add a reaction to an announcement</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully implemented</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>DELETE /api/v1/announcements/:id/reactions/:name</code><br>Remove a reaction from an announcement</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully implemented</td>
  </tr>
</table>
