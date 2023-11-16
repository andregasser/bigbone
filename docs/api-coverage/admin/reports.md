---
title: Reports
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 3
---

# Reports

<a href="https://docs.joinmastodon.org/methods/admin/reports/" target="_blank">https://docs.joinmastodon.org/methods/admin/reports/</a>

| Method                                          | Description             | Status | Comments | 
|-------------------------------------------------|-------------------------|--------|----------|
| `GET /api/v1/admin/reports`                     | View all reports        | 🔴     |          |
| `GET /api/v1/admin/reports/:id`                 | View a single report    | 🔴     |          |
| `PUT /api/v1/admin/reports/:id`                 | Update a report         | 🔴     |          |
| `POST /api/v1/admin/reports/:id/assign_to_self` | Assign report to self   | 🔴     |          |
| `POST /api/v1/admin/reports/:id/unassign`       | Unassign report         | 🔴     |          |
| `POST /api/v1/admin/reports/:id/resolve`        | Mark report as resolved | 🔴     |          |
| `POST /api/v1/admin/reports/:id/reopen`         | Reopen a closed report  | 🔴     |          |
