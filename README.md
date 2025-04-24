# Apache MINA 

A simple console-based TCP echo chatroom built using [Apache MINA](https://mina.apache.org/), demonstrating non-blocking I/O communication in Java.

## Features

- Event-driven server-client architecture
- Java 17 + Apache MINA 2.1.10
- Continuous text messaging (chat style)
- Clean SLF4J + Logback logging system
- Fully Maven-managed project

## Project Structure

```
mina-echo/
├── src/
│   └── main/
│       ├── java/org.example/
│       │   ├── EchoServer.java
│       │   └── EchoClient.java
│       └── resources/
│           └── logback.xml
├── pom.xml
└── README.md
```

## How to Install, Configure, and Use

### Prerequisites

- Java 17
- Maven 3.6+

### Setup

1. Clone or download this project
2. Open in IntelliJ IDEA (or your preferred IDE)
3. Make sure dependencies load via Maven (`pom.xml`)
4. Run the server first, then the client

### Run Server

```bash
Run EchoServer.java
```

Console:
```
Chat server started on port 9123
Client: hello!
```

### Run Client

```bash
Run EchoClient.java
```

Console:
```
Connected. Type messages to send. Type 'bye' to exit.
> hello!
Server: You said: hello!
```

## Demo Output

Client:
```
> hello mina!
Server: You said: hello mina!
> how are you?
Server: You said: how are you?
> bye
```

Server:
```
Client: hello mina!
Client: how are you?
```

## Dependencies

- `org.apache.mina:mina-core:2.1.10`
- `org.slf4j:slf4j-api:2.0.9`
- `ch.qos.logback:logback-classic:1.4.12`