package com.gubadev.injectproject

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gubadev.injectproject.rest.GithubRepositoriesService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var service: GithubRepositoriesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApp).getAppComponent().inject(this)
        setContentView(R.layout.activity_main)

        searchButton.setOnClickListener({
            textView.text = ""
            showRepos()
        })
    }

    private fun showRepos() {
        service.getRepos(editor.editableText.toString()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap({ repos -> Observable.fromIterable(repos) })
                .subscribe(
                        { t ->
                            textView.text = StringBuilder(textView.text.toString())
                                    .append(" ")
                                    .append(t.name)
                                    .append("\n")
                                    .toString()
                        },
                        {
                            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
                        })
    }


}