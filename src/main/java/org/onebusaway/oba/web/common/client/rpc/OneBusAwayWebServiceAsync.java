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
package org.onebusaway.oba.web.common.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

import org.onebusaway.oba.web.common.client.model.LocalSearchResult;
import org.onebusaway.oba.web.common.client.model.OneBusAwayConstraintsBean;
import org.onebusaway.oba.web.common.client.model.MinTransitTimeResult;
import org.onebusaway.oba.web.common.client.model.TimedPlaceBean;
import org.onebusaway.tripplanner.web.common.client.model.TripBean;

import java.util.List;

public interface OneBusAwayWebServiceAsync extends RemoteService {

  public static final String SERVICE_PATH = "/services/onebusaway";

  public static OneBusAwayWebServiceAsync SERVICE = GWT.create(OneBusAwayWebService.class);

  public void getMinTravelTimeToStopsFrom(double lat, double lon, OneBusAwayConstraintsBean constraints,
      AsyncCallback<MinTransitTimeResult> callback);

  public void getLocalPaths(String resultId, List<LocalSearchResult> localResults,
      AsyncCallback<List<TimedPlaceBean>> callback);

  public void getTripPlan(String resultId, TimedPlaceBean destination, AsyncCallback<TripBean> callback);
}