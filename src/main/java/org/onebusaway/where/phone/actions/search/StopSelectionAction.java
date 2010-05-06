/*
 * Copyright 2008 Brian Ferris
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.onebusaway.where.phone.actions.search;

import java.util.ArrayList;
import java.util.List;

import org.onebusaway.common.web.common.client.model.StopBean;
import org.onebusaway.where.web.common.client.model.NameTreeBean;
import org.onebusaway.where.web.common.client.rpc.WhereService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;


public class StopSelectionAction extends ActionSupport {

  private static final long serialVersionUID = 1L;

  protected WhereService _obaService;

  private List<Integer> _selection = new ArrayList<Integer>();

  private NameTreeBean _names;

  private StopBean _stop;

  @Autowired
  public void setOneBusAwayService(WhereService service) {
    _obaService = service;
  }

  private int _routeNumber;

  public void setRouteNumber(int routeNumber) {
    _routeNumber = routeNumber;
  }

  public int getRouteNumber() {
    return _routeNumber;
  }

  public List<Integer> getSelection() {
    return _selection;
  }

  public void setSelection(List<Integer> selection) {
    _selection = selection;
  }

  public NameTreeBean getNames() {
    return _names;
  }

  public StopBean getStop() {
    return _stop;
  }

  @Override
  public String execute() throws Exception {
    String route = Integer.toString(_routeNumber);
    _names = _obaService.getStopByRoute(route, _selection);
    if (_names.hasStop()) {
      _stop = _names.getStop();
      return "stopFound";
    }
    return SUCCESS;
  }
}