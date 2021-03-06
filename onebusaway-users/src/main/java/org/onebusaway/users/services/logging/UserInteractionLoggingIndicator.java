/**
 * Copyright (C) 2011 Brian Ferris <bdferris@onebusaway.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.users.services.logging;

import java.util.Map;

import org.onebusaway.users.model.IndexedUserDetails;

public interface UserInteractionLoggingIndicator {

  /**
   * @param details the target user details entry
   * @return null if logging is not enabled for this user, otherwise a Map of
   *         details to be used as the base of the logging entry
   */
  public Map<String, Object> isLoggingEnabledForUser(IndexedUserDetails details);
}
