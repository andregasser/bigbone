# Signing

## Overview
This document explains how build artifact signing is set up in the BigBone project. It also provides step by step 
guidance on how to manage OpenPGP key pairs required for signing.

## Signing Artifacts During The Build
...

Local building?

## Key Pair Creation
Create a new OpenPGP key pair:
`gpg --full-generate-key`

Some points to consider:
* Choose a validity of two years
* Set a strong password for the private key
* Choose default suggestions 

## Exporting Private Key for GitHub
The `Release` workflow on GitHub requires the private key and the associated private key password in order to work. 
Once you have the signing key pair in your keyring, you can export the private key in the correct format for GitHub with
this command: `gpg --armor --export-secret-keys <Key ID>`

## Key Backup / Restore
Backup public key into file: 
`gpg --export --export-options backup --output public.gpg`

Backup private key into file (asks for password): 
`gpg --export-secret-keys --export-options backup --output private.gpg`

Restore public key from file: 
`gpg --import public.gpg`

Restore private key from file (asks for password): 
`gpg --import private.gpg`

## Upload Key to Key Servers
The public key of the key pair that is used for signing should be uploaded to an OpenPGP key server. With GnuPG, this
can be easily done by invoking `--keyserver keys.openpgp.org --send-key <Key ID>`. Unfortunately, Homebrew's 
GnuPG version has an issue with uploading using the `gpg --send-key` command. Because of this, the steps 
below explain how a public key can be uploaded via https://keys.openpgp.org:

* Export public key into a file: `gpg --export <Key ID> > my_key.pub`
* Open https://keys.openpgp.org and upload the key by selecting `my_key.pub`from your local disk.
* Search for the new key on https://keys.openpgp.org to verify correct upload.
