# Bigbone

![Build](https://github.com/andregasser/bigbone/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/bigbone/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/bigbone)
![Latest Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://s01.oss.sonatype.org/content/repositories/snapshots/social/bigbone/bigbone/maven-metadata.xml&label=Latest%20Snapshot&color=blue&query=.//versioning/latest)

**Bigbone** is a [Mastodon](https://docs.joinmastodon.org/) client for Java and Kotlin.

# Why Bigbone

Bigbone is a fork of [Mastodon4J](https://github.com/sys1yagi/mastodon4j), a Mastodon client library for Java and Kotlin that was published by Toshihiro Yagi. 
The goal of Mastodon4J was to provide an easy-to-use library for interacting with the Mastodon social media network from Java and Kotlin code. Unfortunately, 
it became abandoned and has not seen any updates since 2018. Since Elon Musk's Twitter acquisition in 2022, Mastodon has gained tremendous popularity. A project
that is so well received by the community deserves to have up-to-date client libraries. I have therefore decided to jump in and am now maintaining the Bigbone 
client library since mid-November 2022 as an alternative to Mastodon4J. I hope that this library will help other developers to build cool stuff for the 
Mastodon community.

The name **Bigbone** has mostly symbolic character. I have chosen the name Bigbone for this library because Mastodons represent impressive animals from the 
Pleistocene, built of big and heavy bones. At the same time, we hope this library will build some sort of "skeleton" for your Mastodon-related projects. 
Interestingly, there is also [Big Bone Lick State Park in Kentucky](https://parks.ky.gov/union/parks/historic/big-bone-lick-state-historic-site) where 
American Mastodons have been excavated.

# Core Functionality

With a library like Bigbone, you can for example build tools which
- act on status updates on your timelines (home, local, federated).
- post, reblog and favourite statuses
- plus lots of other stuff

# Implementation Status

**I did not release an official version on Maven Central yet**, but there's already a `2.0.0-SNAPSHOT` that you can use to play around / experiment with. 
Just please be aware that, although it is a SNAPSHOT version, it is still in an early stage and there are for sure dark places in the library, where stuff will
not work as expected. If you find issues, please file an issue in the [Issues](https://github.com/andregasser/bigbone/issues) section.  

I really hope to get out an initial 2.0.0 version very soon (hopefully January 2023) to have something out there that is maintained and can be used by other 
developers.

Bigbone does not yet implement the full API of Mastodon. Actually, there is still **a lot to do**. Our main goal for the 2.0.0 release is to get something out 
that is in a maintained state and on a solid basis, technology-wise. In future releases, focus will be put on extending the existing API coverage. 

For details on the current API coverage please checkout wiki page [Mastodon API Coverage](https://github.com/andregasser/bigbone/wiki/Mastodon-API-Coverage).

Bigbone uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).

# Get Started

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

### Usage Examples

We provide some example code snippets for Java and Kotlin in [USAGE.md](USAGE.md) to get you started.

### Sample Code

There are also two Gradle modules that provide some insights on how to use this library:
- `sample-java` for Java example code
- `sample-kotlin` for Kotlin example code

# Bigbone Contributors

The following people have actively contributed to the development of Bigbone:

- Andreas (Mastodon: @factotum@c.im) - For actively contributing to the whole library project - thanks!
- Cédric Champeau (Mastodon: @melix@mastodon.xyz) - For putting the Gradle build scripts in great shape again

# Contribution

Contributions are welcome! If you find bugs or other issues, please open an issue in the [Issues](https://github.com/andregasser/bigbone/issues) section. I will 
happily review any request that is created.  

# Previous Work

My personal thanks go to Toshihiro and his contributors back then for building [Mastodon4J](https://github.com/sys1yagi/mastodon4j). The library they have
built in the past serves as a basis for our own development efforts. ❤️ Thank you! ❤️

# License

Bigbone is published under the MIT license. For more information on rights and obligations related to the MIT license, we would like to refer to
[this page](https://fossa.com/blog/open-source-licenses-101-mit-license/) here. The license file can be found in the [LICENSE](LICENSE) file.
