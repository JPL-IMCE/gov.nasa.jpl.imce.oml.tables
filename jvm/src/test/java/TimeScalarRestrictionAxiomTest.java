/*
 * Copyright 2016 California Institute of Technology ("Caltech").
 * U.S. Government sponsorship acknowledged.
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
 * License Terms
 */

import org.junit.Test;
import org.junit.Assert;

import gov.nasa.jpl.imce.omf.schema.tables.TimeScalarRestrictionAxiom;
import gov.nasa.jpl.imce.omf.schema.tables.TimeScalarRestrictionAxiomJava;
import gov.nasa.jpl.imce.omf.schema.tables.TimeScalarRestrictionAxiomHelper;

import scala.compat.java8.OptionConverters;

import java.util.Optional;

public class TimeScalarRestrictionAxiomTest {

    @Test
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";

        Optional maxExclusive = Optional.of("123");
        Optional maxInclusive = Optional.of("4567");
        Optional minExclusive = Optional.of("333");
        Optional minInclusive = Optional.of("678");

        String restrictedScalarUUID = "4567-2345-ABCD-1245";
        String scalarUUID = "1245-ABCD-2345-4567";

        TimeScalarRestrictionAxiom w1 = TimeScalarRestrictionAxiomJava.javaTimeScalarRestrictionAxiom(graphUUID, uuid, maxExclusive, maxInclusive, minExclusive, minInclusive, restrictedScalarUUID, scalarUUID);

        // need to use OptionConverters so the types are compatible for comparison
        Assert.assertEquals(w1.minExclusive(), OptionConverters.toScala(minExclusive));
        String s1 = TimeScalarRestrictionAxiomHelper.toJSON(w1);

        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"maxExclusive\":[\"%s\"],\"maxInclusive\":[\"%s\"],\"minExclusive\":[\"%s\"],\"minInclusive\":[\"%s\"],\"restrictedScalarUUID\":\"%s\",\"scalarUUID\":\"%s\"}",
                graphUUID, uuid, OptionConverters.toScala(maxExclusive).get(), OptionConverters.toScala(maxInclusive).get(), OptionConverters.toScala(minExclusive).get(), OptionConverters.toScala(minInclusive).get(), restrictedScalarUUID, scalarUUID);

        Assert.assertEquals(t1, s1);

        TimeScalarRestrictionAxiom r1 = TimeScalarRestrictionAxiomHelper.fromJSON(s1);
        Assert.assertEquals(w1.graphUUID(), r1.graphUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.maxExclusive(), r1.maxExclusive());
        Assert.assertEquals(w1.minInclusive(), r1.minInclusive());
        Assert.assertEquals(w1.restrictedScalarUUID(), r1.restrictedScalarUUID());
        Assert.assertEquals(w1.scalarUUID(), r1.scalarUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}
