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
package gov.nasa.jpl.imce.oml

import java.util.stream.Collectors
import java.util.Comparator

import scala.collection.immutable.Seq
import scala.collection.JavaConverters._
import scala.math.Ordering

package object parallelSort {

  def parSortBy[A, B]
  (seq: Seq[A], f: A => B)
  (implicit ord: Ordering[B])
  : Seq[A]
  = if (seq.isEmpty) seq
  else {
    val array = new java.util.ArrayList[A](seq.size)
    array.addAll(seq.asJava)

    val comparator: Comparator[_ >: A] = ord on f

    val sorted = array.parallelStream.sorted(comparator).collect(Collectors.toList[A])

    val result: Seq[A] = sorted.asScala.to[Seq]

    result
  }
}
