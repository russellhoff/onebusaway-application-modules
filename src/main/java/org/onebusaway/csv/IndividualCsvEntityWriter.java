/**
 * 
 */
package org.onebusaway.csv;

import edu.washington.cs.rse.text.CSVLibrary;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IndividualCsvEntityWriter implements EntityHandler {

  private PrintWriter _writer;

  private List<String> _fieldNames = new ArrayList<String>();

  private EntitySchema _schema;

  private CsvEntityContext _context;

  public IndividualCsvEntityWriter(CsvEntityContext context,
      EntitySchema schema, PrintWriter writer) {
    _writer = writer;
    _schema = schema;
    _context = context;

    _fieldNames.clear();
    for (FieldMapping field : _schema.getFields())
      field.getCSVFieldNames(_fieldNames);
    _writer.println(CSVLibrary.getIterableAsCSV(_fieldNames));
  }

  public void handleEntity(Object object) {
    BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(object);
    Map<String, Object> csvValues = new HashMap<String, Object>();
    for (FieldMapping field : _schema.getFields())
      field.translateFromObjectToCSV(_context, wrapper, csvValues);
    List<Object> values = new ArrayList<Object>(csvValues.size());
    for (String fieldName : _fieldNames) {
      Object value = csvValues.get(fieldName);
      if (value == null)
        value = "";
      values.add(value);
    }
    _writer.println(CSVLibrary.getIterableAsCSV(values));
  }

  public void close() {
    _writer.close();
  }
}