package br.com.nb.authorizer;

import br.com.nb.authorizer.application.OperationsExecutor;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

  public static final String MESSAGE_FILE_NOT_FOUND = "File not found.";

  public static void main(String[] args) {
    OperationsExecutor operationsExecutor = new OperationsExecutor();

    try {
      checkIfTheFileWasInformed(args);

      var fileOperations = args[0];
      var operations = captureOperationsFromTheConsole(fileOperations);
      var resultsOperations = operationsExecutor.execute(operations);
      resultsOperations.forEach(System.out::println);

      System.exit(0);
    } catch (Exception exception) {
      exception.printStackTrace();
      System.exit(1);
    }
  }

  private static void checkIfTheFileWasInformed(String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      throw new FileNotFoundException(MESSAGE_FILE_NOT_FOUND);
    }
  }

  private static List<String> captureOperationsFromTheConsole(String arg)
      throws FileNotFoundException {
    var fileWithOperations = arg;
    var scannerOperations = new Scanner(new FileReader(fileWithOperations));

    var operations = new ArrayList<String>();

    while (scannerOperations.hasNext()) {
      operations.add(scannerOperations.nextLine());
    }

    scannerOperations.close();

    return operations;
  }
}
