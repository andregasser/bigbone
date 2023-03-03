-- ---------------------------------------------------------------------------
-- Accounts
-- ---------------------------------------------------------------------------
INSERT INTO public.accounts
(username, "domain", private_key, public_key, created_at, updated_at, note, display_name, uri, url, avatar_file_name, avatar_content_type, avatar_file_size, avatar_updated_at, header_file_name, header_content_type, header_file_size, header_updated_at, avatar_remote_url, "locked", header_remote_url, last_webfingered_at, inbox_url, outbox_url, shared_inbox_url, followers_url, protocol, id, memorial, moved_to_account_id, featured_collection_url, fields, actor_type, discoverable, also_known_as, silenced_at, suspended_at, hide_collections, avatar_storage_schema_version, header_storage_schema_version, devices_url, sensitized_at, suspension_origin, trendable, reviewed_at, requested_review_at)
VALUES('user1', NULL, '-----BEGIN RSA PRIVATE KEY-----
MIIEowIBAAKCAQEA3rotLA1dZ6IJpPf8pGB/7yCEYQC/nqSBFB8+UeMaKwz3RnOR
ddXjpnJ1WNj+74yuHeywhFdKcVNswctTsgRRx/9FPoDYf5/UVYkhd5rn+ZRoVqkb
zDV/NirLPOAifeng/TlKg+AudZu6usY6XQTt40t5PflA6MrlaW7DSgfLvks/DAwZ
a9fOxenExe1A0+8nAfurVwbOBqepyAgdo5uwGkZzaPLFBX+cmSrh0TzD7hvA8wGC
o11yI2RrPBwWD+dHN63f5vdSRdDsASEJ0pOSzJpn9pABpdqF4VyVrR35kbamR2zk
mbETzPnTR6qpjrunCJWxCE+pHZRLGmwQ1DSDTQIDAQABAoIBACEIi9qUwN218qg8
/4OBrHfS4s9gRQtKarqJR7ztadwouCQCz/giGPl+0Fi1FlqBCvH1QNjf+XKFZQP5
HBiy+04mVibHBVUoZ12oUQZkOzORB0iTHERVSuDFyA7nTzZNkcj8zkhw33Mh/bfl
3pIdQQsNDpZaeX4NWakhfGVmcpluWLEE1CbaRAX/2pfsdMzGlHhSwmLL1bLCl748
m/igp6/IiOLDVE6SLj3mnXN83oiF6zXz4bKSBs9QzDY+Cq82kC/R/o7Xy89UvOWR
Jk17jlJP5NPScBe8UgeoawnuVgCrp3RQ3Mn3WzOaPQT/P+pDmI7kLjw/A2DPbksM
Xmf+BwECgYEA+EK5Qsv3l4v6H+CKtTtuKFdI+Xn5KYEc+ewK/HxgK9qt7e2Hxu1p
1O/q/NklgFRm6Uc4+qL+VgXLGIawIF5Z0dLMdjruPuCb5POF1tF8jpvv4DvVhkb8
FtfeqPxTfh8ny9UW+sACS5Pcd6CebusvEj7qPfBYFZPN/lu0mxYADaUCgYEA5auu
JVRGwCyPVrt7Ep8oQeip/BgYlgU30OrVdbdwmsgfj+4jYRKlj5zmriG/j09sQ3J/
N44TAsvbmb1ec1fC/fhQEqGLN96OQLlgu8jIvyMEhYZImd1iExE5Kl0bZIHlPKIn
862/gIHfm03PNR7tN83CR+OTSw/qkFBzXZu+fokCgYAq1+j83BJYdvGteqS1bhIu
qBGR7DjRrtR/VPr4Y8Z9CvolK27ZJ7Ox6c2V4SuVn/s6mu38YxCda9aG3weaLm41
xbk/ViAzfbOpqVUZo4FpYwPcoLbSu4FZt9tXMmR+SYYmqRmUIHrE7DmJ85Aj+DZh
YbYvBkFNR/quwMjEUuGjeQKBgQCAihhaDhPW5FmI2j7kSi1WzeetDrNb6ym1BGlZ
i8Q67Hw1qrskTjJY8SBuHrc+dt4J1Z/7sMlVDFEod0LQXTLlvz7ZXo4zQ5zr/pR0
JMitqOST8nbZnnyQXVhYLGnzU3u/9qVTjU+blZRXMDIi8d8kF0V/xI+gOBU4lb4r
isoPuQKBgGHHfSASXYElGDhob9HbyrzTCpmow6JRo5hH3lB9yLuOrYjKoJOWC/ve
lCmFb7jPehmohajThwrPxrjQ+3CvGzq8dIW9AFgj1Lc0Z+5vhFtuFsh0cFznqIup
4lVqg0cIeNOnX3nRIkLFUP7fqbfaqvQryAE2gn5EhahjKXQJj1Cc
-----END RSA PRIVATE KEY-----
', '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3rotLA1dZ6IJpPf8pGB/
7yCEYQC/nqSBFB8+UeMaKwz3RnORddXjpnJ1WNj+74yuHeywhFdKcVNswctTsgRR
x/9FPoDYf5/UVYkhd5rn+ZRoVqkbzDV/NirLPOAifeng/TlKg+AudZu6usY6XQTt
40t5PflA6MrlaW7DSgfLvks/DAwZa9fOxenExe1A0+8nAfurVwbOBqepyAgdo5uw
GkZzaPLFBX+cmSrh0TzD7hvA8wGCo11yI2RrPBwWD+dHN63f5vdSRdDsASEJ0pOS
zJpn9pABpdqF4VyVrR35kbamR2zkmbETzPnTR6qpjrunCJWxCE+pHZRLGmwQ1DSD
TQIDAQAB
-----END PUBLIC KEY-----
', '2023-02-11 23:00:00.238', '2023-02-11 23:00:00.238', '', 'User 1', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, '', NULL, '', '', '', '', 0, 109848585822595953, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO public.accounts
(username, "domain", private_key, public_key, created_at, updated_at, note, display_name, uri, url, avatar_file_name, avatar_content_type, avatar_file_size, avatar_updated_at, header_file_name, header_content_type, header_file_size, header_updated_at, avatar_remote_url, "locked", header_remote_url, last_webfingered_at, inbox_url, outbox_url, shared_inbox_url, followers_url, protocol, id, memorial, moved_to_account_id, featured_collection_url, fields, actor_type, discoverable, also_known_as, silenced_at, suspended_at, hide_collections, avatar_storage_schema_version, header_storage_schema_version, devices_url, sensitized_at, suspension_origin, trendable, reviewed_at, requested_review_at)
VALUES('user2', NULL, '-----BEGIN RSA PRIVATE KEY-----
MIIEowIBAAKCAQEAn12zyhGV7tW1pjaubmlY6loVbMokAlMexn4VuiJm68iK8Qw4
RwKnt+J2BenRcda94AULC8+r6WQ3gsXl0CzGTGaOUgHU5CMRh+w4ptanJ0vArnhl
yeZRimBkugUG0mjaV7zsx0f3ov0o7Fde2afdplIHViOltZT+fdKoec3Le4UnpVWt
8A+nyqgjW1t4GGHJv6yn1Xs9UquIxP+ea04ZToicGuuXd8rls6vAXqPQVtqA+SqH
6YniDmiejTv+fAeoYqjOGWJgeGSiWTYgRGocnnNTvX29/ioPY31Em9X4zlPgAHKu
H3F4C+yMW2RjmwbDM3+JWz6BIIR58Ubp3KtquQIDAQABAoIBAE/nMEzBUnZkSmZj
BkQvp7ggoJtiInnT6RtGfmhL9WSEsT04jkL0TtlAv0IpwKyeKadXf/lVD3G+oKJ9
zML6oBYQWY0+g9o6xwBqbOEhwL9QGPShQw/e3cVOnVIzUP9QOSFChUlJiRz8Z3dQ
OytQIi7Q9AK5bs1DPfIiG/yuNTbhenwK+Yo5to+NVAC1W3/Ifocut8eHLJfCBbsT
yHj5uBcXfqR65h3C5+UKv+RR0RFqqATDqPfwXftlUj54AVwzYwTJL4+yMeiNKEXi
iUHl0tz8CtWt43kh8pnOZpxf49B7iVCA/6XofS1szGzBVr4LIYH6hKV8YB7fY34N
oAHBED0CgYEA1Ci4Rp6NeS/7XCgKmUv4FtOUPg6d7CPKlk1POxf6sys2eeYRt4bZ
aqGgftRpbxlSBQ4B+IgFCGTDstxeCQjmLgXNXjHiPSdUgS2eRZVTlZH2gdJ42M0/
k6N4eFejtrGwvQG4NS4HB2whrF4bM5zGIPYi9kmjFgIoxh7AmVzbZ0cCgYEAwEw2
5Qe8H9akXhH72NRgDSiW6etFhtrbc2XUDt3ah++rrxLBCGjaMFFLAFpu8RjLJ6Y6
NV5QdRGiYjcGFUQZa6b4VqmukxgafidnNvqarZN7CYwG+2cVIfZYLCh493b2OFV6
LFoNxlUIEsgRfpeb3EfhC6PhYio6PuDH8bhvnf8CgYEAwgb7gJpAiDR5jS6RguEX
YsjdKmPzyaJyBENv0nt7q6A5kgH28gFBlWN7hIJ+ajyd5kGJS+IZuDNvRoSC+4BI
Ao8L4rl8TrFJd1phBQIsDv4Ok2wJWT1ch4lZA7Yi41JecHzCGuCdTQx3P0xRVEJs
cHiqQNPXcqlG4On++kMR5MECgYB1VQuZq6KJ+rnH/EkjAFKrNL0AdeGyaw/D1boS
aCPTFdY3uMW3j+Kx4KhWytcunOY4hyC1N5r4E451RwWbLDYmjXaxyk8OyYJAv2In
FboTdw13wczw0PEADAO4zekhdaC+tskZDsmg3nlSnGiwSWl8ew9BvvjioMlxXE4j
m1nfmQKBgCPjrg1MFy1Kx0lF7EXAg4GWb9r38nH2aDd762RLYeY2c6R1vrcUDuFl
nyGDu61mVuiOu/gHmhD5K3CzoNTzQuPyopBln7z1QG2ytVNKOgCxhmbkmCCA0Zst
M4gZXYOPRFXCCMVmg7q2kI8TCZYjepWeO+D0cU80YakjQKxaJUsL
-----END RSA PRIVATE KEY-----
', '-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn12zyhGV7tW1pjaubmlY
6loVbMokAlMexn4VuiJm68iK8Qw4RwKnt+J2BenRcda94AULC8+r6WQ3gsXl0CzG
TGaOUgHU5CMRh+w4ptanJ0vArnhlyeZRimBkugUG0mjaV7zsx0f3ov0o7Fde2afd
plIHViOltZT+fdKoec3Le4UnpVWt8A+nyqgjW1t4GGHJv6yn1Xs9UquIxP+ea04Z
ToicGuuXd8rls6vAXqPQVtqA+SqH6YniDmiejTv+fAeoYqjOGWJgeGSiWTYgRGoc
nnNTvX29/ioPY31Em9X4zlPgAHKuH3F4C+yMW2RjmwbDM3+JWz6BIIR58Ubp3Ktq
uQIDAQAB
-----END PUBLIC KEY-----
', '2023-02-12 08:10:10.500', '2023-02-12 08:10:10.500', '', 'User 2', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, '', NULL, '', '', '', '', 0, 109850749185747308, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ---------------------------------------------------------------------------
-- Users
-- ---------------------------------------------------------------------------
INSERT INTO public.users
(email, created_at, updated_at, encrypted_password, reset_password_token, reset_password_sent_at, sign_in_count, current_sign_in_at, last_sign_in_at, "admin", confirmation_token, confirmed_at, confirmation_sent_at, unconfirmed_email, locale, encrypted_otp_secret, encrypted_otp_secret_iv, encrypted_otp_secret_salt, consumed_timestep, otp_required_for_login, last_emailed_at, otp_backup_codes, account_id, id, disabled, moderator, invite_id, chosen_languages, created_by_application_id, approved, sign_in_token, sign_in_token_sent_at, webauthn_id, sign_up_ip, skip_sign_in_token, role_id)
VALUES('user1@email.dev', '2023-02-11 23:00:00.285', '2023-02-11 23:34:50.321', '$2a$10$wA/zE6GJ.jz7.WC7yMgkhedRLNfYlcTRY9GNBSB.MVlvJJZuvBABa', NULL, NULL, 1, '2023-02-11 23:34:50.321', '2023-02-11 23:34:50.321', false, NULL, '2023-02-11 23:00:00.302', NULL, NULL, 'en', NULL, NULL, NULL, NULL, false, NULL, NULL, 109848585822595953, 2, false, false, NULL, NULL, NULL, true, NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO public.users
(email, created_at, updated_at, encrypted_password, reset_password_token, reset_password_sent_at, sign_in_count, current_sign_in_at, last_sign_in_at, "admin", confirmation_token, confirmed_at, confirmation_sent_at, unconfirmed_email, locale, encrypted_otp_secret, encrypted_otp_secret_iv, encrypted_otp_secret_salt, consumed_timestep, otp_required_for_login, last_emailed_at, otp_backup_codes, account_id, id, disabled, moderator, invite_id, chosen_languages, created_by_application_id, approved, sign_in_token, sign_in_token_sent_at, webauthn_id, sign_up_ip, skip_sign_in_token, role_id)
VALUES('user2@email.dev', '2023-02-12 08:10:10.624', '2023-02-12 10:17:20.747', '$2a$10$i0SkpfnpRWRpCzxWFNbyMe2T.mHpjgFhSwdN3LbvjPpgZ7tfMvpoG', NULL, NULL, 3, '2023-02-12 10:17:20.747', '2023-02-12 08:13:39.413', false, '', '2023-02-11 23:00:00.302', NULL, NULL, 'en', NULL, NULL, NULL, NULL, false, NULL, NULL, 109850749185747308, 3, false, false, NULL, NULL, NULL, true, NULL, NULL, NULL, '172.25.0.1'::inet, NULL, NULL);
