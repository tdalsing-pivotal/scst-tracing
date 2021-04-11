# SCSt Sleuth

This repo contains a demo of using [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) with 
[Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth).  Sleuth is a module that adds traceability to MVC 
request/response and to messages sent via Spring Cloud Stream.  Tracing of requests, responses, and messages can span multiple, separate 
microservices.  This allows the tracing of a single request or message across a large system, which is invaluable to debugging complex 
applications that encompass many different microservices. 

Sleuth generates traces between 2 microservices (i.e., a "span") and across the entire end-to-end flow (i.e., a "trace").  It can also track flows 
that cross MVC/SCSt boundaries, allowing for tracing of hybrid applications that contain both HTTP request/response and messaging.  Tracing across 
messaging systems is supported via headers, which will work with most SCSt binders.  Most HTTP clients, such as Feign, WebClient, and RestTemplate 
are supported.

Tracing uses generated UUIDs to identify specific spans and traces.

Sleuth can output traces in a number of ways, including:

- via logging
- via Elasticsearch
- via Zipkin (a tool that visualizes the traces and spans)

## Logging

When Sleuth is included in the build dependencies, it automatically changes the log output format to include the span and trace UUIDs.  The UUIDs 
can be used to examine the logs to determine the root cause of a failure, which may have been triggered deep in a system but not noticed until the 
call reaches a higher level.  The specific log output associated with the suspect span or trace can be located by grepping the logs for each 
component involved in the trace or span.

## Elasticsearch

In a large system going to the logs for each component is very difficult, so the use of the ELK stack (Elasticsearch, Logstash, and Kibana) in 
large environments is very common.  While not specific to Sleuth, the combination of Sleuth and Elasticsearch is a very powerful tool to see the 
sequence of events that led to the error.  Simply search the Elasticsearch indexes for the trace or span UUID in question to see all of the log 
output for the trace or span in one place.  This can also show the sequence of events, which may be important in determining what caused the problem.

Note that other log aggregation tools, such as Splunk, will also work since the UUIDs are simply part of the log output.

## Zipkin

Zipkin is a tool to visualize traces and spans.  For a given trace it shows the components and spans involved in the trace.  It also shows where 
sequential and parallel flows occur and their relationship.  This is a very powerful tool since it visualizes the entire trace, which spans are 
involved, which components participate in the span and trace, and potentially where the error was first recorded.
