---
title: Domain Blocks
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 2
---

# Domain Blocks

<a href="https://docs.joinmastodon.org/methods/admin/domain_blocks/" target="_blank">https://docs.joinmastodon.org/methods/admin/domain_blocks/</a>

| Method                                   | Description                    | Status                          | Comments         | 
|------------------------------------------|--------------------------------|---------------------------------|------------------|
| `GET /api/v1/admin/domain_blocks`        | List all blocked domains       | <img src="/assets/green16.png"> | Fully supported. |
| `GET /api/v1/admin/domain_blocks/:id`    | Get a single blocked domain    | <img src="/assets/green16.png"> | Fully supported. |
| `POST /api/v1/admin/domain_blocks`       | Block a domain from federating | <img src="/assets/green16.png"> | Fully supported. |
| `PUT /api/v1/admin/domain_blocks/:id`    | Update a domain block          | <img src="/assets/green16.png"> | Fully supported. |
| `DELETE /api/v1/admin/domain_blocks/:id` | Remove a domain block          | <img src="/assets/green16.png"> | Fully supported. |
