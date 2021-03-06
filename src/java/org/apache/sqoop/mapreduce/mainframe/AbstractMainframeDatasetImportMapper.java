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

package org.apache.sqoop.mapreduce.mainframe;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.sqoop.config.ConfigurationConstants;
import org.apache.sqoop.lib.SqoopRecord;
import org.apache.sqoop.mapreduce.AutoProgressMapper;

import java.io.IOException;

public abstract class AbstractMainframeDatasetImportMapper<KEY>
  extends AutoProgressMapper<LongWritable, SqoopRecord, KEY, NullWritable> {

  private MainframeDatasetInputSplit inputSplit;
  private MultipleOutputs<KEY, NullWritable> multiFileWriter;
  private long numberOfRecords;

  public void map(LongWritable key,  SqoopRecord val, Context context)
    throws IOException, InterruptedException {
    String dataset = inputSplit.getCurrentDataset();
    numberOfRecords++;
    multiFileWriter.write(createOutKey(val), NullWritable.get(), dataset);
  }

  @Override
  protected void setup(Context context)
    throws IOException, InterruptedException {
    super.setup(context);
    inputSplit = (MainframeDatasetInputSplit)context.getInputSplit();
    multiFileWriter = new MultipleOutputs<>(context);
    numberOfRecords = 0;
  }

  @Override
  protected void cleanup(Context context)
    throws IOException, InterruptedException {
    super.cleanup(context);
    multiFileWriter.close();
    context.getCounter(
      ConfigurationConstants.COUNTER_GROUP_MAPRED_TASK_COUNTERS,
      ConfigurationConstants.COUNTER_MAP_OUTPUT_RECORDS)
      .increment(numberOfRecords);
  }

  protected abstract KEY createOutKey(SqoopRecord sqoopRecord);
}