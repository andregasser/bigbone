# BigBone

![Build](https://github.com/andregasser/bigbone/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/bigbone/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/bigbone)
![Latest Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://s01.oss.sonatype.org/content/repositories/snapshots/social/bigbone/bigbone/maven-metadata.xml&label=Latest%20Snapshot&color=blue&query=.//versioning/latest)

**BigBone** is a [Mastodon](https://docs.joinmastodon.org/) client library for Java and Kotlin.

# Why BigBone

BigBone is a fork of [Mastodon4J](https://github.com/sys1yagi/mastodon4j), a Mastodon client library for Java and Kotlin that was published by Toshihiro Yagi. 
The goal of Mastodon4J at that time was to provide an easy-to-use library for interacting with the Mastodon social media network from Java and Kotlin code. 
Unfortunately, it became abandoned and has not seen any updates since 2018. 

Since Elon Musk's Twitter acquisition in 2022, Mastodon has gained tremendous popularity. A project that is so well received by the community deserves to have 
up-to-date client libraries. I have therefore decided to jump in and am now maintaining the BigBone client library since mid-November 2022 as an alternative 
to Mastodon4J. I hope that this library will help other developers to build cool stuff for the Mastodon community.

The name **BigBone** has mostly symbolic character. I have chosen the name BigBone for this library because Mastodons represent impressive animals from the 
Pleistocene, built of big and heavy bones. At the same time, we hope this library will build some sort of "skeleton" for your Mastodon-related projects. 
Interestingly, there is also [Big Bone Lick State Park in Kentucky](https://parks.ky.gov/union/parks/historic/big-bone-lick-state-historic-site) where 
American Mastodons have been excavated.

# Core Functionality

With a library like BigBone, you can build tools which
- act on status updates on any of your timelines (home, local, federated)
- post, reblog, and favourite statuses
- plus lots of other stuff!

# Implementation Status

BigBone does not yet implement the full API of Mastodon. Actually, there is still **a lot to do**. The main focus for future releases will be extending the 
API coverage and fixing bugs that pop up. For some more insights please check out any of these pages:

- [Mastodon API Coverage](https://github.com/andregasser/bigbone/wiki/Mastodon-API-Coverage) 
- [Release Roadmap](https://github.com/andregasser/bigbone/wiki/Release-Roadmap)

BigBone uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).

# Get Started

## Gradle (Groovy DSL)

### Using Latest Stable Release

Instructions for adding BigBone `2.0.0` to your Gradle project:

Repository:

```groovy
repositories {
    mavenCentral()
}
```
Dependencies:

```groovy
dependencies {
    implementation "social.bigbone:bigbone:2.0.0"
    implementation "social.bigbone:bigbone-rx:2.0.0"
}
```

### Using Latest Snapshot Release

Instructions for adding BigBone `2.0.0-SNAPSHOT` to your Gradle project:

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

### Using Latest Stable Release

Instructions for adding BigBone `2.0.0` to your Gradle project:

Repository:

```kotlin
repositories {
    mavenCentral()
}
```

Dependencies:

```kotlin
dependencies {
    implementation("social.bigbone:bigbone:2.0.0")
    implementation("social.bigbone:bigbone-rx:2.0.0")
}
```

### Using Latest Snapshot Release

Instructions for adding BigBone `2.0.0-SNAPSHOT` to your Gradle project:

Repository:

```kotlin
repositories {
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}
```

Dependencies:

```kotlin
dependencies {
    implementation("social.bigbone:bigbone:2.0.0-SNAPSHOT")
    implementation("social.bigbone:bigbone-rx:2.0.0-SNAPSHOT")
}
```

## Maven

### Using Latest Stable Release

Instructions for adding BigBone `2.0.0` to your Maven project:

Repository:

```xml
<repositories>
    <repository>
        <id>maven-central</id>
        <name>Maven Central Repository</name>
        <url>https://repo1.maven.org/maven2</url>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories>
```

Dependencies:

```xml
<dependency>
    <groupId>social.bigbone</groupId>
    <artifactId>bigbone</artifactId>
    <version>2.0.0</version>
</dependency>
<dependency>
    <groupId>social.bigbone</groupId>
    <artifactId>bigbone-rx</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Using Latest Snapshot Release

Instructions for adding BigBone `2.0.0-SNAPSHOT` to your Maven project:

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

# BigBone Contributors

The following people have actively contributed to the development of BigBone:

- [Andreas](https://fosstodon.org/@bocops) - For actively contributing to the whole library project - thanks!
- [Cédric Champeau](https://mastodon.xyz/@melix) - For putting the Gradle build scripts in great shape again

Thank you very much for your contributions!

# Contributing

Contributions are welcome! Please have a look at [CONTRIBUTING.md](CONTRIBUTING.md) for more details.  

# Are You Using BigBone?

If so, please let me know and I will add you to the list here which is pretty small at the moment 🙂 

- [MastoFX](https://github.com/pilhuhn/MastoFX) - A simple Mastodon client written in JavaFX

# Previous Work

My personal thanks go to Toshihiro and his contributors back then for building [Mastodon4J](https://github.com/sys1yagi/mastodon4j). The library they have
built in the past serves as a basis for our own development efforts. ❤️ Thank you! ❤️

# License

BigBone is published under the MIT license. For more information on rights and obligations related to the MIT license, we would like to refer to
[this page](https://fossa.com/blog/open-source-licenses-101-mit-license/) here. The license file can be found in the [LICENSE](LICENSE) file.
