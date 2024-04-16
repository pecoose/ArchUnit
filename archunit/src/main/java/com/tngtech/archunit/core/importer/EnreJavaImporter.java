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
package com.tngtech.archunit.core.importer;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.enre.EnreModel;
import com.tngtech.archunit.core.importer.enre.EnreUtils;
import com.tngtech.archunit.core.importer.enre.FacadeModel;
import com.tngtech.archunit.core.importer.enre.FacadeUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnreJavaImporter {

    public static EnreModel enre() {
        File outFilePath = null;
        try {
            URL binPath = EnreJavaImporter.class.getClassLoader().getResource("bin");
            File fileBinPath = new File(binPath.toURI());
//            ProcessBuilder builder = new ProcessBuilder().inheritIO();
            ProcessBuilder builder = new ProcessBuilder();
            builder.directory(fileBinPath);
            String enreExecutableName = EnreUtils.enreExecutableName;
            String projectPath = "/Users/peco/Work/build/ArchUnit/archunit";
            String projectName = "archunit";
            String outDirName = String.format(EnreUtils.enreOutputDirNameTemplate, projectName);
            String outFileName = String.format(EnreUtils.enreOutputFileNameTemplate, projectName);
            List<String> xargs = new ArrayList<>(Arrays.asList("java", "-jar", enreExecutableName, "java", projectPath, projectName));
            builder.command(xargs);
//            System.out.println("cmd: " + builder.command().toString());
            Process proc = null;
            /*
            try {
                proc = builder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
                }
                int exitCode = proc.waitFor();
                System.out.println("exit " + exitCode);
                if (exitCode != 0) {
                    System.out.println("enre-java failed");
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (proc != null) {
                    proc.destroy();
                }
            }
             */
            File outDirPath = new File(fileBinPath, outDirName);
            outFilePath = new File(outDirPath, outFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EnreUtils.readJson(outFilePath.getPath());
    }
}
