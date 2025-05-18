# log-parser

## Overview

Parses Standard Log Files to print the following:

- The number of unique IP addresses
- The top 3 most visited URLs
- The top 3 most active IP addresses

### Assumptions
- Log format is strictly similar to the sample provided in /resources/programming-task-example-data.log
- For each log line, the words are separated by single space (" ")
- The IP address is the 1st element in the log line split by single space
- The URL  is the 7th element in the log line split by single space

## Getting Started

### Prerequisites

- Java 8 or higher
- Gradle (optional, if you wish to build from source)

### Installation

Clone the repository:

```bash
git clone https://github.com/amansabir/log-parser.git
cd log-parser
```

Build the project (if using Gradle):

```bash
gradle build
```

### Usage

Run the parser with your log file:

```bash
java -jar target/log-parser.jar 

Enter the log file path: /path/to/your/logfile.log
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the MIT License.
