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

Bigbone has not been published as a released version yet. But you can include a snapshot build of our library in your project if you want.
But prepare for breaking changes, as we are in the process of changing some fundamental things. 

Bigbone is available on Jitpack. Check the latest snapshot version here:
[![](https://jitpack.io/v/andregasser/bigbone.svg)](https://jitpack.io/#andregasser/bigbone)

To use Bigbone via Jitpack, add it to your root `build.gradle` at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Then, add Bigbone dependencies to your module `build.gradle` file:

```groovy
implementation 'com.github.andregasser.bigbone:bigbone:master-SNAPSHOT'
implementation 'com.github.andregasser.bigbone:bigbone-rx:master-SNAPSHOT'
```

If you want to better understand how JitPack works, checkout their documentation [here](https://jitpack.io/docs/).
Usage examples for Bigbone can be found in [USAGE.md](USAGE.md).

## Mastodon API 

Bigbone aims to implement the Mastodon API. The official Mastodon API can be found here: https://docs.joinmastodon.org/client/intro/
More information about the current implementation progress can be found in [API.md](API.md). For more details about future releases 
and our roadmap please have a look in the **Issues** or **Projects** section. 

## Releases
We did not yet release a publicly available version. But we are working on it and hope to release an initial version 2.0.0 soon. Until then,
you can experiment with our snapshot builds on JitPack.

Bigbone uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).

# Contribution
Contributors are very welcome. If you think you can contribute, please do so! We will happily review any request we get. You can either
create a pull request or [create an issue](https://github.com/andregasser/bigbone/issues). 

Thanks to all the people who have contributed so far:

[![Profile images of all the contributors](https://contrib.rocks/image?repo=andregasser/bigbone)](https://github.com/andregasser/bigbone/graphs/contributors)

# License
Bigbone is published under the MIT license. For more information on rights and obligations related to the MIT license, we would like to refer to
[this page](https://fossa.com/blog/open-source-licenses-101-mit-license/) here. The license file can be found in the [LICENSE](LICENSE) file.
