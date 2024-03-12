package com.example.keureujakeun

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** Raihan Chaira on 3/4/2024
 * raihanchaira21@gmail.com
 */
@Parcelize
data class Todo (
    val checkDone : Boolean,
    val todoTittle : String,
    val todoDate : String
) : Parcelable