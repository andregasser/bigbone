---
title: Reports
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 3
---

# Reports

<a href="https://docs.joinmastodon.org/methods/admin/reports/" target="_blank">https://docs.joinmastodon.org/methods/admin/reports/</a>

| Method                                          | Description             |              Status               | Comments         | 
|-------------------------------------------------|-------------------------|:---------------------------------:|------------------|
| `GET /api/v1/admin/reports`                     | View all reports        | <img src="/assets/green16.png" /> | Fully supported. |
| `GET /api/v1/admin/reports/:id`                 | View a single report    | <img src="/assets/green16.png" /> | Fully supported. |
| `PUT /api/v1/admin/reports/:id`                 | Update a report         | <img src="/assets/green16.png" /> | Fully supported. |
| `POST /api/v1/admin/reports/:id/assign_to_self` | Assign report to self   | <img src="/assets/green16.png" /> | Fully supported. |
| `POST /api/v1/admin/reports/:id/unassign`       | Unassign report         | <img src="/assets/green16.png" /> | Fully supported. |
| `POST /api/v1/admin/reports/:id/resolve`        | Mark report as resolved | <img src="/assets/green16.png" /> | Fully supported. |
| `POST /api/v1/admin/reports/:id/reopen`         | Reopen a closed report  | <img src="/assets/green16.png" /> | Fully supported. |
