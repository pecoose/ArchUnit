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
package com.tngtech.archunit.core.domain.properties;

import com.tngtech.archunit.PublicAPI;
import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaOwnership;

import static com.tngtech.archunit.PublicAPI.Usage.ACCESS;

@PublicAPI(usage = ACCESS)
public interface HasOwnership {

    @PublicAPI(usage = ACCESS)
    JavaOwnership getOwnership();

    @PublicAPI(usage = ACCESS)
    final class Predicates {
        private Predicates() {
        }

        @PublicAPI(usage = ACCESS)
        public static DescribedPredicate<HasOwnership> ownership(JavaOwnership ownership) {
            return new OwnershipPredicate(ownership);
        }

        private static class OwnershipPredicate extends DescribedPredicate<HasOwnership> {

            private final JavaOwnership ownership;

            OwnershipPredicate(JavaOwnership ownership) {
                super("ownership " + ownership);
                this.ownership = ownership;
            }

            @Override
            public boolean test(HasOwnership input) {
                if (input.getOwnership() == null) {
                    return true;
                }
                return input.getOwnership().equals(this.ownership);
            }
        }
    }
}
