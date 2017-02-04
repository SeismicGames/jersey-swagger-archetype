package ${package}.${artifactId}.providers;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
    // logger
    private static final Logger logger = LoggerFactory.getLogger(GsonProvider.class);

    // gson
    private final Gson gson = new Gson();

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public Object readFrom(Class<Object> aClass, Type type, Annotation[] annotations, MediaType mediaType,
                           MultivaluedMap<String, String> multivaluedMap, InputStream inputStream)
            throws IOException, WebApplicationException {

        try (InputStreamReader is = new InputStreamReader(inputStream, "UTF-8")) {
            return gson.fromJson(is, type);
        } catch (JsonSyntaxException e) {
            logger.error("Gson parsing error", e);
        }

        return null;
    }

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return 0;
    }

    @Override
    public void writeTo(Object o, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType,
                        MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream)
            throws IOException, WebApplicationException {

        try (OutputStreamWriter os = new OutputStreamWriter(outputStream, "UTF-8")) {
            gson.toJson(o, type, os);
        }
    }
}