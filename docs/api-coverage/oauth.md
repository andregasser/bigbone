---
title: OAuth
layout: default
parent: API Coverage
nav_order: 2
---

# OAuth

Generate and manage OAuth tokens.

<a href="https://docs.joinmastodon.org/methods/oauth/" target="_blank">https://docs.joinmastodon.org/methods/oauth/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /oauth/authorize</code><br>Authorize a user</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>force_login</code>, <code>lang</code> query parameters are missing.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /oauth/token</code><br>Obtain a token</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /oauth/revoke</code><br>Revoke a token</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
</table>
