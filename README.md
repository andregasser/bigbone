# Bigbone

![Build](https://github.com/andregasser/bigbone/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/bigbone/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/bigbone)

**Bigbone** is a [Mastodon](https://docs.joinmastodon.org/) client for Java and Kotlin.

# Why Bigbone

Bigbone is a fork of the original [Mastodon4J](https://github.com/sys1yagi/mastodon4j) library published by Toshihiro Yagi. Unfortunately, it became abandoned
and has not seen any updates since 2018. I have therefore (more or less spontaneously) decided to revive it. Initially, I did not have any plans on doing
something like this, but slipped more or less into this role. 

Since Elon Musk's Twitter acquisition, Mastodon has gained tremendous popularity. A project that is so well received by the community deserves to have 
up-to-date client libraries. That's why I took on this task and try to develop the library in a good sense. This is the call of duty. 

The name has mostly symbolic character. We chose the name Bigbone for this library because Mastodons represent impressive animals from the Pleistocene, built 
of big and heavy bones. At the same time, we hope this library will build some sort of "skeleton" for your Mastodon-related projects. Interestingly, there is 
also [Big Bone Lick State Park in Kentucky](https://parks.ky.gov/union/parks/historic/big-bone-lick-state-historic-site) where American Mastodons have been 
excavated.

My personal thanks goes to Toshihiro and his contributors back then for building [Mastodon4J](https://github.com/sys1yagi/mastodon4j). The library they have
built in the past serves as a basis for our own development efforts. Thank you!

# Get Started

Bigbone is available on [Maven Central](https://search.maven.org/). We have not yet released an official version, but there is a `2.0.0-SNAPSHOT` build you
can experiment / play around with. 

**Remember: SNAPSHOT versions are not stable, so please expect updates on a daily basis until the official 2.0.0 release is made
available.** 

## Gradle (Groovy DSL)

Instructions for adding Bigbone `2.0.0-SNAPSHOT` to your Gradle project (using Groovy DSL):

Repository:

```groovy
repositories {
    maven {
        url "https://s01.oss.sonatype.org/content/repositories/snapshots/"
    }
}
```

Dependencies:

```groovy
dependencies {
    implementation "social.bigbone:bigbone:2.0.0-SNAPSHOT"
    implementation "social.bigbone:bigbone-rx:2.0.0-SNAPSHOT"
}
```

## Gradle (Kotlin DSL)

Instructions for adding Bigbone `2.0.0-SNAPSHOT` to your Gradle project (using Kotlin DSL):

Repository:

```groovy
repositories {
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

Dependencies:

```groovy
dependencies {
    implementation("social.bigbone:bigbone:2.0.0-SNAPSHOT")
    implementation("social.bigbone:bigbone-rx:2.0.0-SNAPSHOT")
}
```

## Maven

Instructions for adding Bigbone `2.0.0-SNAPSHOT` to your Maven project:

Repository:

```xml
<repositories>
    <repository>
        <id>maven-central-snapshots</id>
        <name>Maven Central Snapshot Repository</name>
        <url>https://s01.oss.sonatype.org/content/repositories/snapshots/</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
</repositories>
```

Dependencies:

```xml
<dependency>
    <groupId>social.bigbone</groupId>
    <artifactId>bigbone</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
<dependency>
    <groupId>social.bigbone</groupId>
    <artifactId>bigbone-rx</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```

Usage examples for Bigbone can be found in [USAGE.md](USAGE.md).

## Mastodon API 

Bigbone aims to implement the Mastodon API. The official Mastodon API can be found here: https://docs.joinmastodon.org/client/intro/
More information about the current implementation progress can be found in [API.md](API.md). For more details about future releases 
and our roadmap please have a look in the **Issues** or **Projects** section. 

## Releases

We did not yet release a publicly available version. But we have released our first 2.0.0-SNAPSHOT on Maven Central. We are working on the 2.0.0 release and 
hope to get it out soon. Until then, we encourage you to experiment / play with the 2.0.0-SNAPSHOT build and report any issues you find.

Bigbone uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).

# Contribution

Contributors are very welcome. If you think you can contribute, please do so! We will happily review any request we get. You can either
create a pull request or [create an issue](https://github.com/andregasser/bigbone/issues). 

Thanks to all the people who have contributed so far:

[![Profile images of all the contributors](https://contrib.rocks/image?repo=andregasser/bigbone)](https://github.com/andregasser/bigbone/graphs/contributors)

# License

Bigbone is published under the MIT license. For more information on rights and obligations related to the MIT license, we would like to refer to
[this page](https://fossa.com/blog/open-source-licenses-101-mit-license/) here. The license file can be found in the [LICENSE](LICENSE) file.
