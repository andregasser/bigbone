# :desert_island: Vacation Announcement :desert_island:

Dear users, I'm on vacation until May 12, 2023. During this time, issues and pull requests will be processed with a delay. After my return, however, I will gladly work again for BigBone. Thank you for your understanding! I wish you a good time.

# BigBone

![Build](https://github.com/andregasser/bigbone/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/bigbone/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/bigbone)
![Latest Snapshot](https://img.shields.io/badge/dynamic/xml?url=https://s01.oss.sonatype.org/content/repositories/snapshots/social/bigbone/bigbone/maven-metadata.xml&label=Latest%20Snapshot&color=blue&query=.//versioning/latest)

**BigBone** is a [Mastodon](https://docs.joinmastodon.org/) client library for Java and Kotlin.

# Why BigBone

BigBone is a fork of [Mastodon4J](https://github.com/sys1yagi/mastodon4j), a Mastodon client library for Java and Kotlin that was published by Toshihiro Yagi. 
The goal of Mastodon4J was to provide an easy-to-use library for interacting with the [Mastodon social media network](https://joinmastodon.org/) from Java and 
Kotlin code. Unfortunately, it became abandoned and has not seen any updates since 2018. 

Since Elon Musk's Twitter acquisition in 2022, Mastodon has gained tremendous popularity. A project that is so well received by the community deserves to have 
up-to-date and maintained client libraries. Back in November 2022, I took the decision to bring the Mastodon4J project back to life. That's when this project,
the BigBone client library for Java and Kotlin, was born. May it serve you well in your Mastodon-related endeavours!

The name **BigBone** has mostly symbolic character. I have chosen the name BigBone for this library because Mastodons represent impressive animals from the 
Pleistocene, built of big and heavy bones. At the same time, we hope this library will build some sort of "skeleton" for your Mastodon-related projects. 
Interestingly, there is also [Big Bone Lick State Park in Kentucky](https://parks.ky.gov/union/parks/historic/big-bone-lick-state-historic-site) where 
American Mastodons have been excavated.

# Core Functionality

With a library like BigBone, you can for example build tools which
- act on statuses on your timelines (home, local, federated).
- create, boost and favourite statuses and polls
- schedule statuses
- plus lots of other stuff!

# Implementation Status

**I did not release an official version on Maven Central yet**, but there's already a `2.0.0-SNAPSHOT` that you can use to play around / experiment with. 
Just please be aware that with every new snapshot version, there can be breaking changes along the lines. I am sure that there will be "dark places" in the 
library, where stuff will not work as expected. If you find issues, please file an issue in the [Issues](https://github.com/andregasser/bigbone/issues) section.  

BigBone does not yet implement the full API of Mastodon. Actually, there is still **a lot to do**. Our main goal for the 2.0.0 release is to get something out 
that is in a maintained state and on a solid basis, technology-wise. In future releases, focus will be put on extending the existing API coverage. 

For details on the current API coverage please checkout wiki page [Mastodon API Coverage](https://github.com/andregasser/bigbone/wiki/Mastodon-API-Coverage).

BigBone uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).

# Get Started

## Gradle (Groovy DSL)

Instructions for adding BigBone `2.0.0-SNAPSHOT` to your Gradle project (using Groovy DSL):

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

Instructions for adding BigBone `2.0.0-SNAPSHOT` to your Gradle project (using Kotlin DSL):

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

# BigBone Users

Are you using this library in your project? If so, let me know and I will happily feature your project in this section here.

# Contribution

Contributions are welcome! Please have a look at [CONTRIBUTING.md](CONTRIBUTING.md) for more details.  

# Previous Work

My personal thanks go to Toshihiro and his contributors back then for building [Mastodon4J](https://github.com/sys1yagi/mastodon4j). The library they have
built in the past serves as a basis for our own development efforts. ❤️ Thank you! ❤️

# License

BigBone is published under the MIT license. For more information on rights and obligations related to the MIT license, we would like to refer to
[this page](https://fossa.com/blog/open-source-licenses-101-mit-license/) here. The license file can be found in the [LICENSE](LICENSE) file.
