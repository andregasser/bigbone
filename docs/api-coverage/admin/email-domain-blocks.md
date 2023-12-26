---
title: Email Domain Blocks
layout: default
parent: Admin
grand_parent: API Coverage
nav_order: 8
---

# Email Domain Blocks

<a href="https://docs.joinmastodon.org/methods/admin/email_domain_blocks/" target="_blank">https://docs.joinmastodon.org/methods/admin/email_domain_blocks/</a>

| Method                                         | Description                        | Status                          | Comments        | 
|------------------------------------------------|------------------------------------|---------------------------------|-----------------|
| `GET /api/v1/admin/email_domain_blocks`        | List all blocked email domains     | <img src="/assets/green16.png"> | Fully supported |
| `GET /api/v1/admin/email_domain_blocks/:id`    | Get a single blocked email domain  | <img src="/assets/green16.png"> | Fully supported |
| `POST /api/v1/admin/email_domain_blocks`       | Block an email domain from signups | <img src="/assets/green16.png"> | Fully supported |
| `DELETE /api/v1/admin/email_domain_blocks/:id` | Delete an email domain block       | <img src="/assets/green16.png"> | Fully supported |
