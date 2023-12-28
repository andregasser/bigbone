---
title: Accounts
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 1
---

# Accounts

<a href="https://docs.joinmastodon.org/methods/admin/accounts/" target="_blank">https://docs.joinmastodon.org/methods/admin/accounts/</a>

| Method                                        | Description                          | Status                          | Comments                          | 
|-----------------------------------------------|--------------------------------------|---------------------------------|-----------------------------------|
| `GET /api/v1/admin/accounts`                  | View accounts (v1)                   | <img src="/assets/red16.png">   | Deprecated. Won’t be implemented. |
| `GET /api/v2/admin/accounts`                  | View accounts (v2)                   | <img src="/assets/green16.png"> | Fully supported                   |
| `GET /api/v1/admin/accounts/:id`              | View a specific account              | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/approve`     | Approve a pending account            | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/reject`      | Reject a pending account             | <img src="/assets/green16.png"> | Fully supported                   |
| `DELETE /api/v1/admin/accounts/:id`           | Delete an account                    | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/action`      | Perform an action against an account | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/enable`      | Enable a currently disabled account  | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/unsilence`   | Unsilence an account                 | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/unsuspend`   | Unsuspend an account                 | <img src="/assets/green16.png"> | Fully supported                   |
| `POST /api/v1/admin/accounts/:id/unsensitive` | Unmark an account as sensitive       | <img src="/assets/green16.png"> | Fully supported                   |
