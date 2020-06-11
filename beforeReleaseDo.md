# Before a Release Do

The **StartMojo** class has a default Kickstart Dependency hard coded into it.

It is **important** that the version number is adjusted to the **release version**
number before the release is performed. To make the release work (it will build the
project) the **Sling Kickstart** module must be released first so that the it can
load that version.

Andreas Schaefer, 6/11/2020