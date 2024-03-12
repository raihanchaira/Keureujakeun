package com.example.keureujakeun

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/** Raihan Chaira on 3/12/2024
 * raihanchaira21@gmail.com
 */
@Parcelize
data class TodoAdd (
    var tittle : String,
    var date : String,
    var desc : String
):Parcelable