package org.otaibe.apache.tika.docx.nerror;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.core.buffer.Buffer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Getter
@Setter
@Slf4j
public class TikaParserResourceTest {

    @ConfigProperty(name = "service.http.port", defaultValue = "8081")
    Integer port;

    @Inject
    Vertx vertx;

    @Test
    public void testEndpoint() {

        Buffer docxFile = getVertx().fileSystem().readFileBlocking("test_bg.docx");

        URI uri = UriBuilder.fromPath(TikaParserResource.ROOT_PATH)
                .host("localhost")
                .port(getPort())
                .scheme("http")
                .build();
        log.info("uri: {}", uri);

        given()
                .when()
                .body(docxFile.getBytes())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM)
                .post(uri)
                .then()
                .statusCode(200)
                .body(is("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
    }

}