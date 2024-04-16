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

public class EnreUtils {

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

    public static EnreModel readJson(String filepath) {
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

    private static EnreEnhancementPO parseEnhancementDTO(JSONObject parent) {
        if (!verify(parent, "enhancement")) {
            return null;
        }
        JSONObject obj = (JSONObject) parent.get("enhancement");
        if (!verify(
                obj,
                "isGetter",
                "isRecursive",
                "isStatic",
                "isConstructor",
                "isOverride",
                "isSetter",
                "isPublic",
                "isDelegator",
                "isSynchronized",
                "isAbstract")) {
            return null;
        }
        return new EnreEnhancementPO(
                (Boolean) obj.get("isGetter"),
                (Boolean) obj.get("isRecursive"),
                (Boolean) obj.get("isStatic"),
                (Boolean) obj.get("isConstructor"),
                (Boolean) obj.get("isOverride"),
                (Boolean) obj.get("isSetter"),
                (Boolean) obj.get("isPublic"),
                (Boolean) obj.get("isDelegator"),
                (Boolean) obj.get("isSynchronized"),
                (Boolean) obj.get("isAbstract"));
    }

    private static EnreParameterPO parseParameterDTO(JSONObject parent) {
        if (!verify(parent, "parameter")) {
            return null;
        }
        JSONObject paramObj = (JSONObject) parent.get("parameter");
        if (!verify(paramObj, "types", "names")) {
            return null;
        }
        return new EnreParameterPO(
                (String) paramObj.get("types"), (String) paramObj.get("names"));
    }

    private static String parseInnerType(JSONObject parent) {
        if (!parent.has("innerType")) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        JSONArray types = (JSONArray) parent.get("innerType");
        for (int i = 0; i < types.length(); i++) {
            res.append(types.get(i));
            if (i != types.length() - 1) {
                res.append(",");
            }
        }
        return res.toString();
    }

    private static EnreEntityPO parseEntityObj(JSONObject variableObj) {
        EnreEntityPO res;
        Integer id = (Integer) variableObj.get("id");
        String name = (String) variableObj.get("name");
        String qualifiedName = (String) variableObj.get("qualifiedName");
        Boolean external = (Boolean) variableObj.get("external");
        String ownership = (String) variableObj.get("ownership");
        if (external) {
            res = new EnreExternalEntityPO(id, name, qualifiedName, ENRE_ENTITY_EXTERNAL, ownership);
        } else {
            String category = (String) variableObj.get("category");
            Integer parentId = (Integer) variableObj.get("parentId");
            String file = (String) get(variableObj, "File");
            String rawType = (String) get(variableObj, "rawType");
            Integer anonymousBindVar = (Integer) get(variableObj, "anonymousBindVar");
            Integer anonymousRank = (Integer) get(variableObj, "anonymousRank");
            String modifiers = (String) get(variableObj, "modifiers");
            Boolean global = (Boolean) get(variableObj, "global");
            EnreLocationPO location = parseLocation(variableObj, "location");
            String innerType = parseInnerType(variableObj);
            EnreEnhancementPO ene = parseEnhancementDTO(variableObj);
            EnreParameterPO enp = parseParameterDTO(variableObj);
            switch (category) {
                case "Package":
                    res = new EnrePackageEntityPO(id, name, qualifiedName, ENRE_ENTITY_PACKAGE, ownership, parentId);
                    break;
                case "File":
                    res = new EnreFileEntityPO(id, name, qualifiedName, ENRE_ENTITY_FILE, ownership, parentId, file);
                    break;
                case "Class":
                    if (location == null) {
                        return null;
                    }
                    if (anonymousRank == null || anonymousBindVar == null) {
                        res =
                                new EnreClassEntityPO(id, name, qualifiedName, ENRE_ENTITY_CLASS, ownership, parentId,
                                        rawType, modifiers, file, innerType, location);
                    } else {
                        res =
                                new EnreAnonymousClassEntityPO(id, name, qualifiedName, ENRE_ENTITY_ANONYMOUS_CLASS, ownership, parentId,
                                        rawType, file, anonymousBindVar, anonymousRank, location);
                    }
                    break;
                case "Enum":
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreEnumEntityPO(id, name, qualifiedName, ENRE_ENTITY_ENUM, ownership, parentId,
                                    rawType, modifiers, file, location);
                    break;
                case "Enum Constant":
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreEnumConstantEntityPO(id, name, qualifiedName, ENRE_ENTITY_ENUM_CONSTANT, ownership, parentId,
                                    file, location);
                    break;
                case "Annotation":
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreAnnotationEntityPO(id, name, qualifiedName, ENRE_ENTITY_ANNOTATION, ownership, parentId,
                                    rawType, modifiers, file, location);
                    break;
                case "Annotation Member":
                    if (location == null) {
                        return null;
                    }
                    res = new EnreAnnotationMemberEntityPO(id, name, qualifiedName, ENRE_ENTITY_ANNOTATION_MEMBER, ownership, parentId,
                            rawType, file, location);
                    break;
                case "Interface":
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreInterfaceEntityPO(id, name, qualifiedName, ENRE_ENTITY_INTERFACE, ownership, parentId,
                                    rawType, file, modifiers, location);
                    break;
                case "Method":
                    if (ene == null) {
                        return null;
                    }
                    if (enp == null) {
                        return null;
                    }
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreMethodEntityPO(id, name, qualifiedName, ENRE_ENTITY_METHOD, ownership, parentId,
                                    rawType, file, modifiers, ene,
                                    enp, location);
                    break;
                case "Module":
                    res = null;
                    break;
                case "Record":
                    res = null;
                    break;
                case "Type Parameter":
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreTypeParameterEntityPO(id, name, qualifiedName, ENRE_ENTITY_TYPE_PARAMETER, ownership, parentId,
                                    rawType, file, location);
                    break;
                case "Variable":
                    if (location == null) {
                        return null;
                    }
                    res =
                            new EnreVariableEntityPO(id, name, qualifiedName, ENRE_ENTITY_VARIABLE, ownership, parentId,
                                    rawType, file, modifiers, global, location);
                    break;
                default:
                    throw new RuntimeException("No such entity type: " + category);
            }
        }
        return res;
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

    private static EnreRelationPO parseCellObj(JSONObject obj) {
        if (!verify(obj, "src", "dest", "values")) {
            return null;
        }
        EnreRelationPO res = new EnreRelationPO();
        res.setSrc((Integer) obj.get("src"));
        res.setDest((Integer) obj.get("dest"));
        JSONObject valuesObj = (JSONObject) obj.get("values");
        EnreLocationPO loc = parseLocation(valuesObj, "loc");
        if (loc == null) {
            return null;
        }
        res.setStartLine(loc.getStartLine());
        res.setEndLine(loc.getEndLine());
        res.setStartColumn(loc.getStartColumn());
        res.setEndColumn(loc.getEndColumn());
        Iterator<String> it = valuesObj.keys();
        String key;
        while (it.hasNext()) {
            key = it.next();
            switch (key) {
                case "loc":
                    break;
                case "arguments":
                    res.setArguments((String) valuesObj.get("arguments"));
                    break;
                case "bindVar":
                    res.setBindVar((Integer) valuesObj.get("bindVar"));
                    break;
                case "modifyAccessible":
                    res.setModifyAccessible((Boolean) valuesObj.get("modifyAccessible"));
                    break;
                case "invoke":
                    res.setInvoke((Boolean) valuesObj.get("invoke"));
                    break;
                default:
                    res.setCategory(key);
                    res.setRelationCount((Integer) valuesObj.get(key));
            }
        }
        return res;
    }

    public static EnreModel parse(JSONObject obj) {
        EnreModel res = new EnreModel(
                new ArrayList<>(),
                new ArrayList<>(),
                new EnreStatPO()
        );
        if (obj.has("cells")) {
            Object cellsObj = obj.get("cells");
            if (cellsObj instanceof JSONObject) {
                EnreRelationPO enreRelation = parseCellObj((JSONObject) cellsObj);
                if (enreRelation != null) {
                    res.getCells().add(enreRelation);
                }
            } else if (cellsObj instanceof JSONArray) {
                for (Object cellObj : (JSONArray) cellsObj) {
                    EnreRelationPO enreRelation = parseCellObj((JSONObject) cellObj);
                    if (enreRelation != null) {
                        res.getCells().add(enreRelation);
                    }
                }
            } else {
                throw new RuntimeException("unknown object type for cells: " + cellsObj.getClass().getName());
            }
        }
        if (obj.has("variables")) {
            Object variablesObj = obj.get("variables");
            if (variablesObj instanceof JSONObject) {
                EnreEntityPO ent = parseEntityObj((JSONObject) variablesObj);
                if (ent != null) {
                    res.getVariables().add(ent);
                }
            } else if (variablesObj instanceof JSONArray) {
                for (Object variableObj : (JSONArray) variablesObj) {
                    EnreEntityPO ent = parseEntityObj((JSONObject) variableObj);
                    if (ent != null) {
                        res.getVariables().add(ent);
                    }
                }
            } else {
                throw new RuntimeException("unknown object type for variables: " + variablesObj.getClass().getName());
            }
        }
        if (obj.has("entityNum") && obj.has("relationNum")) {
            obj.get("entityNum");
            res.setStat(parseStat((JSONObject) obj.get("entityNum"), (JSONObject) obj.get("relationNum")));
        }
        return res;
    }
}
