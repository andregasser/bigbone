---
title: Accounts
layout: default
parent: API Coverage
nav_order: 4
---

# Accounts

Methods concerning accounts and profiles.

<a href="https://docs.joinmastodon.org/methods/accounts/" target="_blank">https://docs.joinmastodon.org/methods/accounts/</a>

<table style="width:100%;table-layout:fixed;">
  <tr>
    <th style="width:45%;text-align:left;">Endpoint</th>
    <th style="width:10%;text-align:center;">Status</th>
    <th style="width:45%;text-align:left;">Comments</th>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts</code><br>Register an account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;">Error response with details not propagated. Otherwise, fully implemented.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/verify_credentials</code><br>Verify account credentials</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;">Should return <code>CredentialAccount</code> instead of <code>Account</code>.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>PATCH /api/v1/accounts/update_credentials</code><br>Update account credentials</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>locked</code>, <code>bot</code>, <code>discoverable</code>, <code>fields_attributes</code>, <code>source</code> query parameters are missing.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id</code><br>Get account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id/statuses</code><br>Get account's statuses</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>exclude_reblogs</code>, <code>tagged</code> query parameters are missing.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id/followers</code><br>Get account's followers</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id/following</code><br>Get account's following</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id/featured_tags</code><br>Get account's featured tags</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id/lists</code><br>Get lists containing this account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/:id/follow</code><br>Follow account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>reblogs</code>, <code>notify</code>, <code>languages</code> query parameters are missing.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/unfollow</code><br>Unfollow account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/remove_from_followers</code><br>Remove account from followers</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/block</code><br>Block account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/unblock</code><br>Unblock account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/mute</code><br>Mute account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>notifications</code>, <code>duration</code> form data parameters are missing.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/unmute</code><br>Unmute account</td>
    <td style="width:10%;text-align:center;"><img src="/assets/green16.png"></td>
    <td style="width:45%;text-align:left;">Fully supported.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/pin</code><br>Feature account on your profile</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/unpin</code><br>Unfeature account from profile</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>POST /api/v1/accounts/:id/note</code><br>Set private note on profile</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/relationships</code><br>Check relationships to other accounts</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>with_suspended</code> parameter missing from query parameters. Will default to false.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/familiar_followers</code><br>Find familiar followers</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/search</code><br>Search for matching accounts</td>
    <td style="width:10%;text-align:center;"><img src="/assets/orange16.png"></td>
    <td style="width:45%;text-align:left;"><code>offset</code> (defaults to 0), <code>resolve</code> (defaults to false), <code>following</code> (defaults to false) query parameters are missing.</td>
  </tr>
  <tr>
    <td style="width:45%;text-align:left;"><code>GET /api/v1/accounts/lookup</code><br>Lookup account ID from Webfinger address</td>
    <td style="width:10%;text-align:center;"><img src="/assets/red16.png"></td>
    <td style="width:45%;text-align:left;">Not implemented yet.</td>
  </tr>
</table>
