package com.amitsah.launchmodedemo

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

open class BaseActivity : ComponentActivity() {
    private lateinit var activityManager:ActivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        println(getTaskStats())
    }


    private fun getTaskStats(): String {
        val stringBuilder = StringBuilder()
        val totalTasks: Int = activityManager.appTasks.size
        stringBuilder.append("\nTasks Count: $totalTasks\n\n")
        val taskInfo: List<ActivityManager.RunningTaskInfo> =
            activityManager.getRunningTasks(10)
        for (info in taskInfo) {
            stringBuilder.append(
                """
                Task Id: ${info.id}, Number of Activities : ${info.numActivities}  
                
                """.trimIndent()
            )
            stringBuilder.append(
                """
                TopActivity: ${
                    info.topActivity?.className
                }   
                
                """.trimIndent()
            )
            stringBuilder.append(
                """
                BaseActivity:${
                    info.baseActivity?.className
                }
    
                """.trimIndent()
            )
            stringBuilder.append("\n\n")

        }
        return stringBuilder.toString()
    }

}

@Composable
fun CommonActivityLaunchingUI(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            context.startActivity(Intent(context, MainActivity::class.java))
        }) {
            Text(text = "Start Main Activity")
        }
        Button(onClick = {
            context.startActivity(Intent(context, Activity2::class.java))
        }) {
            Text(text = "Start Activity 2")
        }
        Button(onClick = {
            context.startActivity(Intent(context, Activity3::class.java))
        }) {
            Text(text = "Start Activity 3")
        }
        Button(onClick = {
            context.startActivity(Intent(context, Activity4::class.java))
        }) {
            Text(text = "Start Activity 4")
        }
    }
}



