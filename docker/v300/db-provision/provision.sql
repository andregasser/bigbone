-- ---------------------------------------------------------------------------
-- Accounts
-- ---------------------------------------------------------------------------
INSERT INTO public.accounts
(id, username, "domain", secret, private_key, public_key, remote_url, salmon_url, hub_url, created_at, updated_at, note, display_name, uri, url, avatar_file_name, avatar_content_type, avatar_file_size, avatar_updated_at, header_file_name, header_content_type, header_file_size, header_updated_at, avatar_remote_url, subscription_expires_at, "locked", header_remote_url, last_webfingered_at, inbox_url, outbox_url, shared_inbox_url, followers_url, protocol, memorial, moved_to_account_id, featured_collection_url, fields, actor_type, discoverable, also_known_as, silenced_at, suspended_at, trust_level)
VALUES(1, 'user1', NULL, '', '-----BEGIN RSA PRIVATE KEY-----
MIIEpgIBAAKCAQEA0CPMIq5WqDgPSrnuwrlrJtv/YaamZjEPW2/SxaT5Nrd4YHlU
FZ8YTN6hg40FekyJerYztj+PDok5VRJ+a/qbeiqLyEham8u+k+83KfDoCtoIVD1+
lMQxDYu0G1IErboWFTMM80S/R5Lt91Eq2cbOS00rk0xXql0xpX3zM0/a29meOnxN
fMuKjhkrfgPqjdPJOeobCPNotcAmI/h3HF+c2WFyYU2t3n6p2CKDAbo0Gflz40X7
hhDUKISrL5//DjJ0GAZGNBK8F9MrwVFX2xVs9CetkdG4v9ph5zIIyf4792ZNVFRp
cteP+31ogaateIYxr6AuYQPOxWXOwree6YQmFwIDAQABAoIBAQCSfs+cDVbi2Wox
70IUfgrj8+4Anhn6TUeA1OjlwWLC10l7pYnAGhUxbXUCsUwMszgETZ+CA6Q91+mp
bfXbLC0oRC/UTNCxXSsK1lDeTTgVAzbfi9S/AoOABDdFD2pJmHMYgrD3vAhhaRHi
g5eEZkAmke88onwFZciesVJuaCMjnveRZPoi+WDUJJPYwxs9K58GuGT6xM2Ur2sj
hITuJ7QLjqK8r7luoaNkPY7E/INpJk3q16qkmt7/uJc8OEM7GA5k76Wki7LY2Et3
iuTO+48FW+GqEPOmh1CR3LnbPuD8AFAUAfJiDrJ0BI+nt247O2LA/bLUqzoBmkZn
m5cQNyaBAoGBAOv69puNUEFmLcePbXgoX8xe5BphJp+c4elnVAkZDv1yUri898e9
mgkZCR/aLREej5WCbfEiUQGBiHFBpYFMdMU7Ru1RXbOCZIFng6WfC7XNKKriilV3
C/lxh+aRwlZnOh2CthTUXN6wix3WtlkxEHOEcfl14jiYCcPk02vOz7JXAoGBAOHM
MUGKDe0aPJuKQFtc2fz29CfgK+L30YjWX56hv/PTfNKFNCCOSWLZY6lYt10Ui4ix
UddoJFhB1cyPEpBuIr/eAc3SH0dOPMlrhbZPD5qWZOgLpHBeTohJi/z64wwUghlF
+6TxUnViGyhI2uJ8aHZHXxdXTzbQdVJ6SwNkmFJBAoGBAMIzgKu2+is4Gci3gpF2
OJCKLxJ+jTih9VEOFhx+oNV3NhiOeyPIJ3IBRn8OIDSMgAG8mxns8dlZbp42NixY
bWi7Rb2cTo6ianAMbkzovMxbEHLBiLKZDCO2iO75WYOd5DfREGPH+L3TcEmYH+z6
8dz/TITNvORySucJ8+wvAMJ1AoGBAIDDTe9/B9QHWzqjy3GOcK1GMxOuTbif8/ih
r2Op/+nO1+bBmWlbWtQo/iFrex10guHPpMk/FULTQFXn3QFKHTznIIY75iRmpFmE
nHrLeS2ByEdfpEWEMiqcLl10wRd5dnttIkT8bTaNXMlLSq9PnM6egsavvneWxuDi
5nYn3olBAoGBAMw13lp51WEz/QyEihlG6nhTRdnPt4R0+U2uYdamaY6nk7d5e8g2
iEDa9V4hbiUlZbds/2O0x66vsxsSFjz7D5RSruMIL172Adxg6uN0wU8uOBVHTcKA
knbqEd+6BQ4TQDexSdsXdBxhPNr4eAJ5qMgmeeeDknNe6K37c1ApVQmO
-----END RSA PRIVATE KEY-----
', '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0CPMIq5WqDgPSrnuwrlr
Jtv/YaamZjEPW2/SxaT5Nrd4YHlUFZ8YTN6hg40FekyJerYztj+PDok5VRJ+a/qb
eiqLyEham8u+k+83KfDoCtoIVD1+lMQxDYu0G1IErboWFTMM80S/R5Lt91Eq2cbO
S00rk0xXql0xpX3zM0/a29meOnxNfMuKjhkrfgPqjdPJOeobCPNotcAmI/h3HF+c
2WFyYU2t3n6p2CKDAbo0Gflz40X7hhDUKISrL5//DjJ0GAZGNBK8F9MrwVFX2xVs
9CetkdG4v9ph5zIIyf4792ZNVFRpcteP+31ogaateIYxr6AuYQPOxWXOwree6YQm
FwIDAQAB
-----END PUBLIC KEY-----
', '', '', '', '2023-02-19 18:05:18.915', '2023-02-19 18:05:18.915', '', 'User 1', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, '', NULL, '', '', '', '', 0, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO public.accounts
(id, username, "domain", secret, private_key, public_key, remote_url, salmon_url, hub_url, created_at, updated_at, note, display_name, uri, url, avatar_file_name, avatar_content_type, avatar_file_size, avatar_updated_at, header_file_name, header_content_type, header_file_size, header_updated_at, avatar_remote_url, subscription_expires_at, "locked", header_remote_url, last_webfingered_at, inbox_url, outbox_url, shared_inbox_url, followers_url, protocol, memorial, moved_to_account_id, featured_collection_url, fields, actor_type, discoverable, also_known_as, silenced_at, suspended_at, trust_level)
VALUES(2, 'user2', NULL, '', '-----BEGIN RSA PRIVATE KEY-----
MIIEpQIBAAKCAQEAzQuhl0oWpP81Dt3S0lsZXloQlATj1yXeojbTGYRSc2J8AE9y
IPxQg1DDY9RYEYIJL/OhkpA0L5rp/EF+COhB66OtXg1bCrlkcA3PMrayY4ejHBRT
8oNjnJwfxhXPzJ+YdoRvBzxF/yUuKnsETP6GKBKWBdS0fZcncIA0DGw2R/6Lrv8K
zwGnixK26pGpD2Qs5kwAhas53GebYJS4LqrDbykqaLtZmgR00po4PUBrBWrp/UY8
XnPrv/u+rIWo3niOWwA1K+ZpbIzU3CMzt1cY+r82c82Ml6EsNI+xDdwaqNCNHtoE
drF6LJLeyDUA6x3TgsizXsS4oB4dpI/3hZ/7GQIDAQABAoIBAQCjqYMDVNmaPTHG
efwKdzhafRz6aKTLV6jKZWQ928ZL3vIG9WKtPXeuOMqcZiMdZmOa6KWwbHWbAdV9
yXCl/2yOfwikJ5jhiHTIk90sJ3NfyiXBBeCzk3fwyxT5cPN56nExOCe+6LceHjkp
IyOdqRK/7YXdhZfDHuEWBN6Gg+EXuID2ldO9bluwWlsxEJr36lWnNg52RpWslt/o
dKZvuUF7THk0CQ1nBsHLZsBx7Ut+7jBqe8jIZrI6U/Op13BVqILgbcSfJ3GNh5II
3L4kGOgK5S8ilbVGinf+w7XM2o2m7GaTPK4+WQ9y4rp6fxmNLC3UGsuBYnF0zyKL
XDNTorYZAoGBAO/2GA0/M9LEIuRHO5Oiwb4wMrkGk6cRbPIqeuRWKrDu3NYigU8d
fuKR9Bu3AB66vokxNWLhe5ENuZhrPtJTrUVu/tsWNNRL2qhr2K9uCi4aAaEfq1MS
ciwgQKD6Z/QIijrJBFHg3vsIckRNO9oy2zXgPRBSKkuQcEB+ez5OooxrAoGBANrA
Gi3ZPc7VGXtfcHOtqIXIjNhxQb1QrYfZFaxSGv9dAOlmGDUQHv24yp7lBxnVn1+S
A2yeFbbw/NALhsfp7+sA2aM7+TTKmNNHh+ajzZtaW5jzhiElu/OsEfbtqUlRDUzi
oExCLdjiwPXLaVa8xhIjRYdeUmS5x0u1mIrSSHeLAoGAJxYL1uSLmXIvSMW9k4+4
RTVunarPlDAHce6vc9ABRi6yNAMM0wk36MnjXDxTva2naYtlacTWrsrwhz44zmOo
3p10z9TvKAFvDW37rp+a8uVzjH4vFunzRPK/9Di1hA98DJU/qeKaD4PRsQRvklwk
TsNGzwjDMOnHDwpLItwGKB8CgYEAqSTPWVJYc3zdZkMm3ZuS6ldnaNkyPuOKOrfG
BWe7Tmeok9CIuUcM350qt3BabER9JlXt5efa3Ik8wtWW0y/1fp46qvhtur9JKl1u
HQ3dptlYaE0tEqZtzX3oqimcs39DrIaz8iNd9g1nrOcrPZFcCpxRX9m0FgR8sPLq
z7THqf8CgYEAjeIfo6Xaqf2xOoFy0tkNVy7pktgOb1K4KcDlYTLSKV+f1omTVxML
KTz2r8WRJ1iHBk4mVKRadZ+m5m9ck1IuJYA9iV2zpWmtty9qwPld8sa8e6SG5TYN
7oILowpzTMSI2imcrM6Vv0cFtME68cc1I+m41hupf9eFiAdA0UeDJnY=
-----END RSA PRIVATE KEY-----
', '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzQuhl0oWpP81Dt3S0lsZ
XloQlATj1yXeojbTGYRSc2J8AE9yIPxQg1DDY9RYEYIJL/OhkpA0L5rp/EF+COhB
66OtXg1bCrlkcA3PMrayY4ejHBRT8oNjnJwfxhXPzJ+YdoRvBzxF/yUuKnsETP6G
KBKWBdS0fZcncIA0DGw2R/6Lrv8KzwGnixK26pGpD2Qs5kwAhas53GebYJS4LqrD
bykqaLtZmgR00po4PUBrBWrp/UY8XnPrv/u+rIWo3niOWwA1K+ZpbIzU3CMzt1cY
+r82c82Ml6EsNI+xDdwaqNCNHtoEdrF6LJLeyDUA6x3TgsizXsS4oB4dpI/3hZ/7
GQIDAQAB
-----END PUBLIC KEY-----
', '', '', '', '2023-02-19 18:13:04.380', '2023-02-19 18:13:04.380', '', 'User 2', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, '', NULL, '', '', '', '', 0, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


-- ---------------------------------------------------------------------------
-- Users
-- ---------------------------------------------------------------------------
INSERT INTO public.users
(id, email, created_at, updated_at, encrypted_password, reset_password_token, reset_password_sent_at, remember_created_at, sign_in_count, current_sign_in_at, last_sign_in_at, current_sign_in_ip, last_sign_in_ip, "admin", confirmation_token, confirmed_at, confirmation_sent_at, unconfirmed_email, locale, encrypted_otp_secret, encrypted_otp_secret_iv, encrypted_otp_secret_salt, consumed_timestep, otp_required_for_login, last_emailed_at, otp_backup_codes, filtered_languages, account_id, disabled, moderator, invite_id, remember_token, chosen_languages, created_by_application_id, approved)
VALUES(1, 'user1@email.dev', '2023-02-19 18:05:19.087', '2023-02-19 18:12:26.965', '$2a$10$wA/zE6GJ.jz7.WC7yMgkhedRLNfYlcTRY9GNBSB.MVlvJJZuvBABa', NULL, NULL, NULL, 2, '2023-02-19 18:09:22.827', '2023-02-19 18:05:19.258', '172.31.0.9'::inet, '172.31.0.9'::inet, false, 'dGzBfe_1e2s55V9e4HRX', '2023-02-19 18:10:19.087', '2023-02-19 18:05:19.087', NULL, 'en', NULL, NULL, NULL, NULL, false, NULL, NULL, '{}', 1, false, false, NULL, NULL, NULL, NULL, true);
INSERT INTO public.users
(id, email, created_at, updated_at, encrypted_password, reset_password_token, reset_password_sent_at, remember_created_at, sign_in_count, current_sign_in_at, last_sign_in_at, current_sign_in_ip, last_sign_in_ip, "admin", confirmation_token, confirmed_at, confirmation_sent_at, unconfirmed_email, locale, encrypted_otp_secret, encrypted_otp_secret_iv, encrypted_otp_secret_salt, consumed_timestep, otp_required_for_login, last_emailed_at, otp_backup_codes, filtered_languages, account_id, disabled, moderator, invite_id, remember_token, chosen_languages, created_by_application_id, approved)
VALUES(2, 'user2@email.dev', '2023-02-19 18:13:04.462', '2023-02-19 18:13:04.507', '$2a$10$i0SkpfnpRWRpCzxWFNbyMe2T.mHpjgFhSwdN3LbvjPpgZ7tfMvpoG', NULL, NULL, NULL, 1, '2023-02-19 18:13:04.507', '2023-02-19 18:13:04.507', '172.31.0.9'::inet, '172.31.0.9'::inet, false, 'Knxervoj_2MaLRghj2Tc', '2023-02-19 18:18:04.462', '2023-02-19 18:13:04.462', NULL, 'en', NULL, NULL, NULL, NULL, false, NULL, NULL, '{}', 2, false, false, NULL, NULL, NULL, NULL, true);
