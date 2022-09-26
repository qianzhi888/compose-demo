package com.compose.compose_demo

import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
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
                    color = Color.White
                ) {
                    Greeting("Android")
                }
            }
        }
    }


    private val verticalGradientBrush by lazy {
        Brush.verticalGradient(
            colors = listOf(
                Color.Red,
                Color.Green,
                Color.Yellow
            )
        )
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

        //在compose中使用modifier的属性时，需要注意调用顺序，不同的调用顺序所显示的效果也有所不同
        Row(
            modifier = Modifier
                .padding(16.dp)//设置外间隙
                .fillMaxWidth() //宽度填满整个父控件
                .height(150.dp)
                .clip(shape = RoundedCornerShape(12.dp))//裁剪
                .background(color = Color.Gray)
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(12.dp))//圆角边框
                .padding(8.dp)//设置内间隙
        ) {
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)//将宽度和高度同时设置为60dp
                    .clip(
                        CircleShape
                    )//将图片裁剪为圆形
            )

            //间距组件
            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        CircleShape
                    )
            )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color = Color.Red)//设置纯色背景
            ) {
                Text(text = "纯色", Modifier.align(Alignment.Center))
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(brush = verticalGradientBrush)//设置垂直线性渐变背景
            ) {
                Text(text = "渐变色", modifier = Modifier.align(Alignment.Center))
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposedemoTheme {
    }
}