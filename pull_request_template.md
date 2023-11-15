# Description

Please include a summary of the changes and the related issue. Please also include relevant motivation and context. List any dependencies that are required for this change. If your change closes an existing issue, please make this explicit by writing "Closes &lt;link to issue&gt;".

# Type of Change

(Keep the one that applies, remove the rest)

- Bug fix
- New feature
- Documentation
- Dependency update / replacement

# Breaking Changes

A breaking change is a change to supported functionality between released versions of a library that would require 
a customer to do work in order to upgrade to the newer version. If your change includes one or more breaking changes, 
please list/document them here, otherwise set "None". This information will be published in our release notes.

- Breaking change 1
- ...

# How Has This Been Tested?

Please describe the tests that you ran to verify your changes. 

# Mandatory Checklist

- [ ] My change follows the projects coding style
- [ ] I ran `gradle check` and there were no errors reported
- [ ] I have performed a self-review of my code
- [ ] I have added tests that prove my fix is effective or that my feature works
- [ ] New and existing unit tests pass locally with my changes
- [ ] KDoc added to all public methods

# Optional Things To Check

The items below are some more things to check before asking other people to review your code.

- In case you worked on new features: Did you also implement the reactive endpoint (bigbone-rx)?
- In case you added new *Methods classes: Did you also add it to `MastodonClient`?
- In case you worked on endpoints: Please mention in the PR that [API Coverage](https://github.com/andregasser/bigbone/wiki/Mastodon-API-Coverage) page needs to be updated (if needed)
