-- ---------------------------------------------------------------------------
-- Accounts
-- ---------------------------------------------------------------------------
INSERT INTO public.accounts
(id, username, "domain", private_key, public_key, created_at, updated_at, note, display_name, uri, url, avatar_file_name, avatar_content_type, avatar_file_size, avatar_updated_at, header_file_name, header_content_type, header_file_size, header_updated_at, avatar_remote_url, "locked", header_remote_url, last_webfingered_at, inbox_url, outbox_url, shared_inbox_url, followers_url, protocol, memorial, moved_to_account_id, featured_collection_url, fields, actor_type, discoverable, also_known_as, silenced_at, suspended_at, hide_collections, avatar_storage_schema_version, header_storage_schema_version, devices_url, suspension_origin, sensitized_at, trendable, reviewed_at, requested_review_at)
VALUES(109921877420063741, 'user1', NULL, '-----BEGIN RSA PRIVATE KEY-----
MIIEpAIBAAKCAQEAxfqT0p1sPXk5jtscQ1uauYJ4dnXElTpZNO1RHN6qfR+O0jCi
9UyNfpK5lNDOMRKHBARlvxukJRicbenYirtxS3B5mQyadIymCSr5wXBllcDbmzqN
5z61SonPeFTvw3QB6iDRMErAXlJH6jRQQsc71Qkl20W7tNeMpGgAzxhxrlU002h7
zNpWD/9YPFdaH3wRK2SPpRcBeIcPT4wcC/Kkzw1bu5LP4RpMSiVd0uYS5fMBHHcU
7OXrSqs2K6wmusdllMAzrogQ82tAT8NhpfNzoA3pHKCSOx0yWR/Fww4GCF9ex7P5
qjKYSKfI8clIIY2wA3iZHZvQsrJyH8NWKqhUDwIDAQABAoIBAQCNszK4bVo6iZiL
ygHpqBgPLPYBe3svf+q3eiAzNJ/8HGlBwfPw9FDVxjiz7duWvfcLBga5CRSsRHg6
J0SIz25gm2k9BQtL3hs8tpi6PjNHQxkrenZ0i6qAZjCrpMdTMqXoKILxOJ+FIwk7
9SN5GFaXvQtubPPTlO+iAJqLn6hGKI20DhErLB+e4O2EFaJWmfuBJV2lHh5iReHc
lwHOa1auV+qd8OVW2hT4sIElA2Ht8KJFmqUkpT17WT2zVHBdQiAUnoAPH4Rmqg7m
9jMWuTopsjiv8daF2777EVvXTltjXO+XOml9MfWWFyrG2UkXh/yupDU42wHRpkJ2
gfSvLZ15AoGBAOqIZfgTczolFhmOk25AShK2upFYqhfPsZTS5TTxRngt6HH70WJG
fI/M60EZ2Cyv9nmAvX+MWbmekfcsWyD8123QCjvtMIPMzX/lylVPReC8B7Xw5tvV
QYnf0s4FOa1YOztqklBf8iYWzmVVUSbbhGx2MxOX4o6PSiV3oP5nV8TTAoGBANgZ
pD0VyZ1SrHHnYABNyVgA4bB7OGLPkKmqVTYL0O+s2PWztTT/wRYPiC3A1BkZ780q
CbgMOFTs2WRNa2f7BCi9rDuxj5e6CNwKTk6BzWRra38XNn+F9rOQkIiONfUZYkdZ
sXRU4XaLTmENL1TOKM7EOvNwL8s9oXvP0Grpt95VAoGAZ6idmQXPufVGTUls9B/K
HXdSAoeztg6AKI0F7V5ujRONV0O7ibopt80UdZOcrjOftpgmKUe5KIdjcGHUbUIT
XV/EBjSRas+MTGkeApKlCe1RYyL0hlk9PFkG7TP2CB2lIF+8B3ZAqoEN/E+3LHt9
jgq4DILnCadfWCwFpiu66S0CgYEAxi6l39uGXgB+46sk8MJlv453bEllCcF/0wnC
QeGOSPAEvTiFG8jdhGTUJ9mq6iZONKKz7RVP6BqauBEESoT7aH2iYdBSdvyGuX2+
GOOTwN9+4zwfn8301PLkikeZlmEkL3lDdAOQDg5t4DWeTbEP3ag6xB0Dx+mKmiiY
5CPZm00CgYA/WY8uPxnNbMpjD4dx2mNaCmTjUrnHU9QH72UpX8Yd3Ty3yTubAgMH
CqK1rOWAZkxRjX/rWRhLfIOSK7vHzt7ocleglc1zvpdzgUVYP/Qgg4fPaEjpXl7E
3zDvf4RzaEMPxBR2nhwvGzBByOrOfIkgSgLKsFjR1FGdGIirNAmo/A==
-----END RSA PRIVATE KEY-----
', '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxfqT0p1sPXk5jtscQ1ua
uYJ4dnXElTpZNO1RHN6qfR+O0jCi9UyNfpK5lNDOMRKHBARlvxukJRicbenYirtx
S3B5mQyadIymCSr5wXBllcDbmzqN5z61SonPeFTvw3QB6iDRMErAXlJH6jRQQsc7
1Qkl20W7tNeMpGgAzxhxrlU002h7zNpWD/9YPFdaH3wRK2SPpRcBeIcPT4wcC/Kk
zw1bu5LP4RpMSiVd0uYS5fMBHHcU7OXrSqs2K6wmusdllMAzrogQ82tAT8NhpfNz
oA3pHKCSOx0yWR/Fww4GCF9ex7P5qjKYSKfI8clIIY2wA3iZHZvQsrJyH8NWKqhU
DwIDAQAB
-----END PUBLIC KEY-----
', '2023-02-24 21:39:01.103', '2023-02-24 21:39:01.103', '', 'User 1', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, '', NULL, '', '', '', '', 0, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.accounts
(id, username, "domain", private_key, public_key, created_at, updated_at, note, display_name, uri, url, avatar_file_name, avatar_content_type, avatar_file_size, avatar_updated_at, header_file_name, header_content_type, header_file_size, header_updated_at, avatar_remote_url, "locked", header_remote_url, last_webfingered_at, inbox_url, outbox_url, shared_inbox_url, followers_url, protocol, memorial, moved_to_account_id, featured_collection_url, fields, actor_type, discoverable, also_known_as, silenced_at, suspended_at, hide_collections, avatar_storage_schema_version, header_storage_schema_version, devices_url, suspension_origin, sensitized_at, trendable, reviewed_at, requested_review_at)
VALUES(109921883647414033, 'user2', NULL, '-----BEGIN RSA PRIVATE KEY-----
MIIEowIBAAKCAQEA12jBL3CSwJkHAgToDZnvN4Z/rR5zs8saz7LxgS+hygwsEd7k
QwPDrPwj4rSEPZ/T8BtjGnY/WYKrP8actio3SmrA6KxyXOHXnaFO4FdhK8cSkXYs
7kk+gLl3WuAosLf5gOIhAsI0FCP0Oof66N8icoSPeSJ0qFJ7NdiLlI/8uXTzZfYY
Y6b68uLybDigCxqTZgA3WIqwupBE9VarVs2kmVrGJt1jOgSpbM29f/Erkh7d56SF
nqWkzPDr7EOnjXcyxOKqAf0+Zfg0pQCyQKSF+zH634CX/Sd52zOLpTozWrvRfgKJ
Ktj/0nBwnCpZbGrd2cjHkKZhtw79nw56WfFcmQIDAQABAoIBAEnlA7CUe6NaW48T
w882qVtw5kerFKHu4MmzZymb/3JibpPK1J8woWWsMVtJ9bt2IqxRmNOxENZR/xEn
vwa2wLTknjyzO6AlUWQfvAE1uH3eEr6XvZ4YvjpGZW1lTFAjLJJMAGsD1/24v8V8
dFSChzylwNobOc2sTdwQ5wEVy+qKVP5meis0dVFz0DjMGgZxEAjjv3AZpqlr6jxQ
vUVt4G4rRBypXV2FrlymOx7HRmgDupojvcPsKf3CCS9E+xzLY8Znx4jD3LLTTCrV
JABwgvoQp7682X4c4n21y1XwVgdK8d9b/Qy64MyBPV7D6OUvo/AnatiLAT0QACIb
e908IgkCgYEA7H9O4NJYvjJwlEU8YzPQICtFrGsywX7QoFn8WDIC4kxMrfgrDrvw
gdIE3mPOnPHKWZJQFfLbRvLdEQ4pTQkmMgjgNrq0Jg3tBxaZU7LUvOyRRhAGhVKu
3k/5U7XgSc+bDymshwehMjGsuhk/1NtYr65KTFDfebj8tGfPDbQ4BFMCgYEA6SxB
gL5MqPNydmwTxsC1qeMTLKsPNYvtsgpupMko+ccDF26DweM6WP9HUGuzufkOI5Gt
KfbgXfd0llORdME7BQp+OfkzfSfU5ZEfKr+W24r6DMreqXxTwLQxtkOeMDzMQVAC
sn1P1b4S6+p0MG2Jry+ISRoK42/bnYXpqKATfeMCgYB5SfVfMiHXMbExKa0Fx43m
4apy+etm9y3x/xR++u8ltjHhSxRyEwsO2XIryA4vyYWYmIRALXQuF+IunA+sdZPE
N28eQnSfy6aTaxCpr16RodYDk7Z0a+pJE8TwHKfx0upYRWw5OD2Aq59FNivZc8Ex
IHb0Wsmx+KCJuW+kPJg6BwKBgQCuiuRVnR4BcosCPpDnPKZAD6zwyPPPn3lExy5f
x/0WTHTx+1ZBsvigWidl+dlFozuEQK4hNZ5qeKwbQ18kyOiHM32do/949nIVjnaI
J3ajzPq0zL+0m06Od9SmnSMy9K/pTB1YwxkZ5PiBBLYI8lYYfzpDjm0MJ1V8Kxvn
kDxHBQKBgDYkakFi/j0ezPMcWKDKMTjCFvlDqcljJ/ftrLLYPqkgsuFvdGDIC4Xx
q2XXDv+LTMzt0rEJ9V2b3XsK4qSioNDgPePtC7Rq1KNh4hNxolg87337rRPROhs8
7h6z4QvRWdTEYDinlp1FtomW5MXtD1gH+p3taFR6cm4jD+RvzWzu
-----END RSA PRIVATE KEY-----
', '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA12jBL3CSwJkHAgToDZnv
N4Z/rR5zs8saz7LxgS+hygwsEd7kQwPDrPwj4rSEPZ/T8BtjGnY/WYKrP8actio3
SmrA6KxyXOHXnaFO4FdhK8cSkXYs7kk+gLl3WuAosLf5gOIhAsI0FCP0Oof66N8i
coSPeSJ0qFJ7NdiLlI/8uXTzZfYYY6b68uLybDigCxqTZgA3WIqwupBE9VarVs2k
mVrGJt1jOgSpbM29f/Erkh7d56SFnqWkzPDr7EOnjXcyxOKqAf0+Zfg0pQCyQKSF
+zH634CX/Sd52zOLpTozWrvRfgKJKtj/0nBwnCpZbGrd2cjHkKZhtw79nw56WfFc
mQIDAQAB
-----END PUBLIC KEY-----
', '2023-02-24 21:40:35.998', '2023-02-24 21:40:35.998', '', 'User 2', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, '', NULL, '', '', '', '', 0, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


-- ---------------------------------------------------------------------------
-- Users
-- ---------------------------------------------------------------------------
INSERT INTO public.users
(id, email, created_at, updated_at, encrypted_password, reset_password_token, reset_password_sent_at, sign_in_count, current_sign_in_at, last_sign_in_at, "admin", confirmation_token, confirmed_at, confirmation_sent_at, unconfirmed_email, locale, encrypted_otp_secret, encrypted_otp_secret_iv, encrypted_otp_secret_salt, consumed_timestep, otp_required_for_login, last_emailed_at, otp_backup_codes, account_id, disabled, moderator, invite_id, chosen_languages, created_by_application_id, approved, sign_in_token, sign_in_token_sent_at, webauthn_id, sign_up_ip, skip_sign_in_token, role_id)
VALUES(1, 'user1@email.dev', '2023-02-24 21:39:01.270', '2023-02-24 21:39:01.403', '$2a$10$Biv/hF9O6CpQq/.1OHWHVeUhtQUom.Jzoylw7heimat/alCOHUC6C', NULL, NULL, 0, '2023-02-24 21:39:01.402', '2023-02-24 21:39:01.402', false, 'j8NBpXMeUKTeXg3x-Gnt', '2023-02-24 21:39:50.402', '2023-02-24 21:39:01.271', NULL, 'en', NULL, NULL, NULL, NULL, false, NULL, NULL, 109921877420063741, false, false, NULL, NULL, NULL, true, NULL, NULL, NULL, '192.168.48.1'::inet, NULL, NULL);
INSERT INTO public.users
(id, email, created_at, updated_at, encrypted_password, reset_password_token, reset_password_sent_at, sign_in_count, current_sign_in_at, last_sign_in_at, "admin", confirmation_token, confirmed_at, confirmation_sent_at, unconfirmed_email, locale, encrypted_otp_secret, encrypted_otp_secret_iv, encrypted_otp_secret_salt, consumed_timestep, otp_required_for_login, last_emailed_at, otp_backup_codes, account_id, disabled, moderator, invite_id, chosen_languages, created_by_application_id, approved, sign_in_token, sign_in_token_sent_at, webauthn_id, sign_up_ip, skip_sign_in_token, role_id)
VALUES(2, 'user2@email.dev', '2023-02-24 21:40:36.188', '2023-02-24 21:40:36.312', '$2a$10$84E3EpYPu/XjEyb8wfbTQu.zlGwQVHIZzFUc2Zv.W9xT5gwxMrG.G', NULL, NULL, 0, '2023-02-24 21:40:36.312', '2023-02-24 21:40:36.312', false, 'Vj4xfvP3sNj4PBozea69', '2023-02-24 21:40:40.312', '2023-02-24 21:40:36.188', NULL, 'en', NULL, NULL, NULL, NULL, false, NULL, NULL, 109921883647414033, false, false, NULL, NULL, NULL, true, NULL, NULL, NULL, '192.168.48.1'::inet, NULL, NULL);
