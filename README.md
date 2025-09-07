# Stratego - Java

Stratego game application developed in Java as part of the DEV2 course at HE2B ESI (2018-2019), to familiarize students with object-oriented programming

# Installation

To compile and run this project, you need to have Java (JDK 8 or higher) installed.

1. **Compile the project:**
	 - Open a terminal in the project root directory.
	 - Run the following command to compile the project:
		 ```
		 javac -d build/classes -cp build/classes -sourcepath src src/g43729/stratego/Stratego.java
		 ```

2. **Run the project:**
	 - After compilation, run the main class using the following command:
		 ```
		 java -cp build/classes g43729.stratego.Stratego
		 ```

## Usage

After building and running, follow the on-screen instructions to play Stratego.

### Commands

* `help` : displays the commands
* `select [row] [column]` : selects a piece at the designated position
* `moves` : shows the possible movements for the selected piece
* `apply [index]` : moves the selected piece with the selected movement
* `quit` : exits the game

