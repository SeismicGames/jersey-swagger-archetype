# jersey-swagger-archetype
A Jersey/Swagger/Grizzly RESTful API Archetype

To use this, build a Maven project using the archetype:

```bash
$ mvn archetype:generate                                  \
    -DarchetypeGroupId=com.seismicgames                   \
    -DarchetypeArtifactId=jersey-swagger-archetype        \
    -DarchetypeVersion=1.0.0                              \
    -DgroupId=<your.group.id>                             \
    -DartifactId=<your.artifact.id>
```