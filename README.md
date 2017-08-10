# jersey-swagger-archetype
A Jersey2/Swagger/Grizzly RESTful API Archetype

To use this, build a Maven project using the archetype:

```bash
$ mvn archetype:generate                                  \
    -DarchetypeGroupId=com.seismicgames                   \
    -DarchetypeArtifactId=jersey-swagger-archetype        \
    -DarchetypeVersion=1.0.2                              \
    -DgroupId=<your.group.id>                             \
    -DartifactId=<your.artifact.id>
```