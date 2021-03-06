/*
 * Licensed to Metamarkets Group Inc. (Metamarkets) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Metamarkets licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.druid.metadata;

import java.util.List;

/**
 */
public interface MetadataStorageConnector
{
  String CONFIG_TABLE_KEY_COLUMN = "name";
  String CONFIG_TABLE_VALUE_COLUMN = "payload";

  Void insertOrUpdate(
      String tableName,
      String keyColumn,
      String valueColumn,
      String key,
      byte[] value
  ) throws Exception;

  byte[] lookup(
      String tableName,
      String keyColumn,
      String valueColumn,
      String key
  );

  /**
   * Atomic compare-and-swap variant of insertOrUpdate().
   *
   * @param updates Set of updates to be made. If compare checks succeed for all updates, perform all updates.
   *                If any compare check fails, reject all updates.
   * @return true if updates were made, false otherwise
   * @throws Exception
   */
  default boolean compareAndSwap(
      List<MetadataCASUpdate> updates
  ) throws Exception
  {
    throw new UnsupportedOperationException("compareAndSwap is not implemented.");
  }

  void createDataSourceTable();

  void createPendingSegmentsTable();

  void createSegmentTable();

  void createRulesTable();

  void createConfigTable();

  void createTaskTables();

  void createAuditTable();

  void createSupervisorsTable();

  void deleteAllRecords(String tableName);
}
