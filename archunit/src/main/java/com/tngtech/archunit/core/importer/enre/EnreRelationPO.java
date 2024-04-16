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

public class EnreRelationPO {

    private Integer src;
    private Integer dest;
    private Integer startLine;
    private Integer endLine;
    private Integer startColumn;
    private Integer endColumn;
    private String category;
    private Integer relationCount;
    private Integer bindVar;
    private String arguments;
    private Boolean modifyAccessible;
    private Boolean invoke;

    public EnreRelationPO() {

    }

    public EnreRelationPO(Integer src, Integer dest, Integer startLine, Integer endLine, Integer startColumn, Integer endColumn, String category, Integer relationCount, Integer bindVar, String arguments, Boolean modifyAccessible, Boolean invoke) {
        this.src = src;
        this.dest = dest;
        this.startLine = startLine;
        this.endLine = endLine;
        this.startColumn = startColumn;
        this.endColumn = endColumn;
        this.category = category;
        this.relationCount = relationCount;
        this.bindVar = bindVar;
        this.arguments = arguments;
        this.modifyAccessible = modifyAccessible;
        this.invoke = invoke;
    }

    public Integer getSrc() {
        return src;
    }

    public void setSrc(Integer src) {
        this.src = src;
    }

    public Integer getDest() {
        return dest;
    }

    public void setDest(Integer dest) {
        this.dest = dest;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getRelationCount() {
        return relationCount;
    }

    public void setRelationCount(Integer relationCount) {
        this.relationCount = relationCount;
    }

    public Integer getBindVar() {
        return bindVar;
    }

    public void setBindVar(Integer bindVar) {
        this.bindVar = bindVar;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public Boolean getModifyAccessible() {
        return modifyAccessible;
    }

    public void setModifyAccessible(Boolean modifyAccessible) {
        this.modifyAccessible = modifyAccessible;
    }

    public Boolean getInvoke() {
        return invoke;
    }

    public void setInvoke(Boolean invoke) {
        this.invoke = invoke;
    }
}