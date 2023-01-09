/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example;

import com.google.cloud.contentwarehouse.v1.DocumentSchema;
import com.google.cloud.contentwarehouse.v1.DocumentSchemaServiceClient;
import com.google.cloud.contentwarehouse.v1.LocationName;

public class ListSchema {
  public static String PROJECT_NUMBER;
  public static String LOCATION;

  private final LocationName parent = LocationName.of(PROJECT_NUMBER, LOCATION);

  /**
   * List the schema that are defined in Document AI Warehouse.
   */
  public void listSchema() {
    try {
      try (DocumentSchemaServiceClient documentSchemaServiceClient = DocumentSchemaServiceClient.create()) {
        DocumentSchemaServiceClient.ListDocumentSchemasPagedResponse response = documentSchemaServiceClient.listDocumentSchemas(parent);
        System.out.println("display name    name");
        System.out.println("--------------- ------------------------------------------------------------------------");
        for (DocumentSchema currentSchema: response.iterateAll()) {
          System.out.printf("%-15.15s %s\n",currentSchema.getDisplayName() , currentSchema.getName());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  } // listSchema

  public static void main(String[] args) {
    ListSchema.PROJECT_NUMBER = System.getenv("PROJECT_NUMBER");
    if (ListSchema.PROJECT_NUMBER == null) {
      System.err.println("No PROJECT_NUMBER environment variable set");
      return;
    }
    ListSchema.LOCATION = System.getenv("LOCATION");
    if (ListSchema.LOCATION == null) {
      ListSchema.LOCATION = "us"; // Default to us location
    }

    ListSchema app = new ListSchema();
    app.listSchema();
    System.out.println("ListSchema completed");
  } // main
} // ListSchema
