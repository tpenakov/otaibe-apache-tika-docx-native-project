package org.otaibe.apache.tika.docx.nerror;

import io.quarkus.tika.TikaMetadata;
import io.quarkus.tika.TikaParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Path(TikaParserResource.ROOT_PATH)
@Getter(AccessLevel.PROTECTED)
@Slf4j
public class TikaParserResource {

    public static final String ROOT_PATH = "/parse";

    @Inject
    TikaParser tikaParser;

    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@NotNull byte[] body) {
        String contentType = null;
        String text = null;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        try {
            contentType = getContentType(inputStream);
            inputStream.reset();
            text = getTikaParser().getText(inputStream);
        } catch (Exception e) {
            log.error("unable to get content type", e);
        }

        log.info("Content-Type: {}", contentType);
        log.info("Text: {}", text);

        return contentType;
    }

    private String getContentType(InputStream inputStream) {
        TikaMetadata metadata = getTikaParser().getMetadata(inputStream);
        return metadata.getSingleValue(HttpHeaders.CONTENT_TYPE);
    }
}