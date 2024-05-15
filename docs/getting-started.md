---
title: Getting Started
layout: default
nav_order: 2
---

# Getting Started

In this guide, we want to show you how you can easily integrate BigBone into your project. Simply follow the steps below.

## Adding BigBone to Your Project

BigBone consists of two main modules:

1. `bigbone` which exposes the Mastodon API endpoints as handy methods usable via a `MastodonClient`
2. `bigbone-rx` which adds a thin RxJava3 layer around the `bigbone` module

{: .note }
If your application does not require reactive functionality, you can omit the `bigbone-rx` dependency in the following 
steps. BigBone uses RxJava for implementing the reactive part. Check out [their website](https://github.com/ReactiveX/RxJava) 
to get more information.

### Gradle (Groovy DSL)

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
    // Optional, if you want to use the BigBone RxJava3 wrappers
    implementation "social.bigbone:bigbone-rx:2.0.0-SNAPSHOT"
}
```

### Gradle (Kotlin DSL)

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
    // Optional, if you want to use the BigBone RxJava3 wrappers
    implementation("social.bigbone:bigbone-rx:2.0.0-SNAPSHOT")
}
```

### Maven

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

        <!-- Optional, if you want to use the BigBone RxJava3 wrappers -->
<dependency>
    <groupId>social.bigbone</groupId>
    <artifactId>bigbone-rx</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</dependency>
```
