package com.rodcollab.keepsimple

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import java.util.Calendar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WidgetMonthsSelector() {

    val pagerState = rememberPagerState(Calendar.getInstance().get(Calendar.MONTH))

    WidgetDialog(onDismiss = { /*TODO*/ }) {

        Text(
            modifier = Modifier.alpha(0.8f),
            text = "Select the month",
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.size(12.dp))
        VerticalPager(
            modifier = Modifier
                .height(100.dp)
                .sizeIn(),
            pageSize = PageSize.Fixed(40.dp),
            pageCount = 12,
            contentPadding = PaddingValues(24.dp),
            state = pagerState,
            beyondBoundsPageCount = 4
        ) { page ->
            Text(
                modifier = Modifier
                    .alpha(if (pagerState.currentPage == page) 1f else 0.5f)
                    .wrapContentHeight(unbounded = true),
                fontSize = if (pagerState.currentPage == page) 24.sp else 16.sp,
                text = java.time.Month.of(page + 1).getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault())
            )
        }
    }
}

@Composable
internal fun WidgetDialog(onDismiss: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Dialog(
        onDismissRequest = onDismiss,
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(unbounded = true)
                .background(Color.White, RoundedCornerShape(24.dp))
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                content()
            }
        }
    }
}