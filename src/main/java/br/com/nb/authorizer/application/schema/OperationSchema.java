package br.com.nb.authorizer.application.schema;

import java.util.Set;

public abstract class OperationSchema {
  protected Set<String> fields;

  public boolean valid(String json) {
    for (var field : fields) {
      if (!json.contains(field)) return false;
    }

    return true;
  }
}
