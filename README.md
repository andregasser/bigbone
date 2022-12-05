# Mastodon4j

![Build](https://github.com/andregasser/mastodon4j/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/mastodon4j/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/mastodon4j)

**Mastodon4j** is a [Mastodon](https://docs.joinmastodon.org/) client for Java and Kotlin.


# Get Started

Mastodon4j is published on Jitpack. Check the latest version here:
[![](https://jitpack.io/v/andregasser/mastodon4j.svg)](https://jitpack.io/#andregasser/mastodon4j)

To use Mastodon4j via Jitpack, add it to your root build.gradle at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

Then, add Mastodon4j dependencies to your module Gradle file:

```groovy
implementation 'com.github.andregasser.mastodon4j:mastodon4j:$version'
implementation 'com.github.andregasser.mastodon4j:mastodon4j-rx:$version'
```

Usage examples for Mastodon4j can be found in [USAGE.md](USAGE.md).

## API Documentation

The official Mastodon API can be found here: https://docs.joinmastodon.org/client/intro/

Implementation progress by Mastodon4j can be found in [API.md](API.md). Mastodon4j uses [Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html).


# Contribution

## Pull Requests
I am happy to handle any pull requests that come in. Thanks for your contribution!

## Reporting Issues
Please check out the [issues](https://github.com/andregasser/mastodon4j/issues) page for reporting issues. 

# License

```
MIT License

Copyright (c) 2017 Toshihiro Yagi

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```