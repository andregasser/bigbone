---
title: Suggestions
layout: default
parent: API Coverage
nav_order: 17
---

# Suggestions

Server-generated suggestions on who to follow, based on previous positive interactions.

<a href="https://docs.joinmastodon.org/methods/suggestions/" target="_blank">https://docs.joinmastodon.org/methods/suggestions/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v2/suggestions</code><br>View follow suggestions (v2)</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>DELETE /api/v1/suggestions/:account_id</code><br>Remove a suggestion</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/suggestions</code><br>(DEPRECATED) View follow suggestions (v1)</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;"><b>Will not be implemented.</b></td>
  </tr>
</table>
