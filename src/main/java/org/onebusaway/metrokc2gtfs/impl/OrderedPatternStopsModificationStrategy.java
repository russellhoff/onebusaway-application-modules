package org.onebusaway.metrokc2gtfs.impl;

import org.onebusaway.metrokc2gtfs.model.MetroKCOrderedPatternStop;
import org.onebusaway.metrokc2gtfs.model.RouteSchedulePatternId;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;

public interface OrderedPatternStopsModificationStrategy {
  public void modify(RouteSchedulePatternId routeSchedulePatternId,
      SortedMap<Date, List<MetroKCOrderedPatternStop>> orderedPatternsStopsByDate);
}