# Intro
Bet-api is a temporary project:

* 'com.game.bet-api' simulates gaming using bet.

# Getting Started

### Running app
Please consider the following steps:

* Start application using command: mvn spring-boot:run
* Call endpoint "http://localhost:8090/getWinning"
* Use POST method with body like:
```
  {
    "input": 50,
    "bet": 40.5
  }
```

### Tests
The following steps will help you to run "returned to player" test:

* Make sure that main app is running
* Run tests command - you will see resulting values in a console like:
```
    Rounds: 10000
    Total input: 244982
    Total winning: 155384.43
    RTP: 63.43
```
or
```
    Rounds: 10000
    Total input: 496509
    Total winning: 2155671.02
    RTP: 434.17
```
* Feel free to change core.api.rounds value in application.yml file.

