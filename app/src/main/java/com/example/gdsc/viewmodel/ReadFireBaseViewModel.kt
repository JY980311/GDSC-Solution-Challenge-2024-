package com.example.gdsc.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class ReadFireBaseViewModel : ViewModel() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val dbCourses: CollectionReference = db.collection("Water")
    var waterQ = mutableStateOf(0)

    fun getCourses(name: String) {
        dbCourses.whereEqualTo("name", name).get().addOnSuccessListener { documents ->
            if(!documents.isEmpty) {
                waterQ.value = documents.documents[0].get("q").toString().toInt()
            }
        }
    }
}
