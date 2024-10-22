package com.project.affirmations.data

import com.project.affirmations.R
import com.project.affirmations.model.Affirmation

class Datasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.image1 , R.string.author1),
            Affirmation(R.string.affirmation2, R.drawable.image2 , R.string.author2),
            Affirmation(R.string.affirmation3, R.drawable.image3 , R.string.author3),
            Affirmation(R.string.affirmation4, R.drawable.image4 , R.string.author4),
            Affirmation(R.string.affirmation5, R.drawable.image5 , R.string.author5),
            Affirmation(R.string.affirmation6, R.drawable.image6 , R.string.author6),
            Affirmation(R.string.affirmation7, R.drawable.image7 , R.string.author7),
            Affirmation(R.string.affirmation8, R.drawable.image8 , R.string.author8),
            Affirmation(R.string.affirmation9, R.drawable.image9 , R.string.author9),
            Affirmation(R.string.affirmation10, R.drawable.image10 , R.string.author10),
        )
    }
}