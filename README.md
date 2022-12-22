# Bigbone

![Build](https://github.com/andregasser/bigbone/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/bigbone/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/bigbone)

**Bigbone** is a [Mastodon](https://docs.joinmastodon.org/) client for Java and Kotlin.


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
and our roadmap please have a look in the __Issues__ or __Projects__ section. 

## Releases
We did not yet release a publicly available version. But we are working on it and hope to release an initial version 2.0.0 soon. Until then,
you can experiment with our snapshot builds on JitPack.

Bigbone uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).

# Contribution
Contributors are very welcome. If you think you can contribute, please do so! We will happily review any request we get. You can either
create a pull request or [create an issue](https://github.com/andregasser/bigbone/issues). 

# License
Bigbone is a fork of the original library published by Toshihiro Yagi. Unfortunately, it became abandoned and has not seen any updates
since a long time. I have therefore decided to revive and enhance it in an independent way. My personal hope is, that this library will
satisfy developers working on the Mastodon related topics. 

My personal thanks goes to Toshihiro and his contributors back then for building a basis we can now build upon.
The original license file can be found in the [LICENSE](LICENSE) file.
