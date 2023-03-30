package br.com.niltoneapontes.orgs

import android.app.Activity
import android.os.Bundle
import android.widget.Toast

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "Bem vinde ao Orgs", Toast.LENGTH_LONG).show()
    }
}