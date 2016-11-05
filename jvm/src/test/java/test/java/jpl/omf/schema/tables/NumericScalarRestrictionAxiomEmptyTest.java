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

package test.java.jpl.omf.schema.tables;

import org.junit.Test;
import org.junit.Assert;

import gov.nasa.jpl.imce.omf.schema.tables.NumericScalarRestrictionAxiom;
import gov.nasa.jpl.imce.omf.schema.tables.NumericScalarRestrictionAxiomJava;
import gov.nasa.jpl.imce.omf.schema.tables.NumericScalarRestrictionAxiomHelper;

import scala.compat.java8.OptionConverters;

import java.util.Optional;


public class NumericScalarRestrictionAxiomEmptyTest {

    @Test
    @SuppressWarnings("unchecked")
    public void creationTest() {
        String graphUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";

        Optional maxExclusive = Optional.empty();
        Optional maxInclusive = Optional.empty();
        Optional minExclusive = Optional.empty();
        Optional minInclusive = Optional.empty();

        String restrictedScalarUUID = "4567-2345-ABCD-1245";
        String scalarUUID = "1245-ABCD-2345-4567";

        NumericScalarRestrictionAxiom w1 = NumericScalarRestrictionAxiomJava.javaNumericScalarRestrictionAxiom(graphUUID, uuid, maxExclusive, maxInclusive, minExclusive, minInclusive, restrictedScalarUUID, scalarUUID);
        Assert.assertEquals(w1.minExclusive(), OptionConverters.toScala(minExclusive));
        String s1 = NumericScalarRestrictionAxiomHelper.toJSON(w1);

        scala.Option maxE = OptionConverters.toScala(maxExclusive);
        String maxE_s = (maxE.isEmpty()) ? "[]" : maxE.toString();

        scala.Option maxI = OptionConverters.toScala(maxInclusive);
        String maxI_s = (maxI.isEmpty()) ? "[]" : maxI.toString();

        scala.Option minE = OptionConverters.toScala(minExclusive);
        String minE_s = (minE.isEmpty()) ? "[]" : minE.toString();

        scala.Option minI = OptionConverters.toScala(minInclusive);
        String minI_s = (minI.isEmpty()) ? "[]" : minI.toString();

        String t1 = String.format(
                "{\"graphUUID\":\"%s\",\"uuid\":\"%s\",\"maxExclusive\":%s,\"maxInclusive\":%s,\"minExclusive\":%s,\"minInclusive\":%s,\"restrictedScalarUUID\":\"%s\",\"scalarUUID\":\"%s\"}",
                graphUUID, uuid, maxE_s, maxI_s, minE_s, minI_s, restrictedScalarUUID, scalarUUID);
        Assert.assertEquals(t1, s1);

        NumericScalarRestrictionAxiom r1 = NumericScalarRestrictionAxiomHelper.fromJSON(s1);
        Assert.assertEquals(w1.graphUUID(), r1.graphUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.maxExclusive(), r1.maxExclusive());
        Assert.assertEquals(w1.minInclusive(), r1.minInclusive());
        Assert.assertEquals(w1.restrictedScalarUUID(), r1.restrictedScalarUUID());
        Assert.assertEquals(w1.scalarUUID(), r1.scalarUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}
