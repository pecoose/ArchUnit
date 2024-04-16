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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FacadeUtils {

    public static final String enreExecutableName = "enre_java.jar";
    public static final String enreOutputDirNameTemplate = "%s-enre-out";
    public static final String enreOutputFileNameTemplate = "%s-out.json";
    public static final String ENRE_ENTITY_EXTERNAL = "External";
    public static final String ENRE_ENTITY_PACKAGE = "Package";
    public static final String ENRE_ENTITY_FILE = "File";
    public static final String ENRE_ENTITY_CLASS = "Class";
    public static final String ENRE_ENTITY_ANONYMOUS_CLASS = "Anonymous Class";
    public static final String ENRE_ENTITY_ENUM = "Enum";
    public static final String ENRE_ENTITY_ENUM_CONSTANT = "Enum Constant";
    public static final String ENRE_ENTITY_ANNOTATION = "Annotation";
    public static final String ENRE_ENTITY_ANNOTATION_MEMBER = "Annotation Member";
    public static final String ENRE_ENTITY_INTERFACE = "Interface";
    public static final String ENRE_ENTITY_METHOD = "Method";
    public static final String ENRE_ENTITY_TYPE_PARAMETER = "Type Parameter";
    public static final String ENRE_ENTITY_VARIABLE = "Variable";

    public static FacadeModel readJson(String filepath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filepath)));
            JSONObject obj = new JSONObject(content);
            return parse(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Boolean verify(JSONObject obj, String... keys) {
        for (String key : keys) {
            if (!obj.has(key)) {
                return false;
            }
        }
        return true;
    }

    private static Object get(JSONObject obj, String key, Object defaultValue) {
        if (obj.has(key)) {
            return obj.get(key);
        }
        return defaultValue;
    }

    private static EnreLocationPO parseLocation(JSONObject parent, String key) {
        if (!verify(parent, key)) {
            return null;
        }
        JSONObject obj = (JSONObject) parent.get(key);
        if (!verify(obj, "startLine", "endLine", "startColumn", "endColumn")) {
            return null;
        }
        return new EnreLocationPO(
                (Integer) obj.get("startLine"),
                (Integer) obj.get("endLine"),
                (Integer) obj.get("startColumn"),
                (Integer) obj.get("endColumn")
        );
    }

    private static Object get(JSONObject obj, String key) {
        if (obj.has(key)) {
            return obj.get(key);
        }
        return null;
    }

    private static String parseIntrusiveModify(JSONObject obj) {
        if (!verify(obj, "intrusiveModify")) {
            return null;
        }
        JSONObject intrusiveModifyObj = (JSONObject) obj.get("intrusiveModify");
        if (!verify(intrusiveModifyObj, "class_body_modify")) {
            return null;
        }
        return (String) intrusiveModifyObj.get("class_body_modify");
    }

    private static AbstractFacadeEntityPO parseEntityObj(JSONObject variableObj) {
        Integer id = (Integer) variableObj.get("id");
        Integer isIntrusive = (Integer) variableObj.get("isIntrusive");
        Integer isDecoupling = (Integer) variableObj.get("is_decoupling");
        String name = (String) variableObj.get("name");
        Integer notAOSP = (Integer) variableObj.get("not_aosp");
        Integer oldAOSP = (Integer) variableObj.get("old_aosp");
        String ownership = (String) variableObj.get("ownership");
        String qualifiedName = (String) variableObj.get("qualifiedName");
        Integer entityMapping = (Integer) variableObj.get("entity_mapping");
        Integer commitsCount = (Integer) variableObj.get("commits_count");
        String category = (String) variableObj.get("category");
        Integer calledTimes = (Integer) variableObj.get("called_times");
        String packageName = (String) get(variableObj, "packageName");
        String rawType = (String) get(variableObj, "rawType");
        String File = (String) get(variableObj, "File");
        String modifiers = (String) get(variableObj, "modifiers");
        Boolean isGlobal = (Boolean) get(variableObj, "global");
        String parameterTypes = (String) get(variableObj, "parameterTypes");
        String parameterNames = (String) get(variableObj, "parameterNames");
        String classBodyModify = parseIntrusiveModify(variableObj);
        EnreLocationPO location = parseLocation(variableObj, "location");
        AbstractFacadeEntityPO result;
        switch (category) {
            case "Package":
                result = new FacadePackageEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes);
                break;
            case "File":
                result = new FacadeFileEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        packageName);
                break;
            case "Class":
                result = new FacadeClassEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName, classBodyModify, rawType, location, modifiers);
                break;
            case "Enum":
                if (location == null) {
                    return null;
                }
                result = new FacadeEnumEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName, rawType, location, modifiers);
                break;
            case "Enum Constant":
                if (location == null) {
                    return null;
                }
                result = new FacadeEnumConstantEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName);
                break;
            case "Annotation":
                if (location == null) {
                    return null;
                }
                result = new FacadeAnnotationEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName, rawType, location, modifiers);
                break;
            case "Interface":
                if (location == null) {
                    return null;
                }
                result = new FacadeInterfaceEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName, classBodyModify, rawType, location, modifiers);
                break;
            case "Method":
                if (location == null) {
                    return null;
                }
                result = new FacadeMethodEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName, classBodyModify, rawType, parameterTypes, parameterNames, location, modifiers);
                break;
            case "Variable":
                if (location == null) {
                    return null;
                }
                result = new FacadeVariableEntityPO(id, isIntrusive, isDecoupling, name, notAOSP, oldAOSP, ownership,
                        qualifiedName, entityMapping, commitsCount, category, calledTimes,
                        File, packageName, classBodyModify, isGlobal, rawType, location, modifiers);
                break;
            default:
                throw new RuntimeException("No such entity type: " + category);
        }
        return result;
    }

    private static EnreStatPO parseStat(JSONObject entityNum, JSONObject relationNum) {
        EnreStatPO res = new EnreStatPO();
        res.setPackageEntityNumber((Integer) get(entityNum, "Package", 0));
        res.setFileEntityNumber((Integer) get(entityNum, "File", 0));
        res.setClassEntityNumber((Integer) get(entityNum, "Class", 0));
        res.setEnumEntityNumber((Integer) get(entityNum, "Enum", 0));
        res.setEnumConstantEntityNumber((Integer) get(entityNum, "Enum Constant", 0));
        res.setAnnotationEntityNumber((Integer) get(entityNum, "Annotation", 0));
        res.setAnnotationMemberEntityNumber((Integer) get(entityNum, "Annotation Member", 0));
        res.setInterfaceEntityNumber((Integer) get(entityNum, "Interface", 0));
        res.setMethodEntityNumber((Integer) get(entityNum, "Method", 0));
        res.setVariableEntityNumber((Integer) get(entityNum, "Variable", 0));
        res.setTypeParameterEntityNumber((Integer) get(entityNum, "TypeParameter", 0));
        res.setImportRelationNumber((Integer) get(relationNum, "Import", 0));
        res.setInheritRelationNumber((Integer) get(relationNum, "Inherit", 0));
        res.setImplementRelationNumber((Integer) get(relationNum, "Implement", 0));
        res.setContainRelationNumber((Integer) get(relationNum, "Contain", 0));
        res.setCallRelationNumber((Integer) get(relationNum, "Call", 0));
        res.setParameterRelationNumber((Integer) get(relationNum, "Parameter", 0));
        res.setTypedRelationNumber((Integer) get(relationNum, "Typed", 0));
        res.setUseVarRelationNumber((Integer) get(relationNum, "UseVar", 0));
        res.setSetRelationNumber((Integer) get(relationNum, "Set", 0));
        res.setModifyRelationNumber((Integer) get(relationNum, "Modify", 0));
        res.setAnnotateRelationNumber((Integer) get(relationNum, "Annotate", 0));
        res.setCastRelationNumber((Integer) get(relationNum, "Cast", 0));
        res.setOverrideRelationNumber((Integer) get(relationNum, "Override", 0));
        res.setReflectRelationNumber((Integer) get(relationNum, "Reflect", 0));
        res.setDefineRelationNumber((Integer) get(relationNum, "Define", 0));
        return res;
    }

    private static FacadeRelationPair parseRelation(JSONObject obj) {
        JSONObject srcObj = (JSONObject) obj.get("src");
        JSONObject destObj = (JSONObject) obj.get("dest");
        JSONObject valuesObj = (JSONObject) obj.get("values");
        FacadeRelationPO relation = parseValuesObj(valuesObj);
        AbstractFacadeEntityPO srcEntity = parseEntityObj(srcObj);
        AbstractFacadeEntityPO destEntity = parseEntityObj(destObj);
        if (srcEntity == null || destEntity == null) {
            return null;
        }
        relation.setSrcId(srcEntity.getId());
        relation.setDestId(destEntity.getId());
        return new FacadeRelationPair(srcEntity, destEntity, relation);
    }

    private static FacadeRelationPO parseValuesObj(JSONObject obj) {
        FacadeRelationPO result = new FacadeRelationPO();
        Iterator<String> it = obj.keys();
        String key;
        while (it.hasNext()) {
            key = it.next();
            switch (key) {
                case "bindVar":
                    result.setBindVar((Integer) obj.get("bindVar"));
                    break;
                case "modifyAccessible":
                    result.setModifyAccessible((Boolean) obj.get("modifyAccessible"));
                    break;
                case "invoke":
                    result.setInvoke((Boolean) obj.get("invoke"));
                    break;
                default:
                    result.setCategory(key);
            }
        }
        return result;
    }

    private static void fill(JSONObject obj, List<AbstractFacadeEntityPO> variables, List<FacadeRelationPO> cells, String key) {
        FacadeRelationPair pair;
        if (obj.has(key)) {
            Object e2nObj = obj.get(key);
            if (e2nObj instanceof JSONObject) {
                pair = parseRelation((JSONObject) e2nObj);
                if (pair != null) {
                    variables.add(pair.getSrc());
                    variables.add(pair.getDest());
                    cells.add(pair.getRelation());
                }
            } else if (e2nObj instanceof JSONArray) {
                for (Object cellObj : (JSONArray) e2nObj) {
                    pair = parseRelation((JSONObject) cellObj);
                    if (pair != null) {
                        variables.add(pair.getSrc());
                        variables.add(pair.getDest());
                        cells.add(pair.getRelation());
                    }
                }
            } else {
                throw new RuntimeException("unknown object type for cells: " + e2nObj.getClass().getName());
            }
        }
    }

    public static FacadeModel parse(JSONObject obj) {
        FacadeModel result = new FacadeModel(new ArrayList<>(), new ArrayList<>());
        fill(obj, result.getVariables(), result.getCells(), "e2n");
        fill(obj, result.getVariables(), result.getCells(), "e2e");
        fill(obj, result.getVariables(), result.getCells(), "n2n");
        return result;
    }
}
