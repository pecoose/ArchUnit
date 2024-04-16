/*
 * Copyright 2014-2024 TNG Technology Consulting GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tngtech.archunit.core.importer.enre;

public class EnreLocationPO {

    private Integer startLine;
    private Integer endLine;
    private Integer startColumn;
    private Integer endColumn;

    public EnreLocationPO() {
    }

    public EnreLocationPO(Integer startLine, Integer endLine, Integer startColumn, Integer endColumn) {
        this.startLine = startLine;
        this.endLine = endLine;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public void setStartLine(Integer startLine) {
        this.startLine = startLine;
    }

    public Integer getEndLine() {
        return endLine;
    }

    public void setEndLine(Integer endLine) {
        this.endLine = endLine;
    }

    public Integer getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(Integer startColumn) {
        this.startColumn = startColumn;
    }

    public Integer getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(Integer endColumn) {
        this.endColumn = endColumn;
    }
}
