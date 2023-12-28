# Releasing

## Overview
This guide explains how we publish development snapshots and releases for BigBone.

## Publish a New Snapshot
Whenever you want to release a new development snapshot, please follow the steps listed below. For this guide, we assume
that the current version in `/gradle/libs.version.toml` is set to `bigbone = "2.0.0-SNAPSHOT"`.

* Agree within the team whether a new release can be published or not.
* Manually invoke the GitHub `Release` workflow on the master branch. The workflow does the following:
  * It builds, signs and publishes the most recent version of the 'master` branch to Maven Central.
  * It creates a new draft of a release notes page [here](https://github.com/andregasser/bigbone/releases).
* Once the workflow has successfully finished, please do the following:
    * Verify, that the binaries have been successfully uploaded to Maven Central by visiting the [Maven Central
      repository URL for BigBone snapshots]( https://s01.oss.sonatype.org/content/repositories/snapshots/social/bigbone/bigbone/).
    * Note down the Maven Central timestamp and create a tag for it on master. Here we assume the latest commit was
      aaaaaaa: `git tag -a v2.0.0-20230530.130705 -m v2.0.0-20230530.130705 aaaaaaa`.
    * Edit the release notes page that was created during the workflow run and bring it into good shape. Use one of the
      previously created release note documents as a basis to get a feeling on how it should look like. Once you are
      happy, ask other project members for a quick feedback. After positive feedback, publish the release notes.

## Publish a New Release
Let's assume we want to publish a new release for version `2.0.0`. A new release for this version is published to Maven 
Central by performing the following steps in order:

* Agree within the team whether a new release can be published or not.
* Open `/gradle/libs.version.toml` and set `bigbone = "2.0.0"`. Commit this change to the `master` branch of the 
  repository. Note the commit hash (for this example, we simply assume `aaaaaaa`)
* Create a new tag for commit hash `aaaaaaa` using `git tag -a v2.0.0 -m v2.0.0 aaaaaaa` and push it to `master` using
  `git push origin --tags`.
* The GitHub `Release` workflow is automatically executed which does the following:
    * It builds, signs and publishes tag `2.0.0` to Maven Central.
    * It creates a new draft of a release notes page [here](https://github.com/andregasser/bigbone/releases).
* Once the workflow has successfully finished, please do the following:
    * Verify, that the binaries have been successfully uploaded to Maven Central by visiting the [Maven Central
      repository URL for BigBone releases](https://repo1.maven.org/maven2/social/bigbone).
    * Edit the release notes page that was created during the workflow run and bring it into good shape. Use one of the
      previously created release note documents as a basis to get a feeling on how it should look like. Once you are 
      happy, ask other project members for a quick feedback. After positive feedback, publish the release notes.
* Agree within the team on the new development snapshot version (for simplicity, we assume `2.1.0`). Once that is sorted
  out, open `/gradle/libs.version.toml` and set the version to `bigbone = "2.1.0-SNAPSHOT"`. Commit this change to the 
  `master` branch of the repository. The repository is now prepared to work on the `2.1.0` development snapshot.
