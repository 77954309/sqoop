/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sqoop.connector.mysqljdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.sqoop.model.MForm;
import org.apache.sqoop.connector.spi.SqoopConnector;

public class MySqlJdbcConnector implements SqoopConnector {

  private static final List<MForm> CONNECTION_FORMS = new ArrayList<MForm>();
  private static final List<MForm> JOB_FORMS = new ArrayList<MForm>();

  @Override
  public ResourceBundle getBundle(Locale locale) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<MForm> getConnectionForms() {
    return CONNECTION_FORMS;
  }

  @Override
  public List<MForm> getJobForms() {
    // TODO Auto-generated method stub
    return JOB_FORMS;
  }
}