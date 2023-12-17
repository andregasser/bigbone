---
title: IP Blocks
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 9
---

# IP Blocks

<a href="https://docs.joinmastodon.org/methods/admin/ip_blocks/" target="_blank">https://docs.joinmastodon.org/methods/admin/ip_blocks/</a>

| Method                               | Description                               | Status | Comments | 
|--------------------------------------|-------------------------------------------|--------|----------|
| `GET /api/v1/admin/ip_blocks`        | List all IP blocks                        | <img src="/assets/green16.png"> | Fully supported |
| `GET /api/v1/admin/ip_blocks/:id`    | Get a single IP block                     | <img src="/assets/green16.png"> | Fully supported |
| `POST /api/v1/admin/ip_blocks`       | Block an IP address range from signing up | <img src="/assets/green16.png"> | Fully supported |
| `PUT /api/v1/admin/ip_blocks/:id`    | Update a domain block                     | <img src="/assets/green16.png"> | Fully supported |
| `DELETE /api/v1/admin/ip_blocks/:id` | Delete an IP block                        | <img src="/assets/green16.png"> | Fully supported |
