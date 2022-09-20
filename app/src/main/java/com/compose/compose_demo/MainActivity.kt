package com.compose.compose_demo

import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.compose.compose_demo.model.MainViewModel
import com.compose.compose_demo.ui.theme.ComposedemoTheme

class MainActivity : ComponentActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposedemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {

        /* Button(onClick = {
             viewModel.getBannerData()
         }, modifier = Modifier
             .width(80.dp)
             .height(30.dp)
             .background(color = Color.Red), content = {
             Text(text = "点击发起网络请求")
         })*/

        Row() {
            Text(
                text = "点击发起网络请求",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.Blue)
                    .border(2.dp, color = Color.Red, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
                    .clickable {
                        viewModel.getBannerData()
                    },
                style = TextStyle(color = Color.White)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposedemoTheme {
    }
}