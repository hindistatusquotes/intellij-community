/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.uast

import com.intellij.psi.PsiElement
import org.jetbrains.uast.internal.log

open class UIdentifier(
  override val psi: PsiElement?,
  override val uastParent: UElement?
) : JvmDeclarationUElement {
  /**
   * Returns the identifier name.
   */
  open val name: String
    get() = psi?.text ?: "<error>"

  override fun asLogString() = log("Identifier ($name)")

  override val sourcePsi: PsiElement? = psi

  override val javaPsi: PsiElement? = null
}

open class LazyParentUIdentifier(psi: PsiElement?, private val givenParent: UElement?) : UIdentifier(psi, givenParent) {

  override val uastParent: UElement? by lazy { givenParent ?: sourcePsi?.parent?.toUElement() }

}