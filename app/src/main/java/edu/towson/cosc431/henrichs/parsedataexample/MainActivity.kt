package edu.towson.cosc431.henrichs.parsedataexample

import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.time.LocalDate


class MainActivity : AppCompatActivity() {



    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val date = LocalDate.now().toString()
      //  val tasks = (1..10).map { Task( it,"Title"+it, ""+it, it%3==0, "", date) }

        my_recycler_view.layoutManager = LinearLayoutManager(this)
        //my_recycler_view.adapter = MyAdapter(tasks)

        fetchJson()

    }


    private fun fetchJson() {
        println("Attempting to fetch JSON")

        val myUrl = "https://my-json-server.typicode.com/rvalis-towson/todos_api/todos"

        val request = Request.Builder().url(myUrl).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{

            override fun onResponse(call: Call, response: Response) {

                val body = response.body()?.string()
                println(body)

                val gson = GsonBuilder().create()

                val listFeed: List<Task> = gson.fromJson(body, Array<Task>::class.java).toList()

                runOnUiThread {
                    my_recycler_view.adapter = MyAdapter(listFeed)
                }
                //val listFeed: List<Task> = gson.fromJson(body, listOf<Task>()::class.java)

            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }
        })
    }

}

class Task(
    val id: Int,
    val title: String,
    val contents: String,
    val completed: Boolean,
    val image_url: String,
    val dateCreated: String
) {}
