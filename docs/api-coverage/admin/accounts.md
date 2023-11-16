---
title: Accounts
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 1
---

# Accounts

<a href="https://docs.joinmastodon.org/methods/admin/accounts/" target="_blank">https://docs.joinmastodon.org/methods/admin/accounts/</a>

| Method                                        | Description                          | Status | Comments | 
|-----------------------------------------------|--------------------------------------|--------|----------|
| `GET /api/v1/admin/accounts`                  | View accounts (v1)                   | 🔴     |          |
| `GET /api/v2/admin/accounts`                  | View accounts (v2)                   | 🔴     |          |
| `GET /api/v1/admin/accounts/:id`              | View a specific account              | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/approve`     | Approve a pending account            | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/reject`      | Reject a pending account             | 🔴     |          |
| `DELETE /api/v1/admin/accounts/:id`           | Delete an account                    | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/action`      | Perform an action against an account | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/enable`      | Enable a currently disabled account  | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/unsilence`   | Unsilence an account                 | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/unsuspend`   | Unsuspend an account                 | 🔴     |          |
| `POST /api/v1/admin/accounts/:id/unsensitive` | Unmark an account as sensitive       | 🔴     |          |
