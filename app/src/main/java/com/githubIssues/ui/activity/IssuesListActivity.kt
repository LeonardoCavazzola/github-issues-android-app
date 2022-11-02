package com.githubIssues.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.githubIssues.model.Issue
import com.githubIssues.ui.component.LazyLoadingComponent
import com.githubIssues.ui.viewmodel.IssuesListViewModel
import org.koin.android.ext.android.inject

class IssueListActivity : ComponentActivity() {
    private val viewModel: IssuesListViewModel by inject()

    private fun renderIssueList() = runOnUiThread {
        setContent { IssueList(this, viewModel) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllIssues()
        renderIssueList()
    }
}

@Composable
fun IssueList(ctx: Context, viewModel: IssuesListViewModel) {
    LazyLoadingComponent(
        isLoading = viewModel.issues.isEmpty(),
        whenLoaded = {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = viewModel.issues,
                    itemContent = { Issue(ctx, it, viewModel) }
                )
            }
        }
    )
}

@Composable
fun Issue(ctx: Context, issue: Issue, viewModel: IssuesListViewModel) = Box(
    modifier = Modifier
        .border(0.5.dp, Color.LightGray)
        .clickable { viewModel.onIssueClick(ctx, issue) },
    content = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                Text(
                    modifier = Modifier.size(75.dp, 25.dp),
                    fontSize = 20.sp,
                    text = issue.state
                )
                Text(text = issue.title)
            }
        )
    }
)
