# Intro
Bet-api is a temporary project according to a task

Write a backend for the game:

* The player sends the bet and an integer from 1 to 100 to the server.
* The server generates a random integer from 1 to 100 and if the player's number is greater, calculates the winnings and sends a response to the client.
* The winning depends on the chance - = bet * (99 / (100 - number)), for example, if a person chose the number 50 and the bet is 40.5, then the winning will be 80.19

Additional task:

Write a test that will play 1m rounds in parallel in 24 threads and calculate how much money is returned to the player (RTP)
For example: on 1m of games the player spent 1m and won 990,000, RTP will be 99%.

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

