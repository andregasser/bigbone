# mastodon4j

![Build](https://github.com/andregasser/mastodon4j/actions/workflows/build.yml/badge.svg)
[![codecov](https://codecov.io/gh/andregasser/mastodon4j/branch/master/graph/badge.svg?token=3AFHQQH547)](https://codecov.io/gh/andregasser/mastodon4j)


mastodon4j is a [mastodon](https://docs.joinmastodon.org/) client for Java and Kotlin.

# Official API Doc

https://docs.joinmastodon.org/client/intro/

# Get Started

Mastodon4j is published in jitpack.
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

```groovy
implementation 'com.github.andregasser.mastodon4j:mastodon4j:$version'
implementation 'com.github.andregasser.mastodon4j:mastodon4j-rx:$version'
```

Check latest version on Jitpack [![](https://jitpack.io/v/andregasser/mastodon4j.svg)](https://jitpack.io/#andregasser/mastodon4j)



# Versioning

[Semantic Versioning 2.0.0](http://semver.org/spec/v2.0.0.html)


# Contribution

## Pull Requests
I am happy to handle any pull requests that come in. Thanks for your contribution!

## Reporting Issues
Please checkout the [issues](https://github.com/andregasser/mastodon4j/issues) page for reporting issues. 

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
