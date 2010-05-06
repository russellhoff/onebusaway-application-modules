package org.onebusaway.csv;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractFieldMapping implements FieldMapping {

  protected String _csvFieldName;

  protected String _objFieldName;

  protected boolean _required;

  public AbstractFieldMapping(String csvFieldName, String objFieldName,
      boolean required) {
    _csvFieldName = csvFieldName;
    _objFieldName = objFieldName;
    _required = required;
  }

  public void getCSVFieldNames(Collection<String> names) {
    names.add(_csvFieldName);
  }

  protected boolean isMissing(Map<String, Object> csvValues) {
    return !(csvValues.containsKey(_csvFieldName) && csvValues.get(
        _csvFieldName).toString().length() > 0);
  }

  protected boolean isMissingAndOptional(Map<String, Object> csvValues) {

    boolean missing = isMissing(csvValues);

    if (_required && missing)
      throw new IllegalStateException("missing required field: "
          + _csvFieldName);

    return missing;
  }

  protected boolean isOptional() {
    return !_required;
  }
}