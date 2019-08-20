# Spec

## Invariants

Things which should always be true:

- Whitespace between tokens on the same line doesn't affect the output
- Whitespace between forms doesn't affect the output
- Formatting is stable when run multiple times
- Exactly the same tokens should exist after formatting
- Content of strings should be unchanged
- There should always be a single newline at the end of the file

## Principles

- Produce beautiful Clojure code
- Format code in an idiomatic style
