# Yody Common

## Deployment
* Pushes to `develop` branch are automatically release to Nexus (make sure your POM version is in `*-SNAPSHOT` format).
* When your library is ready for release, remove `-SNAPSHOT` version and merge to `master` branch and mark a new tag, it will be released on Nexus.
