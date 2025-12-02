# Octal Byte Converter

## Overview
This project provides utility functions to **decode an octal string into an array of bytes** and **encode an array of bytes back to an octal string**.  
Each byte element in the array is represented in **decimal form**. The solution works for **long input strings**.

## Language / Tools
- Java 21  
- Maven (for project management)

## Features
- OctalUtils `decode(String input)` → Converts an octal string to an array of decimal bytes.  
- OctalUtils `encode(List<Integer> input)` → Converts an array of decimal bytes back to an octal string.

## How to Run
1. Clone the repository:  
   ```bash
   git clone [https://github.com/QinghongDai/CommonUtils]
   
2. Navigate into the project folder:
cd OctalUtils

3. Build and run using Maven:
mvn compile
mvn exec:java -Dexec.mainClass="com.example.OctalUtils"

Example:
function decode
Input octal string: 31646541
Decoded output: [103, 77, 97]

function encode
Input: [103, 77, 97]
Encode output string: 31646541


Notes
The code handles long octal strings efficiently.
Each octal number in the input string is expected to be 3 digits per byte.
