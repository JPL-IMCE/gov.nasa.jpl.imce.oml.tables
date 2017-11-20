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

import gov.nasa.jpl.imce.oml.tables.LiteralDateTime;
import gov.nasa.jpl.imce.oml.tables.TimeScalarRestriction;
import gov.nasa.jpl.imce.oml.tables.TimeScalarRestrictionHelper;

public class TimeScalarRestrictionTest {

    @Test
    public void creationTest() {
        String tboxUUID = "01234-abcde-4569-fehi";
        String uuid = "12345-BCDEF-6789A-012345";
        String name = "abc";
        String restrictedRangeUUID = "1245-ABCD-2345-4567";

        TimeScalarRestriction w1 = new TimeScalarRestriction(uuid, tboxUUID, restrictedRangeUUID, name)
                .withMaxExclusive(new LiteralDateTime("dateTime=123"))
                .withMinExclusive(new LiteralDateTime("dateTime=4567"))
                .withMaxInclusive(new LiteralDateTime("dateTime=333"))
                .withMinInclusive(new LiteralDateTime("dateTime=678"));

        String s1 = TimeScalarRestrictionHelper.toJSON(w1);

        String maxE_s = "\"" + w1.maxExclusive().get().value() + "\"";

        String maxI_s = "\"" + w1.maxInclusive().get().value() + "\"";

        String minE_s = "\"" + w1.minExclusive().get().value() + "\"";

        String minI_s = "\"" + w1.minInclusive().get().value() + "\"";

        String t1 = String.format(
                "{\"uuid\":\"%s\",\"tboxUUID\":\"%s\",\"restrictedRangeUUID\":\"%s\",\"minExclusive\":%s,\"minInclusive\":%s,\"maxExclusive\":%s,\"maxInclusive\":%s,\"name\":\"%s\"}",
                uuid, tboxUUID, restrictedRangeUUID, minE_s, minI_s, maxE_s, maxI_s, name);
        Assert.assertEquals(t1, s1);

        TimeScalarRestriction r1 = TimeScalarRestrictionHelper.fromJSON(s1);
        Assert.assertEquals(w1.tboxUUID(), r1.tboxUUID());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.uuid(), r1.uuid());
        Assert.assertEquals(w1.name(), r1.name());
        Assert.assertEquals(w1.maxExclusive(), r1.maxExclusive());
        Assert.assertEquals(w1.minInclusive(), r1.minInclusive());
        Assert.assertEquals(w1.restrictedRangeUUID(), r1.restrictedRangeUUID());
        Assert.assertTrue(w1.equals(r1));
    }
}
