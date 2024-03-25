# match() Method Documentation

## Description
The `match()` method analyzes an array of strings containing "if", "else", "do", and "while" statements.
It matches each "else" statement with the last unmatched "if" statement appearing before it and each "while" statement with the last unmatched "do" statement appearing before it.
The method prints the indices of each "if" without an "else", each "if-else" pair, each "while" without a "do", and each "do-while" pair in the order of their occurrence in the array.
It also detects and reports any errors such as unmatched "else
