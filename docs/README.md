# BigBone Documentation

# Overview
This repository contains documentation for Bigbone that is published under https://bigbone.social. It is based on Jekyll and the 
[Just the Docs Theme](https://just-the-docs.com/). 

# Contributing
If you want to contribute to this documentation, you should setup Ruby on your machine first.

## Software Installation

- Ruby 3.1.4+DevKit (for Windows use rubyinstaller.org) -> Running on Ruby 3.2.x is currently a problem, as 
  described [here](https://talk.jekyllrb.com/t/liquid-4-0-3-tainted/7946/17).
- Run `ridk enable` after ruby installation

## Building the Website Locally

- Switch into the source directory where the `Gemfile` is located.
- Run `bundle install`
- Run `bundle exec jekyll serve` to build your site and preview it at `localhost:4000`. The built site is stored in the directory `_site`.

The documentation should be served on http://127.0.0.1:4000 and is ready for development.

# Development Notes

## Changing the version of the theme and/or Jekyll

Simply edit the relevant line(s) in the `Gemfile`.

## Adding a plugin

The Just the Docs theme automatically includes the [`jekyll-seo-tag`] plugin.

To add an extra plugin, you need to add it in the `Gemfile` *and* in `_config.yml`. For example, to add [`jekyll-default-layout`]:

- Add the following to your site's `Gemfile`:

  ```ruby
  gem "jekyll-default-layout"
  ```

- And add the following to your site's `_config.yml`:

  ```yaml
  plugins:
    - jekyll-default-layout
  ```
  
