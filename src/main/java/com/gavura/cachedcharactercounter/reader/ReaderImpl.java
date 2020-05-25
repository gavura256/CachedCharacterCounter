package com.gavura.cachedcharactercounter.reader;

import java.util.Scanner;

public class ReaderImpl implements Reader {
    private final Scanner scanner;

    public ReaderImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String readSentence() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
