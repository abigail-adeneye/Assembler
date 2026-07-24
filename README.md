# Two-Pass Assembler — Pass 1

The first pass of a two-pass assembler for a simple ARM-like instruction set, written in Java. Pass 1 parses an assembly language source file, validates its structure, computes the sizes of the data and code segments, and builds a symbol table mapping labels to their byte offsets — the information a second pass would need to resolve branch and jump targets and emit machine code.

## What it does

Given an assembly source file, `Assembler_P1.assemble()`:

1. **Validates the file header** — the first two lines must be `.align 2` and `.data`, or the assembler throws a `RuntimeException`.
2. **Scans the data segment** — counts words declared via `.word` directives to compute the data segment's size in bytes.
3. **Scans the code segment** — walks each instruction line until `.end`, computing the code segment's size in bytes.
4. **Builds a label/offset table** — any line ending in `:` is treated as a label; its relative byte offset within the code segment is recorded in a `LabelOffset` object.
5. **Writes segment sizes** to the specified data and code output files.

## Files

| File | Purpose |
|---|---|
| `Assembler_P1.java` | Pass 1 implementation |
| `LabelOffset.java` | Simple struct-style class pairing a label with its byte offset (provided) |
| `TestAssembler_P1.java` | Instructor-provided test suite — runs the assembler against 4 sample programs and diffs the output against known-correct results |
| `testProg1.s`–`testProg3.s`, `testAllProg.s` | Sample assembly source programs used as test input |
| `correct_testProg1.data`/`.code` (and similarly for `testProg2`, `testProg3`, `testAllProg`) | Expected output files the test suite compares against |

## Running it

```bash
javac *.java
java TestAssembler_P1
```

This runs all 4 tests, each assembling a sample `.s` file, comparing the generated `.data`/`.code` output against the known-correct versions line-by-line, and checking that the returned label/offset list matches what's expected.

## Known limitation

The line-reading loops currently call `.trim()` directly on the result of `BufferedReader.readLine()` before checking for `null`:

```java
while ((line = br.readLine().trim()) != null) { ... }
```

Since `readLine()` returns `null` at end-of-file, this throws a `NullPointerException` instead of exiting the loop cleanly. The fix is to separate the read and the null check:

```java
while ((line = br.readLine()) != null) {
    line = line.trim();
    // ...
}
```

This affects the data segment and code segment scanning loops in `pass1()`.
