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

package test.java.oml.tables;

import org.junit.Test;
import org.junit.Assert;

import gov.nasa.jpl.imce.oml.tables.NumericScalarRestriction;
import gov.nasa.jpl.imce.oml.tables.NumericScalarRestrictionHelper;


public class NumericScalarRestrictionEmptyTest {

    @Test
    public void creationTest() {
        String tboxUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";
        String name = "abc";
        String restrictedRangeUUID = "1245-ABCD-2345-4567";

        NumericScalarRestriction w1 = new NumericScalarRestriction(uuid, tboxUUID, restrictedRangeUUID, name);

        String s1 = NumericScalarRestrictionHelper.toJSON(w1);

        String maxE_s = (w1.maxExclusive().isEmpty()) ? "null" : "\"" + w1.maxExclusive().toString() + "\"";

        String maxI_s = (w1.maxInclusive().isEmpty()) ? "null" : "\"" + w1.maxInclusive().toString() + "\"";

        String minE_s = (w1.minExclusive().isEmpty()) ? "null" : "\"" + w1.minExclusive().toString() + "\"";

        String minI_s = (w1.minInclusive().isEmpty()) ? "null" : "\"" + w1.minInclusive().toString() + "\"";

        String t1 = String.format(
                "{\"uuid\":\"%s\",\"tboxUUID\":\"%s\",\"restrictedRangeUUID\":\"%s\",\"minExclusive\":%s,\"minInclusive\":%s,\"maxExclusive\":%s,\"maxInclusive\":%s,\"name\":\"%s\"}",
                uuid, tboxUUID, restrictedRangeUUID, minE_s, minI_s, maxE_s, maxI_s, name);
        Assert.assertEquals(t1, s1);

        NumericScalarRestriction r1 = NumericScalarRestrictionHelper.fromJSON(s1);
        Assert.assertEquals(w1.tboxUUID(), r1.tboxUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.name(), r1.name());
        Assert.assertEquals(w1.maxExclusive(), r1.maxExclusive());
        Assert.assertEquals(w1.maxInclusive(), r1.maxInclusive());
        Assert.assertEquals(w1.minExclusive(), r1.minExclusive());
        Assert.assertEquals(w1.minInclusive(), r1.minInclusive());
        Assert.assertEquals(w1.restrictedRangeUUID(), r1.restrictedRangeUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}
