package com.ejemplo1.apivolley

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class MainActivity : AppCompatActivity() {
    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queue = Volley.newRequestQueue(this)
    }

    fun BuscarUsr(view: View){
        val url = "https://gorest.co.in/public/v1/users"

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response -> ShowData(response.getJSONArray("data"))},
            { error -> println(error.message) }
        )
        queue?.add(request)



    }

    fun ShowData(users: JSONArray) {
        var txtElementos: EditText = findViewById(R.id.txtElementos)
        for (i in 0 until users.length()) {
            val employee = users.getJSONObject(i)
            val id = employee.getString("id")
            val name = employee.getString("name")
            val email = employee.getString("email")
            val gender = employee.getString("gender")
            val status = employee.getString("status")
            txtElementos.append("Id: $id\n")
            txtElementos.append("Nombre: $name\n")
            txtElementos.append("Email: $email\n")
            txtElementos.append("Genero: $gender\n")
            txtElementos.append("Status: $status\n\n")
        }
    }
}