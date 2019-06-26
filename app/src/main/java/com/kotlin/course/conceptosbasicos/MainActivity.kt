package com.kotlin.course.conceptosbasicos

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.startActivity
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    var adapter = MediaAdapter { (id) -> navigateToDetail(id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        job = Job()

        recycler.adapter = adapter
        MediaProvider.dataAsync { adapter.items = it }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val filter: Filter = when (item.itemId) {
            R.id.filter_photos -> Filter.ByMediaType(MediaItem.Type.PHOTO)
            R.id.filter_videos -> Filter.ByMediaType(MediaItem.Type.VIDEO)
            else -> Filter.None
        }

        filter?.let {
            GlobalScope.launch(Dispatchers.Main) {
                progressBar.show()
                val media1 = async(Dispatchers.IO) { MediaProvider.dataSync("cats")}
                val media2 = async(Dispatchers.IO) { MediaProvider.dataSync("nature")}
                loadFilterData(media1.await() + media2.await())
            }
        }

        return true
    }

    private fun loadFilterData(media: List<MediaItem>, filter: Filter = Filter.None) {
        adapter.items = when (filter) {
            Filter.None -> media
            is Filter.ByMediaType -> media.filter { it.type == filter.type }
        }
        progressBar.hide()
    }

    private fun navigateToDetail(id: Int) {
        startActivity<DetailActivity>(DetailActivity.ID to id)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}