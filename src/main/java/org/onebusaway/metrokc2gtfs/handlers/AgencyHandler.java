package org.onebusaway.metrokc2gtfs.handlers;

import org.onebusaway.csv.CsvEntityWriter;
import org.onebusaway.gtfs.model.Agency;
import org.onebusaway.metrokc2gtfs.TranslationContext;
import org.onebusaway.metrokc2gtfs.model.MetroKCAgency;

public class AgencyHandler extends InputHandler {

  private static final String[] AGENCY_FIELDS = {"name", "url", "timezone"};

  private TranslationContext _context;

  public AgencyHandler(TranslationContext context) {
    super(MetroKCAgency.class, AGENCY_FIELDS);
    _context = context;
  }

  public void handleEntity(Object bean) {

    MetroKCAgency agency = (MetroKCAgency) bean;

    Agency a = new Agency();

    a.setName(agency.getName());
    a.setTimezone(agency.getTimezone());
    a.setUrl(agency.getUrl());

    CsvEntityWriter writer = _context.getWriter();
    writer.handleEntity(a);
  }
}