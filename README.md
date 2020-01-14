# otaibe-apache-tika-docx-native project

The Idea of this project is to show the errors coming from Apache Tika parser in Native mode.

- create native executable: `./mvnw package -Pnative`
- start the binary: `./target/otaibe-apache-tika-docx-native-1.0-SNAPSHOT-runner`
- call the service
    - Option 1 : `curl -v -H "Content-Type: application/octet-stream" -X POST --data-binary @src/test/resources/test_bg.docx http://localhost:11025/parse`
    - Option 2 : `mvn package -D%test.service.http.port=11025`
- the output for the binary execution throws an exception: 
```
2020-01-14 14:43:40,589 ERROR [io.qua.ver.htt.run.QuarkusErrorHandler] (executor-thread-1) HTTP Request to /parse failed, error id: 7eca2481-63eb-44e0-8c4c-4d57968f69ec-1: org.jboss.resteasy.spi.UnhandledException: org.apache.xerces.parsers.ObjectFactory$ConfigurationError: Provider org.apache.xerces.parsers.XIncludeAwareParserConfiguration not found
        at org.jboss.resteasy.core.ExceptionHandler.handleApplicationException(ExceptionHandler.java:106)
        at org.jboss.resteasy.core.ExceptionHandler.handleException(ExceptionHandler.java:372)
        at org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:209)
        at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:496)
        at org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:252)
        at org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:153)
        at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:363)
        at org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:156)
        at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:238)
        at io.quarkus.resteasy.runtime.standalone.RequestDispatcher.service(RequestDispatcher.java:73)
        at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.dispatch(VertxRequestHandler.java:120)
        at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.access$000(VertxRequestHandler.java:36)
        at io.quarkus.resteasy.runtime.standalone.VertxRequestHandler$1.run(VertxRequestHandler.java:85)
        at org.jboss.threads.ContextClassLoaderSavingRunnable.run(ContextClassLoaderSavingRunnable.java:35)
        at org.jboss.threads.EnhancedQueueExecutor.safeRun(EnhancedQueueExecutor.java:2011)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.doRunTask(EnhancedQueueExecutor.java:1535)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1426)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
        at java.lang.Thread.run(Thread.java:748)
        at org.jboss.threads.JBossThread.run(JBossThread.java:479)
        at com.oracle.svm.core.thread.JavaThreads.threadStartRoutine(JavaThreads.java:460)
        at com.oracle.svm.core.posix.thread.PosixJavaThreads.pthreadStartRoutine(PosixJavaThreads.java:193)
Caused by: org.apache.xerces.parsers.ObjectFactory$ConfigurationError: Provider org.apache.xerces.parsers.XIncludeAwareParserConfiguration not found
        at org.apache.xerces.parsers.ObjectFactory.newInstance(Unknown Source)
        at org.apache.xerces.parsers.ObjectFactory.createObject(Unknown Source)
        at org.apache.xerces.parsers.ObjectFactory.createObject(Unknown Source)
        at org.apache.xerces.parsers.DOMParser.<init>(Unknown Source)
        at org.apache.xerces.parsers.DOMParser.<init>(Unknown Source)
        at org.apache.xerces.jaxp.DocumentBuilderImpl.<init>(Unknown Source)
        at org.apache.xerces.jaxp.DocumentBuilderFactoryImpl.newDocumentBuilder(Unknown Source)
        at org.apache.poi.ooxml.util.DocumentHelper.newDocumentBuilder(DocumentHelper.java:91)
        at org.apache.poi.ooxml.util.DocumentHelper.readDocument(DocumentHelper.java:165)
        at org.apache.poi.openxml4j.opc.internal.ContentTypeManager.parseContentTypesFile(ContentTypeManager.java:392)
        at org.apache.poi.openxml4j.opc.internal.ContentTypeManager.<init>(ContentTypeManager.java:104)
        at org.apache.poi.openxml4j.opc.internal.ZipContentTypeManager.<init>(ZipContentTypeManager.java:54)
        at org.apache.poi.openxml4j.opc.ZipPackage.getPartsImpl(ZipPackage.java:258)
        at org.apache.poi.openxml4j.opc.OPCPackage.getParts(OPCPackage.java:721)
        at org.apache.poi.openxml4j.opc.OPCPackage.open(OPCPackage.java:302)
        at org.apache.tika.parser.microsoft.ooxml.OOXMLExtractorFactory.parse(OOXMLExtractorFactory.java:110)
        at org.apache.tika.parser.microsoft.ooxml.OOXMLParser.parse(OOXMLParser.java:110)
        at org.apache.tika.parser.CompositeParser.parse(CompositeParser.java:280)
        at org.apache.tika.parser.CompositeParser.parse(CompositeParser.java:280)
        at org.apache.tika.parser.AutoDetectParser.parse(AutoDetectParser.java:143)
        at io.quarkus.tika.TikaParser.parseStream(TikaParser.java:85)
        at io.quarkus.tika.TikaParser.getMetadata(TikaParser.java:68)
        at io.quarkus.tika.TikaParser.getMetadata(TikaParser.java:64)
        at org.otaibe.apache.tika.docx.nerror.TikaParserResource.getContentType(TikaParserResource.java:52)
        at org.otaibe.apache.tika.docx.nerror.TikaParserResource.hello(TikaParserResource.java:38)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:151)
        at org.jboss.resteasy.core.MethodInjectorImpl.lambda$invoke$3(MethodInjectorImpl.java:122)
        at java.util.concurrent.CompletableFuture.uniApply(CompletableFuture.java:616)
        at java.util.concurrent.CompletableFuture.uniApplyStage(CompletableFuture.java:628)
        at java.util.concurrent.CompletableFuture.thenApply(CompletableFuture.java:1996)
        at java.util.concurrent.CompletableFuture.thenApply(CompletableFuture.java:110)
        at org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:122)
        at org.jboss.resteasy.core.ResourceMethodInvoker.internalInvokeOnTarget(ResourceMethodInvoker.java:594)
        at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTargetAfterFilter(ResourceMethodInvoker.java:468)
        at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invokeOnTarget$2(ResourceMethodInvoker.java:421)
        at org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:363)
        at org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTarget(ResourceMethodInvoker.java:423)
        at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:391)
        at org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invoke$1(ResourceMethodInvoker.java:365)
        at java.util.concurrent.CompletableFuture.uniComposeStage(CompletableFuture.java:995)
        at java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:2137)
        at java.util.concurrent.CompletableFuture.thenCompose(CompletableFuture.java:110)
        at org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:365)
        at org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:477)
        ... 19 more


```

## Others 

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application is packageable using `./mvnw package`.
It produces the executable `otaibe-apache-tika-docx-native-1.0-SNAPSHOT-runner.jar` file in `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/otaibe-apache-tika-docx-native-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or you can use Docker to build the native executable using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your binary: `./target/otaibe-apache-tika-docx-native-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide .